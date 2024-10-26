package com.rainbowsea.config;


import com.alibaba.druid.pool.DruidDataSource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;

@Configuration
@Slf4j
public class DruidDataSourceConfig {

    // 配置 -> 注入DruidDataSource

    //
    @Bean // 注意：要加入到Spring 容器当中去，进行一个ioc容器的管理，默认配置了数据源就会被替换，默认的数据源
    @ConfigurationProperties("spring.datasource")  // 根据application.yaml文件当中的前缀进行获取值，
    // 然后赋值到该内容当中去
    public DataSource dataSource() {
        DruidDataSource druidDataSource = new DruidDataSource();
        log.info("数据源={}", druidDataSource.getClass());
        return druidDataSource;
    }


}
