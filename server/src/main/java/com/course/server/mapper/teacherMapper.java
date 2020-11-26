package com.course.server.mapper;

import com.course.server.domain.teacher;
import com.course.server.domain.teacherExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface teacherMapper {
    long countByExample(teacherExample example);

    int deleteByExample(teacherExample example);

    int deleteByPrimaryKey(String id);

    int insert(teacher record);

    int insertSelective(teacher record);

    List<teacher> selectByExample(teacherExample example);

    teacher selectByPrimaryKey(String id);

    int updateByExampleSelective(@Param("record") teacher record, @Param("example") teacherExample example);

    int updateByExample(@Param("record") teacher record, @Param("example") teacherExample example);

    int updateByPrimaryKeySelective(teacher record);

    int updateByPrimaryKey(teacher record);
}