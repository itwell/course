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
    public ResponseDto test(@RequestBody FileDto fileDto) throws IOException {
        logger.info("上传文件开始");
        String use = fileDto.getUse();
        String key = fileDto.getKey();
        String suffix = fileDto.getSuffix();
        String shardBase64 = fileDto.getShard();
        MultipartFile shard = Base64ToMultipartFile.base64ToMultipart(shardBase64);

        //保存文件到本地
        FileUseEnum useEnum = FileUseEnum.getByCode(use);

        //如果文件夹不存在则创建
        String dir = useEnum.name().toLowerCase();
        File fullDir = new File(FILE_PATH + dir);
        if (!fullDir.exists()) {
            fullDir.mkdir();
        }

        //不同操作系统File.separator 目录分隔符
        String path = dir + File.separator + key + "." + suffix;
        String fullPath = FILE_PATH + path;
        File dest = new File(fullPath);
        shard.transferTo(dest);
        logger.info(dest.getAbsolutePath());

        logger.info("保存文件记录开始");
        fileDto.setPath(path);
        fileService.save(fileDto);

        ResponseDto responseDto = new ResponseDto();
        fileDto.setPath(FILE_DOMAIN + path);
        responseDto.setContent(fileDto);
        return responseDto;
    }

    @GetMapping("/merge")
    public ResponseDto merge() throws Exception {
        File newFile = new File(FILE_PATH + "/course/test123.mp4");
        FileOutputStream outputStream = new FileOutputStream(newFile, true);
        FileInputStream fileInputStream = null; //分片文件
        byte[] byt = new byte[10 * 1024 * 1024];
        int len;

        try {
            //读取第一个分片
            fileInputStream = new FileInputStream(new File(FILE_PATH + "/course/Bc05XtFn.blob"));

            while ((len = fileInputStream.read(byt)) != -1) {
                outputStream.write(byt, 0, len);
            }

            //读取第二个分片
            fileInputStream = new FileInputStream(new File(FILE_PATH + "/course/roQbPm2x.blob"));

            while ((len = fileInputStream.read(byt)) != -1) {
                outputStream.write(byt, 0, len);
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

        ResponseDto responseDto = new ResponseDto();
        return responseDto;
    }
}
