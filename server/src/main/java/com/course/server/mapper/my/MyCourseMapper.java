package com.course.server.mapper.my;

import org.apache.ibatis.annotations.Param;

/**
 * @author itwell
 * @date 2020-11-20 13:37
 */
public interface MyCourseMapper {
    int updateTime(@Param("courseId") String courseId);
}
