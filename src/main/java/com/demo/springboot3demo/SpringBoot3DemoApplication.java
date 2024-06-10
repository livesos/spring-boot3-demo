package com.demo.springboot3demo;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author BHG
 */
@SpringBootApplication
@MapperScan("com.demo.springboot3demo.mapper")
public class SpringBoot3DemoApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringBoot3DemoApplication.class, args);
    }

}
