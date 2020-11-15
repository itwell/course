package com.course.business.controller.admin;


import com.course.server.dto.${Domain}Dto;
import com.course.server.dto.PageDto;
import com.course.server.dto.ResponseDto;
import com.course.server.service.${Domain}Service;
import com.course.server.domain.${Domain};
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
@RequestMapping("/admin/${domain}")
public class ${Domain}Controller {

    private static final Logger logger = LoggerFactory.getLogger(${Domain}Controller.class);
    /*表示controller的业务*/
    public static final String BUSINESS_NAME = "${tableNameCn}";

    @Autowired
    ${Domain}Service ${domain}Service;

    /**
     * 查询大章
     * @param pageDto
     * @return
     */
    @PostMapping("/list")
    public ResponseDto test(@RequestBody PageDto pageDto) {
        logger.info("pageDto: {}",pageDto);
        ResponseDto responseDto = new ResponseDto();
        ${domain}Service.list(pageDto);
        responseDto.setContent(pageDto);
        return responseDto;
    }

    /**
     * 保存大章
     * @param ${domain}Dto
     * @return
     */
    @PostMapping("/save")
    public ResponseDto save(@RequestBody ${Domain}Dto ${domain}Dto) {
        logger.info("pageDto: {}",${domain}Dto);

        ValidatorUtil.require(${domain}Dto.getName(),"名称");
        ValidatorUtil.require(${domain}Dto.getName(),"课程ID");
        ValidatorUtil.length(${domain}Dto.getCourseId(),"课程ID",1,8);

        ResponseDto responseDto = new ResponseDto();
        ${domain}Service.save(${domain}Dto);
        responseDto.setContent(${domain}Dto);
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
        ${domain}Service.delete(id);
        return responseDto;
    }
}
