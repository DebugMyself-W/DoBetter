package com.debugmyself.config;

import com.baomidou.mybatisplus.extension.plugins.PaginationInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * MyBatisPlusIntegration
 *
 * @ClassName MyBatisPlusConfig 分页配置
 * @Description TODO
 * @Author gcl
 * @Date 2021-03-18 8:36
 **/
@Configuration
public class MyBatisPlusConfig {
    /**
     * mybatis-plus 分页插件
     */
    @Bean
    public PaginationInterceptor paginationInterceptor() {
        PaginationInterceptor paginationInterceptor = new PaginationInterceptor();
        return paginationInterceptor;
    }
}
