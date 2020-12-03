package com.course.file.controller.admin;


import com.course.server.dto.FileDto;
import com.course.server.dto.ResponseDto;
import com.course.server.enums.FileUseEnum;
import com.course.server.service.FileService;
import com.course.server.util.Base64ToMultipartFile;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;


/**
 * @author itwell
 * @date 2020-11-11 15:37
 */
@RestController
@RequestMapping("/admin")
public class UploadController {
    private static final Logger logger = LoggerFactory.getLogger(UploadController.class);

    public static final String BUSINESS_NAME = "文件上传";

    @Value("${file.domain}")
    private String FILE_DOMAIN;

    @Value("${file.path}")
    private String FILE_PATH;

    @Autowired
    private FileService fileService;

    @RequestMapping("/upload")
    public ResponseDto test(@RequestBody FileDto fileDto) throws Exception {
        logger.info("上传文件开始");
        String use = fileDto.getUse();
        String key = fileDto.getKey();
        String suffix = fileDto.getSuffix();
        String shardBase64 = fileDto.getShard();
        MultipartFile shard = Base64ToMultipartFile.base64ToMultipart(shardBase64);

        //保存文件到本地
        FileUseEnum useEnum = FileUseEnum.getByCode(use);

        String dir = useEnum.name().toLowerCase();
        File fullDir = new File(FILE_PATH + dir);
        //如果文件夹不存在则创建
        if (!fullDir.exists()) {
            fullDir.mkdir();
        }

        //不同操作系统File.separator 目录分隔符
        //String path = dir + File.separator + key + "." + suffix + "." + fileDto.getShardIndex();

        String path = new StringBuffer(dir)
                .append(File.separator)
                .append(key).append(".")
                .append(suffix)
                .toString();    //不带.1

        String localPath = new StringBuffer(path)
                .append(".")
                .append(fileDto.getShardIndex())
                .toString();    //带.1

        String fullPath = FILE_PATH + localPath;
        File dest = new File(fullPath);
        shard.transferTo(dest);
        logger.info(dest.getAbsolutePath());

        logger.info("保存文件记录开始");
        fileDto.setPath(path);
        fileService.save(fileDto);

        ResponseDto responseDto = new ResponseDto();
        fileDto.setPath(FILE_DOMAIN + path);
        responseDto.setContent(fileDto);

        if (fileDto.getShardIndex().equals(fileDto.getShardTotal())){
            this.merge(fileDto);
        }

        return responseDto;
    }

    public void merge(FileDto fileDto) throws Exception {
        logger.info("合并分片开始======================>");
        String path = fileDto.getPath();    //http://127.0.0.1:9000/file/f/course\6sfSqfOwzmik4A4icMYuUe.mp4
        path = path.replace(FILE_DOMAIN, "");   //course\6sfSqfOwzmik4A4icMYuUe.mp4
        Integer shardTotal = fileDto.getShardTotal();   //
        File newFile = new File(FILE_PATH + path);
        FileOutputStream outputStream = new FileOutputStream(newFile, true);
        FileInputStream fileInputStream = null; //分片文件
        byte[] byt = new byte[10 * 1024 * 1024];
        int len;

        try {
            for (int i = 0; i < shardTotal; i++) {
                //读取第i个分片
                fileInputStream = new FileInputStream(new File(FILE_PATH + path + "." + (i + 1)));

                while ((len = fileInputStream.read(byt)) != -1) {
                    outputStream.write(byt, 0, len);
                }
            }
        } catch (IOException e) {
            logger.error("分片合并异常", e);
        } finally {
            try {
                if (fileInputStream != null) {
                    fileInputStream.close();
                }
                outputStream.close();
                logger.info("IO流关闭");
            } catch (IOException e) {
                logger.error("IO流关闭", e);
            }
        }
        logger.info("合并分片结束======================>");

        //释放对分片文件的占用
        System.gc();
        //给java虚拟机一定的时间让它把手头的工作都做完
        Thread.sleep(100);

        // 删除分片
        logger.info("删除分片开始");
        for (int i = 0; i < shardTotal; i++) {
            String filePath = FILE_PATH + path + "." + (i + 1);
            File file = new File(filePath);
            boolean result = file.delete();
            logger.info("删除{}，{}", filePath, result ? "成功" : "失败");
        }
        logger.info("删除分片结束");
    }

    @GetMapping("/check/{key}")
    public ResponseDto check(@PathVariable String key) throws Exception {
        logger.info("检查上传分片开始：{}", key);
        ResponseDto responseDto = new ResponseDto();
        FileDto fileDto = fileService.findByKey(key);
        //数据库里面放的是相对路径
        if(fileDto != null){
            fileDto.setPath(FILE_DOMAIN + fileDto.getPath());
        }
        responseDto.setContent(fileDto);
        return responseDto;
    }
}
