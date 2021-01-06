package com.course.business.controller.admin;


import com.course.server.dto.PageDto;
import com.course.server.dto.ResponseDto;
import com.course.server.service.SmsService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @author itwell
 * @date 2020-11-11 15:37
 */
@RestController
@RequestMapping("/admin/sms")
public class SmsController {

    private static final Logger logger = LoggerFactory.getLogger(SmsController.class);
    /*表示controller的业务*/
    public static final String BUSINESS_NAME = "短信验证码";

    @Autowired
    SmsService smsService;

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
        smsService.list(pageDto);
        responseDto.setContent(pageDto);
        return responseDto;
    }

    /**
     * 保存大章
     * @param smsDto
     * @return
     */
    /*@PostMapping("/save")
    public ResponseDto save(@RequestBody SmsDto smsDto) {
        logger.info("pageDto: {}",smsDto);

            ValidatorUtil.require(smsDto.getMobile(), "手机号");
            ValidatorUtil.length(smsDto.getMobile(), "手机号", 1, 50);
            ValidatorUtil.require(smsDto.getCode(), "验证码");
            ValidatorUtil.require(smsDto.getUse(), "用途");
            ValidatorUtil.require(smsDto.getAt(), "生成时间");
            ValidatorUtil.require(smsDto.getStatus(), "用途");

        ResponseDto responseDto = new ResponseDto();
        smsService.save(smsDto);
        responseDto.setContent(smsDto);
        return responseDto;
    }*/

    /**
     * 删除大章
     *
     * @param id
     * @return
     */
    /*@DeleteMapping("/delete/{id}")
    public ResponseDto delete(@PathVariable String id) {
        logger.info("id: {}", id);
        ResponseDto responseDto = new ResponseDto();
        smsService.delete(id);
        return responseDto;
    }*/
}
