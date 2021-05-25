package com.example.druidmodule1.config;

import com.baomidou.mybatisplus.plugins.PaginationInterceptor;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * @author Tu
 * @Package com.example.config
 * @date 2021/1/29-17:23
 * PaginationInterceptor是一个分页插件
 */
@Configuration
@EnableTransactionManagement
@MapperScan("com.example.druidmodule1.mapper")
public class MybatisPlusConfig {
    @Bean
    public PaginationInterceptor paginationInterceptor() {
        PaginationInterceptor paginationInterceptor = new PaginationInterceptor();
//        BlogMapper.selectPage(paginationInterceptor,null);
        return paginationInterceptor;
    }
}
