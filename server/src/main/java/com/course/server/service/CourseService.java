package com.course.server.service;

import com.course.server.domain.Course;
import com.course.server.domain.CourseContent;
import com.course.server.domain.CourseExample;
import com.course.server.dto.*;
import com.course.server.enums.CourseStatusEnum;
import com.course.server.mapper.CourseContentMapper;
import com.course.server.mapper.CourseMapper;
import com.course.server.mapper.my.MyCourseMapper;
import com.course.server.util.CopyUtil;
import com.course.server.util.UuidUtil;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.github.pagehelper.util.StringUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @author itwell
 * @date 2020-11-11 16:39
 */
@Service
public class CourseService {

    private static final Logger logger = LoggerFactory.getLogger(CourseService.class);

    @Autowired
    private CourseMapper courseMapper;

    @Autowired
    private MyCourseMapper myCourseMapper;

    @Autowired
    private CourseCategoryService courseCategoryService;

    @Autowired
    private CourseContentMapper courseContentMapper;

    /**
     * 列表查询
     */
    public void list(CoursePageDto pageDto) {
        //分页
        PageHelper.startPage(pageDto.getPage(),pageDto.getSize());
        CourseExample courseExample = new CourseExample();
        CourseExample.Criteria criteria = courseExample.createCriteria();
        //是否为发布状态
        if (!StringUtils.isEmpty(pageDto.getStatus())){
            criteria.andStatusEqualTo(pageDto.getStatus());
        }
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
    @Transactional
    public void save(CourseDto courseDto) {
        Course course = CopyUtil.copy(courseDto, Course.class);
        if(StringUtils.isEmpty(courseDto.getId())){
        this.insert(course);
        }else {
        this.update(course);
        }

        //批量保存课程分类
        courseCategoryService.saveBatch(course.getId(),courseDto.getCategorys());
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

    /**
     * 更新课程时长
     */
    public void updateTime(String courseId){
        logger.info("更新课程时长:{}",courseId);
        myCourseMapper.updateTime(courseId);
    }

    /**
     * 查找课程内容
     */
    public CourseContentDto findContent(String id) {
        CourseContent content = courseContentMapper.selectByPrimaryKey(id);
        if (content == null) {
            return null;
        }
        return CopyUtil.copy(content, CourseContentDto.class);
    }

    /**
     * 保存课程内容，包含新增和修改
     */
    public int saveContent(CourseContentDto contentDto) {
        CourseContent content = CopyUtil.copy(contentDto, CourseContent.class);
        int i = courseContentMapper.updateByPrimaryKeyWithBLOBs(content);
        if (i == 0) {
            i = courseContentMapper.insert(content);
        }
        return i;
    }

    /**
     * 排序
     * @param sortDto
     */
    @Transactional
    public void sort(SortDto sortDto) {
        // 修改当前记录的排序值
        myCourseMapper.updateSort(sortDto);

        // 如果排序值变大
        if (sortDto.getNewSort() > sortDto.getOldSort()) {
            myCourseMapper.moveSortsForward(sortDto);
        }

        // 如果排序值变小
        //把中间的值往后移一位
        if (sortDto.getNewSort() < sortDto.getOldSort()) {
            myCourseMapper.moveSortsBackward(sortDto);
        }
    }

    /**
     * 新课列表查询，只查询已发布的，按创建日期倒序
     */
    public List<CourseDto> listNew(PageDto pageDto) {
        PageHelper.startPage(pageDto.getPage(), pageDto.getSize());
        CourseExample courseExample = new CourseExample();
        courseExample.createCriteria().andStatusEqualTo(CourseStatusEnum.PUBLISH.getCode());
        courseExample.setOrderByClause("created_at desc");
        List<Course> courseList = courseMapper.selectByExample(courseExample);
        return CopyUtil.copyList(courseList, CourseDto.class);
    }
}
