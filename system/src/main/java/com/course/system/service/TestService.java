package com.course.system.service;

import com.course.system.domain.Test;
import com.course.system.mapper.TestMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author itwell
 * @date 2020-11-11 16:39
 */
@Service
public class TestService {
    @Autowired
    TestMapper testMapper;

    public List<Test> list(){
        return testMapper.list();
    };
}
