package com.course.server.service;

import com.course.server.domain.Category;
import com.course.server.domain.CategoryExample;
import com.course.server.dto.CategoryDto;
import com.course.server.dto.PageDto;
import com.course.server.mapper.CategoryMapper;
import com.course.server.util.CopyUtil;
import com.course.server.util.UuidUtil;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.github.pagehelper.util.StringUtil;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * @author itwell
 * @date 2020-11-11 16:39
 */
@Service
public class CategoryService {
    @Autowired
    CategoryMapper categoryMapper;

    /**
     * 查询所有
     */
    public List<CategoryDto> all() {
        //分页
        CategoryExample categoryExample = new CategoryExample();
        categoryExample.setOrderByClause("sort asc");
        List<Category> categoryList = categoryMapper.selectByExample(categoryExample);
        List<CategoryDto> categoryDtoList = CopyUtil.copyList(categoryList, CategoryDto.class);
        return categoryDtoList;
    }

    /**
     * 列表查询
     */
    public void list(PageDto pageDto) {
        //分页
        PageHelper.startPage(pageDto.getPage(),pageDto.getSize());

        CategoryExample categoryExample = new CategoryExample();
                categoryExample.setOrderByClause("sort asc");
        List<Category> categoryList = categoryMapper.selectByExample(categoryExample);

        PageInfo<Category> pageInfo = new PageInfo<>(categoryList);
        pageDto.setTotal(pageInfo.getTotal());

        List<CategoryDto> CategoryDtoList = new ArrayList<CategoryDto>();
                for (int i = 0; i < categoryList.size(); i++) {
                Category category = categoryList.get(i);
                CategoryDto categoryDto = new CategoryDto();
                BeanUtils.copyProperties(category,categoryDto);
                CategoryDtoList.add(categoryDto);
                }
                pageDto.setList(CategoryDtoList);
    }

    /**
    * 保存，id有值时更新，无值时新增
    */
    public void save(CategoryDto categoryDto) {
        Category category = CopyUtil.copy(categoryDto, Category.class);
        if(StringUtils.isEmpty(categoryDto.getId())){
        this.insert(category);
        }else {
        this.update(category);
        }
    }

    /**
    * 新增
    */
    private void insert(Category category) {
        category.setId(UuidUtil.getShortUuid());
        categoryMapper.insert(category);
    }

    /**
    * 更新
    */
    private void update(Category category) {
        categoryMapper.updateByPrimaryKey(category);
    }

    /**
    * 删除
    */
    public void delete(String id) {
        categoryMapper.deleteByPrimaryKey(id);
    }
}
