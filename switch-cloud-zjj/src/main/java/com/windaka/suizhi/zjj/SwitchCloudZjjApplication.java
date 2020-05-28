package com.windaka.suizhi.zjj;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@EnableDiscoveryClient
@SpringBootApplication
public class SwitchCloudZjjApplication {

    public static void main(String[] args) {
        SpringApplication.run(SwitchCloudZjjApplication.class, args);
    }
}
