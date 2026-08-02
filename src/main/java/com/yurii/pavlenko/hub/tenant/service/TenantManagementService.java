package com.yurii.pavlenko.hub.tenant.service;

import com.yurii.pavlenko.hub.config.DynamicRoutingDataSource;
import com.zaxxer.hikari.HikariDataSource;
import org.springframework.stereotype.Service;

import javax.sql.DataSource;
import java.util.HashMap;
import java.util.Map;

@Service
public class TenantManagementService {

    private final DynamicRoutingDataSource routingDataSource;
    // Зберігаємо мапу, куди будемо додавати нові тенанти в рантаймі
    private final Map<Object, Object> targetDataSources = new HashMap<>();

    public TenantManagementService(DataSource dataSource) {
        this.routingDataSource = (DynamicRoutingDataSource) dataSource;
    }

    public void registerNewTenant(String tenantId, String jdbcUrl, String username, String password) {
        HikariDataSource newTenantDataSource = createHikariDataSource(jdbcUrl, username, password);
        newTenantDataSource.setPoolName("HikariPool-" + tenantId);

        // Додаємо новий тенант до загальної мапи
        targetDataSources.put(tenantId, newTenantDataSource);

        // Оновлюємо маршрутизатор у рантаймі
        routingDataSource.setTargetDataSources(targetDataSources);
        routingDataSource.afterPropertiesSet();
    }

    private HikariDataSource createHikariDataSource(String url, String username, String password) {
        HikariDataSource dataSource = new HikariDataSource();
        dataSource.setJdbcUrl(url);
        dataSource.setUsername(username);
        dataSource.setPassword(password);
        dataSource.setMaximumPoolSize(5);
        return dataSource;
    }
}