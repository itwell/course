package com.course.server.service;

import com.course.server.domain.teacher;
import com.course.server.domain.teacherExample;
import com.course.server.dto.teacherDto;
import com.course.server.dto.PageDto;
import com.course.server.mapper.teacherMapper;
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
public class teacherService {
    @Autowired
    teacherMapper teacherMapper;

    /**
     * 列表查询
     */
    public void list(PageDto pageDto) {
        //分页
        PageHelper.startPage(pageDto.getPage(),pageDto.getSize());

        teacherExample teacherExample = new teacherExample();
        List<teacher> teacherList = teacherMapper.selectByExample(teacherExample);

        PageInfo<teacher> pageInfo = new PageInfo<>(teacherList);
        pageDto.setTotal(pageInfo.getTotal());

        List<teacherDto> teacherDtoList = new ArrayList<teacherDto>();
                for (int i = 0; i < teacherList.size(); i++) {
                teacher teacher = teacherList.get(i);
                teacherDto teacherDto = new teacherDto();
                BeanUtils.copyProperties(teacher,teacherDto);
                teacherDtoList.add(teacherDto);
                }
                pageDto.setList(teacherDtoList);
    }

    /**
    * 保存，id有值时更新，无值时新增
    */
    public void save(teacherDto teacherDto) {
        teacher teacher = CopyUtil.copy(teacherDto, teacher.class);
        if(StringUtils.isEmpty(teacherDto.getId())){
        this.insert(teacher);
        }else {
        this.update(teacher);
        }
    }

    /**
    * 新增
    */
    private void insert(teacher teacher) {
        teacher.setId(UuidUtil.getShortUuid());
        teacherMapper.insert(teacher);
    }

    /**
    * 更新
    */
    private void update(teacher teacher) {
        teacherMapper.updateByPrimaryKey(teacher);
    }

    /**
    * 删除
    */
    public void delete(String id) {
        teacherMapper.deleteByPrimaryKey(id);
    }
}
