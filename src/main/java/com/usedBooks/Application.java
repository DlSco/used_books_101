package com.usedBooks;


import tk.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.TimeZone;

@SpringBootApplication
@MapperScan("com.usedBooks.**.mapper")
public class Application {

    public static void main(String[] args) {

        SpringApplication.run(Application.class, args);

        TimeZone tz  =  TimeZone.getTimeZone( " Asia/Shanghai " );
        TimeZone.setDefault(tz);
    }

}

