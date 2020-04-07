package com.example.shardjdbcdemo;

import com.alibaba.druid.spring.boot.autoconfigure.DruidDataSourceAutoConfigure;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.web.servlet.ServletComponentScan;

import java.math.BigDecimal;

@ServletComponentScan
@SpringBootApplication(scanBasePackages = {"com.example"},exclude = {DataSourceAutoConfiguration.class,DruidDataSourceAutoConfigure.class})
@MapperScan(basePackages = {"com.example.shardjdbcdemo.mapper"})
public class ShardJdbcDemoApplication {

    public static void main(String[] args) {
        SpringApplication.run(ShardJdbcDemoApplication.class, args);
    }

}
