package com.course.business.controller.admin;


import com.course.server.dto.CategoryDto;
import com.course.server.dto.PageDto;
import com.course.server.dto.ResponseDto;
import com.course.server.service.CategoryService;
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
@RequestMapping("/admin/category")
public class CategoryController {

    private static final Logger logger = LoggerFactory.getLogger(CategoryController.class);
    /*表示controller的业务*/
    public static final String BUSINESS_NAME = "分类";

    @Autowired
    CategoryService categoryService;

    /**
     * 查询所有
     */
    @PostMapping("/all")
    public ResponseDto all() {
        ResponseDto responseDto = new ResponseDto();
        List<CategoryDto> categoryDtoList = categoryService.all();
        responseDto.setContent(categoryDtoList);
        return responseDto;
    }

    /**
     * 查询大章
     *
     * @param pageDto
     * @return
     */
    @PostMapping("/list")
    public ResponseDto list(@RequestBody PageDto pageDto) {
        logger.info("pageDto: {}", pageDto);
        ResponseDto responseDto = new ResponseDto();
        categoryService.list(pageDto);
        responseDto.setContent(pageDto);
        return responseDto;
    }

    /**
     * 保存大章
     *
     * @param categoryDto
     * @return
     */
    @PostMapping("/save")
    public ResponseDto save(@RequestBody CategoryDto categoryDto) {
        logger.info("pageDto: {}", categoryDto);

        ValidatorUtil.require(categoryDto.getParent(), "父id");
        ValidatorUtil.require(categoryDto.getName(), "名称");
        ValidatorUtil.length(categoryDto.getName(), "名称", 1, 50);

        ResponseDto responseDto = new ResponseDto();
        categoryService.save(categoryDto);
        responseDto.setContent(categoryDto);
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
        categoryService.delete(id);
        return responseDto;
    }
}
