package com.course.server.service;

import com.course.server.domain.Test;
import com.course.server.domain.TestExample;
import com.course.server.mapper.TestMapper;
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
        TestExample testExample = new TestExample();
        testExample.setOrderByClause("id desc");
        return testMapper.selectByExample(testExample);
    }
}
