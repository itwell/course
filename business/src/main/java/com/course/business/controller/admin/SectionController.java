package com.course.business.controller.admin;


import com.course.server.dto.SectionDto;
import com.course.server.dto.PageDto;
import com.course.server.dto.ResponseDto;
import com.course.server.service.SectionService;
import com.course.server.domain.Section;
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
@RequestMapping("/admin/section")
public class SectionController {

    private static final Logger logger = LoggerFactory.getLogger(SectionController.class);
    /*表示controller的业务*/
    public static final String BUSINESS_NAME = "小节";

    @Autowired
    SectionService sectionService;

    /**
     * 查询大章
     * @param pageDto
     * @return
     */
    @PostMapping("/list")
    public ResponseDto test(@RequestBody PageDto pageDto) {
        logger.info("pageDto: {}",pageDto);
        ResponseDto responseDto = new ResponseDto();
        sectionService.list(pageDto);
        responseDto.setContent(pageDto);
        return responseDto;
    }

    /**
     * 保存大章
     * @param sectionDto
     * @return
     */
    @PostMapping("/save")
    public ResponseDto save(@RequestBody SectionDto sectionDto) {
        logger.info("pageDto: {}",sectionDto);


        ResponseDto responseDto = new ResponseDto();
        sectionService.save(sectionDto);
        responseDto.setContent(sectionDto);
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
        sectionService.delete(id);
        return responseDto;
    }
}
