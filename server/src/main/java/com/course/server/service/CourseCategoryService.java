package com.course.server.service;

import com.course.server.domain.CourseCategory;
import com.course.server.domain.CourseCategoryExample;
import com.course.server.dto.CategoryDto;
import com.course.server.dto.CourseCategoryDto;
import com.course.server.dto.PageDto;
import com.course.server.mapper.CourseCategoryMapper;
import com.course.server.util.CopyUtil;
import com.course.server.util.UuidUtil;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.github.pagehelper.util.StringUtil;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * @author itwell
 * @date 2020-11-11 16:39
 */
@Service
public class CourseCategoryService {
    @Autowired
    CourseCategoryMapper courseCategoryMapper;

    /**
     * 列表查询
     */
    public void list(PageDto pageDto) {
        //分页
        PageHelper.startPage(pageDto.getPage(),pageDto.getSize());

        CourseCategoryExample courseCategoryExample = new CourseCategoryExample();
        List<CourseCategory> courseCategoryList = courseCategoryMapper.selectByExample(courseCategoryExample);

        PageInfo<CourseCategory> pageInfo = new PageInfo<>(courseCategoryList);
        pageDto.setTotal(pageInfo.getTotal());

        List<CourseCategoryDto> CourseCategoryDtoList = new ArrayList<CourseCategoryDto>();
                for (int i = 0; i < courseCategoryList.size(); i++) {
                CourseCategory courseCategory = courseCategoryList.get(i);
                CourseCategoryDto courseCategoryDto = new CourseCategoryDto();
                BeanUtils.copyProperties(courseCategory,courseCategoryDto);
                CourseCategoryDtoList.add(courseCategoryDto);
                }
                pageDto.setList(CourseCategoryDtoList);
    }

    /**
    * 保存，id有值时更新，无值时新增
    */
    public void save(CourseCategoryDto courseCategoryDto) {
        CourseCategory courseCategory = CopyUtil.copy(courseCategoryDto, CourseCategory.class);
        if(StringUtils.isEmpty(courseCategoryDto.getId())){
        this.insert(courseCategory);
        }else {
        this.update(courseCategory);
        }
    }

    /**
    * 新增
    */
    private void insert(CourseCategory courseCategory) {
        courseCategory.setId(UuidUtil.getShortUuid());
        courseCategoryMapper.insert(courseCategory);
    }

    /**
    * 更新
    */
    private void update(CourseCategory courseCategory) {
        courseCategoryMapper.updateByPrimaryKey(courseCategory);
    }

    /**
    * 删除
    */
    public void delete(String id) {
        courseCategoryMapper.deleteByPrimaryKey(id);
    }

    /**
     * 批量保存
     */
    @Transactional
    public void saveBatch(String courseId, List<CategoryDto> dtoList){
        CourseCategoryExample example = new CourseCategoryExample();
        example.createCriteria().andCourseIdEqualTo(courseId);
        for (int i = 0; i < dtoList.size(); i++) {
            CategoryDto categoryDto = dtoList.get(i);
            CourseCategory courseCategory = new CourseCategory();
            courseCategory.setId(UuidUtil.getShortUuid());
            courseCategory.setCourseId(courseId);
            courseCategory.setCategoryId(categoryDto.getId());
            insert(courseCategory);
        }
    }

    /**
     * 查找课程下所有分类
     * @param courseId
     */
    public List<CourseCategoryDto> listByCourse(String courseId) {
        CourseCategoryExample example = new CourseCategoryExample();
        example.createCriteria().andCourseIdEqualTo(courseId);
        List<CourseCategory> courseCategoryList = courseCategoryMapper.selectByExample(example);
        return CopyUtil.copyList(courseCategoryList, CourseCategoryDto.class);
    }
}
