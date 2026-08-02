package com.yurii.pavlenko.hub;

import com.yurii.pavlenko.hub.config.TenantProperties;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class})
@EnableConfigurationProperties(TenantProperties.class)
public class MultiTenantHubServerApplication {

    public static void main(String[] args) {
        SpringApplication.run(MultiTenantHubServerApplication.class, args);
    }
}