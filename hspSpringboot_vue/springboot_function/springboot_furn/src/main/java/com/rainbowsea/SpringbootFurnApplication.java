package com.rainbowsea;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan(basePackages = "com.rainbowsea.mapper")  // 表示让 springboot 扫描到该sql映射的包路径
public class SpringbootFurnApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringbootFurnApplication.class, args);
    }

}
