package com.course.business.controller.admin;


import com.course.server.dto.MemberDto;
import com.course.server.dto.PageDto;
import com.course.server.dto.ResponseDto;
import com.course.server.service.MemberService;
import com.course.server.domain.Member;
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
@RequestMapping("/admin/member")
public class MemberController {

    private static final Logger logger = LoggerFactory.getLogger(MemberController.class);
    /*表示controller的业务*/
    public static final String BUSINESS_NAME = "会员";

    @Autowired
MemberService memberService;

    /**
     * 查询大章
     * @param pageDto
     * @return
     */
    @PostMapping("/list")
    public ResponseDto test(@RequestBody PageDto pageDto) {
        logger.info("pageDto: {}",pageDto);
        ResponseDto responseDto = new ResponseDto();
memberService.list(pageDto);
        responseDto.setContent(pageDto);
        return responseDto;
    }

    /**
     * 保存大章
     * @param memberDto
     * @return
     */
    @PostMapping("/save")
    public ResponseDto save(@RequestBody MemberDto memberDto) {
        logger.info("pageDto: {}",memberDto);

            ValidatorUtil.require(memberDto.getMobile(), "手机号");
            ValidatorUtil.length(memberDto.getMobile(), "手机号", 1, 11);
            ValidatorUtil.require(memberDto.getPassword(), "密码");
            ValidatorUtil.length(memberDto.getName(), "昵称", 1, 50);
            ValidatorUtil.length(memberDto.getPhoto(), "头像url", 1, 200);

        ResponseDto responseDto = new ResponseDto();
        memberService.save(memberDto);
        responseDto.setContent(memberDto);
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
memberService.delete(id);
        return responseDto;
    }
}
