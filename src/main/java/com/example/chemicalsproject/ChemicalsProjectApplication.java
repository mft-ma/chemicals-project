package com.example.chemicalsproject;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.example.chemicalsproject.mapper")//扫描mapper文件夹
public class ChemicalsProjectApplication {

    public static void main(String[] args) {
        SpringApplication.run(ChemicalsProjectApplication.class, args);
    }

}
