package com.hongv.koi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.Configuration;

/**
 * @author hongweixu
 */
@SpringBootApplication
@Configuration
@EnableEurekaClient
public class KoiApiApplication {

    public static void main(String[] args) {
        SpringApplication.run(KoiApiApplication.class, args);
    }
}
