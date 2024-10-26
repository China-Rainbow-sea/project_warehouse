package com.rainbowsea.config;


import com.baomidou.mybatisplus.annotation.DbType;
import com.baomidou.mybatisplus.extension.plugins.MybatisPlusInterceptor;
import com.baomidou.mybatisplus.extension.plugins.inner.PaginationInnerInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MybatisPlusConfig {
    /**
     * 梳理:
     * 1. 注入 MyBatisPlusInterceptor 对象/bean
     * 2. 在MyBatisPlusInterceptor bean 加入分页插件 paginationInnerInterceptor
     */
    @Bean  // 不要忘记加 Bean 加入到 ioc 容器当中去
    public MybatisPlusInterceptor mybatisPlusInterceptor() {
        MybatisPlusInterceptor interceptor = new MybatisPlusInterceptor();
        // 这里分页需要指定数据库类型，因为不同的DB,分页SQL语句不同
        interceptor.addInnerInterceptor(new PaginationInnerInterceptor(DbType.MYSQL));
        return interceptor;
    }
}
