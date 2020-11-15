package com.course.generator.server;

import com.course.generator.util.FreemarkerUtil;

import java.io.File;
import java.util.*;

public class ServerGenerator {
    static String MODULE = "business";
    static String toServicePath = "server\\src\\main\\java\\com\\course\\server\\service\\";
    static String toControllerPath = MODULE+"\\src\\main\\java\\com\\course\\"+MODULE+"\\controller\\admin\\";

    public static void main(String[] args) throws Exception {
        String Domain = "Section";
        String domain = "section";
        String tableNameCn = "小节";
        String module = MODULE;
        HashMap<String, Object> map = new HashMap<>();
        map.put("Domain",Domain);
        map.put("domain",domain);
        map.put("tableNameCn",tableNameCn);

        /*service*/
        FreemarkerUtil.initConfig("service.ftl");
        FreemarkerUtil.generator(toServicePath+Domain+"Service.java",map);

        /*controller*/
        FreemarkerUtil.initConfig("controller.ftl");
        FreemarkerUtil.generator(toControllerPath+Domain+"Controller.java",map);
    }
}
