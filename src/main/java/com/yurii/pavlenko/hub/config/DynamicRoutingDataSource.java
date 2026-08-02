/**
 * @file DynamicRoutingDataSource.java
 * @description Routing DataSource implementation to switch databases dynamically based on the current tenant context.
 */

package com.yurii.pavlenko.hub.config;

import com.yurii.pavlenko.hub.tenant.TenantContext;
import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;

public class DynamicRoutingDataSource extends AbstractRoutingDataSource {

    @Override
    protected Object determineCurrentLookupKey() {
        return TenantContext.getCurrentTenant();
    }
}