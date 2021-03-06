package com.course.server.service;

import com.course.server.domain.Teacher;
import com.course.server.domain.TeacherExample;
import com.course.server.dto.PageDto;
import com.course.server.dto.TeacherDto;
import com.course.server.mapper.TeacherMapper;
import com.course.server.util.CopyUtil;
import com.course.server.util.UuidUtil;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
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
public class TeacherService {
    @Autowired
    TeacherMapper teacherMapper;

    /**
     * 查询所有
     */
    public List<TeacherDto> all() {
        //分页
        TeacherExample teacherExample = new TeacherExample();
        List<Teacher> teacherList = teacherMapper.selectByExample(teacherExample);
        List<TeacherDto> teacherDtoList = CopyUtil.copyList(teacherList, TeacherDto.class);
        return teacherDtoList;
    }

    /**
     * 列表查询
     */
    public void list(PageDto pageDto) {
        //分页
        PageHelper.startPage(pageDto.getPage(),pageDto.getSize());

        TeacherExample teacherExample = new TeacherExample();
        List<Teacher> teacherList = teacherMapper.selectByExample(teacherExample);

        PageInfo<Teacher> pageInfo = new PageInfo<>(teacherList);
        pageDto.setTotal(pageInfo.getTotal());

        List<TeacherDto> teacherDtoList = new ArrayList<TeacherDto>();
                for (int i = 0; i < teacherList.size(); i++) {
                Teacher teacher = teacherList.get(i);
                TeacherDto teacherDto = new TeacherDto();
                BeanUtils.copyProperties(teacher,teacherDto);
                teacherDtoList.add(teacherDto);
                }
                pageDto.setList(teacherDtoList);
    }

    /**
    * 保存，id有值时更新，无值时新增
    */
    public void save(TeacherDto teacherDto) {
        Teacher teacher = CopyUtil.copy(teacherDto, Teacher.class);
        if(StringUtils.isEmpty(teacherDto.getId())){
        this.insert(teacher);
        }else {
        this.update(teacher);
        }
    }

    /**
    * 新增
    */
    private void insert(Teacher teacher) {
        teacher.setId(UuidUtil.getShortUuid());
        teacherMapper.insert(teacher);
    }

    /**
    * 更新
    */
    private void update(Teacher teacher) {
        teacherMapper.updateByPrimaryKey(teacher);
    }

    /**
    * 删除
    */
    public void delete(String id) {
        teacherMapper.deleteByPrimaryKey(id);
    }

    /**
     * 查找
     * @param id
     */
    public TeacherDto findById(String id) {
        Teacher teacher = teacherMapper.selectByPrimaryKey(id);
        return CopyUtil.copy(teacher, TeacherDto.class);
    }
}
