package com.course.business.controller.admin;


import com.course.server.dto.CourseContentFileDto;
import com.course.server.dto.ResponseDto;
import com.course.server.service.CourseContentFileService;
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
@RequestMapping("/admin/course-content-file")
public class CourseContentFileController {

    private static final Logger logger = LoggerFactory.getLogger(CourseContentFileController.class);
    /*表示controller的业务*/
    public static final String BUSINESS_NAME = "课程内容文件";

    @Autowired
    CourseContentFileService courseContentFileService;

    /**
     * 列表查询
     */
    @GetMapping("/list/{courseId}")
    public ResponseDto list(@PathVariable String courseId) {
        ResponseDto responseDto = new ResponseDto();
        List<CourseContentFileDto> fileDtoList = courseContentFileService.list(courseId);
        responseDto.setContent(fileDtoList);
        return responseDto;
    }

    /**
     * 保存大章
     *
     * @param courseContentFileDto
     * @return
     */
    @PostMapping("/save")
    public ResponseDto save(@RequestBody CourseContentFileDto courseContentFileDto) {
        logger.info("pageDto: {}", courseContentFileDto);

        ValidatorUtil.require(courseContentFileDto.getCourseId(), "课程id");
        ValidatorUtil.length(courseContentFileDto.getUrl(), "地址", 1, 100);
        ValidatorUtil.length(courseContentFileDto.getName(), "文件名", 1, 100);

        ResponseDto responseDto = new ResponseDto();
        courseContentFileService.save(courseContentFileDto);
        responseDto.setContent(courseContentFileDto);
        return responseDto;
    }

    /**
     * 删除大章
     *
     * @param id
     * @return
     */
    @DeleteMapping("/delete/{id}")
    public ResponseDto delete(@PathVariable String id) {
        logger.info("id: {}", id);
        ResponseDto responseDto = new ResponseDto();
        courseContentFileService.delete(id);
        return responseDto;
    }
}
