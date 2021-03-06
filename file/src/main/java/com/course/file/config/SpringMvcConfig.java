package com.course.file.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class SpringMvcConfig implements WebMvcConfigurer {

    @Value("${file.path}")
    private String FILE_PATH;

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        //我访问 http://127.0.0.1:9003/file/f/teacher/8nZoCcDs-avator2.jpg
        //形成的路径就是 Z:/course/file/src/main/resources/images/course/teacher/8nZoCcDs-avator2.jpg

        //http://127.0.0.1:9000/file/f/teacher/sEb2BTHF.jpg
        //我访问 http://127.0.0.1:9000/file/f/course/3xzByd6GCMOocGK0qIa0kI.jpg
        //形成的路径就是 Z:/course/file/src/main/resources/images/course/teacher/8nZoCcDs-avator2.jpg
        registry.addResourceHandler("/f/**").addResourceLocations("file:" + FILE_PATH);
    }
}