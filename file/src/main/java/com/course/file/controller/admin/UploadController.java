package com.course.file.controller.admin;


import com.course.server.dto.ResponseDto;
import com.course.server.util.UuidUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;


/**
 * @author itwell
 * @date 2020-11-11 15:37
 */
@RestController
@RequestMapping("/admin")
public class UploadController {
    private static final Logger logger = LoggerFactory.getLogger(UploadController.class);

    @RequestMapping("/upload")
    public ResponseDto test(@RequestParam MultipartFile file) throws IOException {
        logger.info("上传文件开始:{}",file);
        logger.info(file.getOriginalFilename());
        logger.info(String.valueOf(file.getSize()));

        //保存文件到本地
        String fileName = file.getOriginalFilename();
        String key = UuidUtil.getShortUuid();
        String fullPath = "Z:/course/file/src/main/resources/images/" + key + "-" + fileName;
        File dest = new File(fullPath);
        file.transferTo(dest);

        logger.info(dest.getAbsolutePath());
        ResponseDto responseDto = new ResponseDto();
        return responseDto;
    }
}
