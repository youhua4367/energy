package com.example.energy_20231120306;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.example.energy_20231120306.mapper")
public class Energy20231120306Application {

    public static void main(String[] args) {
        SpringApplication.run(Energy20231120306Application.class, args);
    }

}
