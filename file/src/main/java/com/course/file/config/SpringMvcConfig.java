package com.course.file.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class SpringMvcConfig implements WebMvcConfigurer {

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        //我访问 http://127.0.0.1:9003/file/f/teacher/8nZoCcDs-avator2.jpg
        //形成的路径就是 Z:/course/file/src/main/resources/images/course/teacher/8nZoCcDs-avator2.jpg
        registry.addResourceHandler("/f/**").addResourceLocations("file:Z:/course/file/src/main/resources/images/course/");
    }
}