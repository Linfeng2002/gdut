package com.gdut.backend.common;

import com.baomidou.mybatisplus.generator.FastAutoGenerator;
import com.baomidou.mybatisplus.generator.engine.FreemarkerTemplateEngine;

import java.util.Arrays;
import java.util.List;

public class injectionGenerator {
    public static void main(String[] args) {
        String OutPath=System.getProperty("user.dir")+"/src/main/java";
        String url="jdbc:mysql://localhost:3306/gdut?useUnicode=true&characterEncoding=utf-8&useSSL=false&serverTimezone=GMT%2B8";
        FastAutoGenerator.create(url,"root","123456")
                // 全局配置
                .globalConfig((scanner, builder) -> builder.outputDir(OutPath).author("杰哥").disableOpenDir().fileOverride())
                // 包配置
                .packageConfig((scanner, builder) -> builder.parent("com.gdut.backend").entity("po"))
                // 策略配置
                .strategyConfig((scanner, builder) -> builder.addInclude(scanner.apply("请输入表名，多个英文逗号分隔？所有输入 all"))
                        .controllerBuilder().enableRestStyle().enableHyphenStyle()
                        .entityBuilder().enableLombok().entityBuilder().enableFileOverride()
                        .build())
                /*
                    模板引擎配置，默认 Velocity 可选模板引擎 Beetl 或 Freemarker
                   .templateEngine(new BeetlTemplateEngine())*/
                .templateEngine(new FreemarkerTemplateEngine())
                //执行
                .execute();
    }



    // 处理 all 情况
//    protected static List<String> getTables(String tables) {
//        return "all".equals(tables) ? Collctions.emptyList() : Arrays.asList(tables.split(","));
//    }
}
