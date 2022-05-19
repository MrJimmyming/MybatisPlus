package cn.wolfcode;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import javax.swing.*;

@SpringBootApplication
@MapperScan("cn.wolfcode.mapper")
public class MybatisPlus {
    public static void main(String[] args) {
        SpringApplication.run(MybatisPlus.class,args);
    }
}
