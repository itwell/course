package com.course.generator.server;

import com.course.generator.util.FreemarkerUtil;

import java.io.File;
import java.util.*;

public class ServerGenerator {
    static String toPath = "generator\\src\\main\\java\\com\\course\\generator\\test\\";

    public static void main(String[] args) throws Exception {
        FreemarkerUtil.initConfig("test.ftl");
        FreemarkerUtil.generator(toPath+"Test.java");
    }
}
