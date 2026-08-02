package com.yurii.pavlenko.hub.config;

import com.zaxxer.hikari.HikariDataSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;
import java.util.HashMap;
import java.util.Map;

@Configuration
public class DataSourceConfig {

    @Bean
    public DataSource dataSource(TenantProperties tenantProperties) {
        DynamicRoutingDataSource routingDataSource = new DynamicRoutingDataSource();

        Map<Object, Object> targetDataSources = new HashMap<>();

        // Динамічно заповнюємо джерела даних на основі конфігурації з application.yml
        if (tenantProperties.getTenants() != null) {
            tenantProperties.getTenants().forEach((tenantId, config) -> {
                HikariDataSource ds = createDataSource(config.getUrl(), config.getUsername(), config.getPassword());
                ds.setPoolName("HikariPool-" + tenantId);
                targetDataSources.put(tenantId, ds);
            });
        }

        routingDataSource.setTargetDataSources(targetDataSources);

        // Встановлюємо default_db як дефолтне джерело для старту Hibernate
        Object defaultDataSource = targetDataSources.get("default_db");
        if (defaultDataSource == null && !targetDataSources.isEmpty()) {
            defaultDataSource = targetDataSources.values().iterator().next();
        }

        routingDataSource.setDefaultTargetDataSource(defaultDataSource);
        routingDataSource.afterPropertiesSet();

        return routingDataSource;
    }

    private HikariDataSource createDataSource(String url, String username, String password) {
        HikariDataSource dataSource = new HikariDataSource();
        dataSource.setJdbcUrl(url);
        dataSource.setUsername(username);
        dataSource.setPassword(password);
        dataSource.setMaximumPoolSize(5);
        return dataSource;
    }
}