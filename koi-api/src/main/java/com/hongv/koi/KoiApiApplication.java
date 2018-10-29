package com.hongv.koi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Configuration;

/**
 * @author hongweixu
 */
@SpringBootApplication
@Configuration
public class KoiApiApplication {

    public static void main(String[] args) {
        SpringApplication.run(KoiApiApplication.class, args);
    }
}
