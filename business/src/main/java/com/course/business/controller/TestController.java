package com.course.business.controller;



import com.course.server.domain.Test;
import com.course.server.service.TestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author itwell
 * @date 2020-11-11 15:37
 */
@RestController
public class TestController {
    @Autowired
    TestService testService;

    @RequestMapping("/test")
    public List<Test> test(){
        return testService.list();
    }
}
