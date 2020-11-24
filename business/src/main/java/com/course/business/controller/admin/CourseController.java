package com.course.business.controller.admin;


import com.course.server.dto.*;
import com.course.server.service.CourseCategoryService;
import com.course.server.service.CourseService;
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
@RequestMapping("/admin/course")
public class CourseController {

    private static final Logger logger = LoggerFactory.getLogger(CourseController.class);
    /*表示controller的业务*/
    public static final String BUSINESS_NAME = "课程";

    @Autowired
    CourseService courseService;

    @Autowired
    private CourseCategoryService courseCategoryService;

    /**
     * 查询大章
     *
     * @param pageDto
     * @return
     */
    @PostMapping("/list")
    public ResponseDto test(@RequestBody PageDto pageDto) {
        logger.info("pageDto: {}", pageDto);
        ResponseDto responseDto = new ResponseDto();
        courseService.list(pageDto);
        responseDto.setContent(pageDto);
        return responseDto;
    }

    /**
     * 保存大章
     *
     * @param courseDto
     * @return
     */
    @PostMapping("/save")
    public ResponseDto save(@RequestBody CourseDto courseDto) {
        logger.info("pageDto: {}", courseDto);

        ValidatorUtil.require(courseDto.getName(), "名称");
        ValidatorUtil.length(courseDto.getName(), "名称", 1, 50);
        ValidatorUtil.length(courseDto.getSummary(), "概述", 1, 2000);
        ValidatorUtil.require(courseDto.getPrice(), "价格（元）");
        ValidatorUtil.length(courseDto.getImage(), "封面", 1, 100);

        ResponseDto responseDto = new ResponseDto();
        courseService.save(courseDto);
        responseDto.setContent(courseDto);
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
        courseService.delete(id);
        return responseDto;
    }

    /**
     * 查找课程下所有分类
     * @param courseId
     */
    @PostMapping("/list-category/{courseId}")
    public ResponseDto listCategory(@PathVariable(value = "courseId") String courseId) {
        ResponseDto responseDto = new ResponseDto();
        List<CourseCategoryDto> dtoList = courseCategoryService.listByCourse(courseId);
        responseDto.setContent(dtoList);
        return responseDto;
    }

    @GetMapping("/find-content/{id}")
    public ResponseDto findContent(@PathVariable String id) {
        ResponseDto responseDto = new ResponseDto();
        CourseContentDto contentDto = courseService.findContent(id);
        responseDto.setContent(contentDto);
        return responseDto;
    }

    @PostMapping("/save-content")
    public ResponseDto saveContent(@RequestBody CourseContentDto contentDto) {
        ResponseDto responseDto = new ResponseDto();
        courseService.saveContent(contentDto);
        return responseDto;
    }
}
