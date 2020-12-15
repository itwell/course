package com.course.business.controller.admin;


import com.course.server.dto.UserDto;
import com.course.server.dto.PageDto;
import com.course.server.dto.ResponseDto;
import com.course.server.service.UserService;
import com.course.server.domain.User;
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
@RequestMapping("/admin/user")
public class UserController {

    private static final Logger logger = LoggerFactory.getLogger(UserController.class);
    /*表示controller的业务*/
    public static final String BUSINESS_NAME = "";

    @Autowired
    UserService userService;

    /**
     * 查询大章
     * @param pageDto
     * @return
     */
    @PostMapping("/list")
    public ResponseDto test(@RequestBody PageDto pageDto) {
        logger.info("pageDto: {}",pageDto);
        ResponseDto responseDto = new ResponseDto();
userService.list(pageDto);
        responseDto.setContent(pageDto);
        return responseDto;
    }

    /**
     * 保存大章
     * @param userDto
     * @return
     */
    @PostMapping("/save")
    public ResponseDto save(@RequestBody UserDto userDto) {
        logger.info("pageDto: {}",userDto);

            ValidatorUtil.require(userDto.getLoginName(), "登陆名");
            ValidatorUtil.length(userDto.getLoginName(), "登陆名", 1, 50);
            ValidatorUtil.length(userDto.getName(), "昵称", 1, 50);
            ValidatorUtil.require(userDto.getPassword(), "密码");

        ResponseDto responseDto = new ResponseDto();
        userService.save(userDto);
        responseDto.setContent(userDto);
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
userService.delete(id);
        return responseDto;
    }
}
