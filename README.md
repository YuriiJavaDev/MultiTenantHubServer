# Standard JPA Server Initialization.
### Description: Implementation of a multi-tenant backend server using Spring Boot and Dynamic Routing Data Source to manage multiple PostgreSQL databases (default_db, tasks_db, coffee_db) at runtime.

#### Requirements Compliance: Successfully resolved Hibernate initialization conflicts and metadata access issues for dynamic routing data sources.

#### Architectural Stack: Java 23, Spring Boot 3.3.0, Spring Data JPA, Hibernate, PostgreSQL, HikariCP, and custom routing filters.

#### Implementation Details: Configured DynamicRoutingDataSource extending AbstractRoutingDataSource, managed thread-local execution context via TenantContext, implemented TenantFilter for header-based tenant resolution, and set up DataSourceConfig to initialize database connection pools dynamically.

#### Expected result: The server successfully boots up without database connection errors on startup and routes incoming HTTP requests to the designated tenant database based on request headers.

### Project Structure:

    com.yurii.pavlenko.hub\
    ├── config
    │   ├── DataSourceConfig.java
    │   ├── DynamicRoutingDataSource.java
    │   └── TenantProperties.java
    ├── security
    │   └── TenantFilter.java
    ├── tenant
    │   ├── TenantContext.java
    │   └── service
    │       └── TenantManagementService.java
    ├── web.controller
    │   └── TenantTestController.java
    ├── MultiTenantHubServerApplication.java
    │
    ├── logger.txt
    ├── tasks.json
    ├── pom.xml
    ├── LICENSE
    ├── TASK.md
    ├── THEORY.md
    └── README.md

## 💻 Code Example

```java
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
```

## ⚖️ License
This project is licensed under the **MIT License**.

Copyright (c) 2026 Yurii Pavlenko

Permission is hereby granted, free of charge, to any person obtaining a copy of this software and associated documentation files...

License: [MIT](LICENSE)
