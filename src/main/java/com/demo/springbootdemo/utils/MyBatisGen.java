package com.demo.springbootdemo.utils;

import org.mybatis.generator.api.MyBatisGenerator;
import org.mybatis.generator.config.Configuration;
import org.mybatis.generator.config.xml.ConfigurationParser;
import org.mybatis.generator.internal.DefaultShellCallback;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

/**
 * mybatis生成代码插件启动类
 *
 * @author kai.guan
 * @date 2019/7/10 16:15
 **/
public class MyBatisGen {

    public static void main(String[] args) throws Exception{
        //mybatis-generatorconfig.xml地址
        String fileName = "E:\\idea_workspace_test\\SpringBootDemo\\src\\main\\resources\\mybatis-generatorconfig.xml";
        if (args.length > 0){
            fileName = args[0];
        }
        File configFile = new File(fileName);
        List<String> warnings = new ArrayList<>();
        boolean overwrite = true;
        ConfigurationParser cp = new ConfigurationParser(warnings);
        Configuration config = cp.parseConfiguration(configFile);
        DefaultShellCallback callback = new DefaultShellCallback(overwrite);
        MyBatisGenerator myBatisGenerator = new MyBatisGenerator(config, callback, warnings);
        myBatisGenerator.generate(null);
    }
}
