package com.course.server.service;

import com.course.server.domain.Course;
import com.course.server.domain.CourseExample;
import com.course.server.dto.CourseDto;
import com.course.server.dto.PageDto;
import com.course.server.mapper.CourseMapper;
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
import java.util.Date;

/**
 * @author itwell
 * @date 2020-11-11 16:39
 */
@Service
public class CourseService {
    @Autowired
    CourseMapper courseMapper;

    /**
     * 列表查询
     */
    public void list(PageDto pageDto) {
        //分页
        PageHelper.startPage(pageDto.getPage(),pageDto.getSize());

        CourseExample courseExample = new CourseExample();
                courseExample.setOrderByClause("sort asc");
        List<Course> courseList = courseMapper.selectByExample(courseExample);

        PageInfo<Course> pageInfo = new PageInfo<>(courseList);
        pageDto.setTotal(pageInfo.getTotal());

        List<CourseDto> CourseDtoList = new ArrayList<CourseDto>();
                for (int i = 0; i < courseList.size(); i++) {
                Course course = courseList.get(i);
                CourseDto courseDto = new CourseDto();
                BeanUtils.copyProperties(course,courseDto);
                CourseDtoList.add(courseDto);
                }
                pageDto.setList(CourseDtoList);
    }

    /**
    * 保存，id有值时更新，无值时新增
    */
    public void save(CourseDto courseDto) {
        Course course = CopyUtil.copy(courseDto, Course.class);
        if(StringUtils.isEmpty(courseDto.getId())){
        this.insert(course);
        }else {
        this.update(course);
        }
    }

    /**
    * 新增
    */
    private void insert(Course course) {
        Date now = new Date();
                course.setCreatedAt(now);
                course.setUpdatedAt(now);
        course.setId(UuidUtil.getShortUuid());
        courseMapper.insert(course);
    }

    /**
    * 更新
    */
    private void update(Course course) {
                course.setUpdatedAt(new Date());
        courseMapper.updateByPrimaryKey(course);
    }

    /**
    * 删除
    */
    public void delete(String id) {
        courseMapper.deleteByPrimaryKey(id);
    }
}