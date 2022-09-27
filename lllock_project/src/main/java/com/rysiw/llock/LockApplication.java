package com.rysiw.llock;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

/**
 * @author Rysiw
 * @date 2022/9/27 10:42
 */
@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class})
public class LockApplication extends SpringBootServletInitializer {
    public static void main(String[] args) {
        SpringApplication.run(LockApplication.class, args);
    }
}
