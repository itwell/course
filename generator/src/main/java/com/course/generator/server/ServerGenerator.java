package com.course.generator.server;

import com.course.generator.util.FreemarkerUtil;

import java.io.File;
import java.util.*;

public class ServerGenerator {
    static String toServicePath = "server\\src\\main\\java\\com\\course\\server\\service\\";

    public static void main(String[] args) throws Exception {
        String Domain = "Section";
        String domain = "section";
        HashMap<String, Object> map = new HashMap<>();
        map.put("Domain",Domain);
        map.put("domain",domain);
        FreemarkerUtil.initConfig("service.ftl");
        FreemarkerUtil.generator(toServicePath+Domain+"Service.java",map);
    }
}
