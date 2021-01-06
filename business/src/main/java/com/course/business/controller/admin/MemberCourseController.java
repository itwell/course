package com.course.business.controller.admin;


import com.course.server.dto.MemberCourseDto;
import com.course.server.dto.PageDto;
import com.course.server.dto.ResponseDto;
import com.course.server.service.MemberCourseService;
import com.course.server.util.ValidatorUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @author itwell
 * @date 2020-11-11 15:37
 */
@RestController
@RequestMapping("/admin/memberCourse")
public class MemberCourseController {

    private static final Logger logger = LoggerFactory.getLogger(MemberCourseController.class);
    /*表示controller的业务*/
    public static final String BUSINESS_NAME = "会员课程报名";

    @Autowired
MemberCourseService memberCourseService;

    /**
     * 查询大章
     * @param pageDto
     * @return
     */
    @PostMapping("/list")
    public ResponseDto test(@RequestBody PageDto pageDto) {
        logger.info("pageDto: {}",pageDto);
        ResponseDto responseDto = new ResponseDto();
memberCourseService.list(pageDto);
        responseDto.setContent(pageDto);
        return responseDto;
    }

    /**
     * 保存大章
     * @param memberCourseDto
     * @return
     */
    @PostMapping("/save")
    public ResponseDto save(@RequestBody MemberCourseDto memberCourseDto) {
        logger.info("pageDto: {}",memberCourseDto);

            ValidatorUtil.require(memberCourseDto.getMemberId(), "会员id");
            ValidatorUtil.require(memberCourseDto.getCourseId(), "课程id");
            ValidatorUtil.require(memberCourseDto.getAt(), "报名时间");

        ResponseDto responseDto = new ResponseDto();
        memberCourseService.save(memberCourseDto);
        responseDto.setContent(memberCourseDto);
        return responseDto;
    }

    /**
     * 保存，id有值时更新，无值时新增
     */
    @PostMapping("/get-enroll")
    public ResponseDto getEnroll(@RequestBody MemberCourseDto memberCourseDto) {
        ResponseDto responseDto = new ResponseDto();
        memberCourseDto = memberCourseService.getEnroll(memberCourseDto);
        responseDto.setContent(memberCourseDto);
        return responseDto;
    }
}
