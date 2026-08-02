/**
 * @file TenantContext.java
 * @description Utility class using ThreadLocal to manage the current tenant identifier for the active execution thread.
 */

package com.yurii.pavlenko.hub.tenant;

public class TenantContext {
    private static final ThreadLocal<String> CURRENT_TENANT = new ThreadLocal<>();

    public static void setCurrentTenant(String tenantId) {
        CURRENT_TENANT.set(tenantId);
    }

    public static String getCurrentTenant() {
        return CURRENT_TENANT.get();
    }

    public static void clear() {
        CURRENT_TENANT.remove();
    }
}