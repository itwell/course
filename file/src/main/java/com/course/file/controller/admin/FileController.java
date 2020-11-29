package com.course.business.controller.admin;


import com.course.server.dto.FileDto;
import com.course.server.dto.PageDto;
import com.course.server.dto.ResponseDto;
import com.course.server.service.FileService;
import com.course.server.domain.File;
import com.course.server.util.ValidatorUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author itwell
 * @date 2020-11-11 15:37
 */
@RestController
@RequestMapping("/admin/file")
public class FileController {

    private static final Logger logger = LoggerFactory.getLogger(FileController.class);
    /*表示controller的业务*/
    public static final String BUSINESS_NAME = "文件";

    @Autowired
FileService fileService;

    /**
     * 查询大章
     * @param pageDto
     * @return
     */
    @PostMapping("/list")
    public ResponseDto test(@RequestBody PageDto pageDto) {
        logger.info("pageDto: {}",pageDto);
        ResponseDto responseDto = new ResponseDto();
fileService.list(pageDto);
        responseDto.setContent(pageDto);
        return responseDto;
    }

    /**
     * 保存大章
     * @param fileDto
     * @return
     */
    @PostMapping("/save")
    public ResponseDto save(@RequestBody FileDto fileDto) {
        logger.info("pageDto: {}",fileDto);

            ValidatorUtil.require(fileDto.getPath(), "相对路径");
            ValidatorUtil.length(fileDto.getPath(), "相对路径", 1, 100);
            ValidatorUtil.length(fileDto.getName(), "文件名", 1, 100);
            ValidatorUtil.length(fileDto.getSuffix(), "后缀", 1, 10);

        ResponseDto responseDto = new ResponseDto();
        fileService.save(fileDto);
        responseDto.setContent(fileDto);
        return responseDto;
    }

    /**
     * 删除大章
     * @param id
     * @return
     */
    @DeleteMapping("/delete/{id}")
    public ResponseDto delete(@PathVariable String id) {
        logger.info("id: {}",id);
        ResponseDto responseDto = new ResponseDto();
fileService.delete(id);
        return responseDto;
    }
}
