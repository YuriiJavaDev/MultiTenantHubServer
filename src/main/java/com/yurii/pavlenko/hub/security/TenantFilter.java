/**
 * @file TenantFilter.java
 * @description HTTP filter intercepting requests to extract the X-Tenant-ID header and populate the TenantContext.
 */

package com.yurii.pavlenko.hub.security;

import com.yurii.pavlenko.hub.tenant.TenantContext;
import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class TenantFilter implements Filter {

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {

        HttpServletRequest httpRequest = (HttpServletRequest) request;
        String tenantId = httpRequest.getHeader("X-Tenant-ID");

        try {
            if (tenantId != null && !tenantId.isEmpty()) {
                TenantContext.setCurrentTenant(tenantId);
            } else {
                TenantContext.setCurrentTenant("tasks_db"); // Дефолтне значення за відсутності заголовка
            }
            chain.doFilter(request, response);
        } finally {
            TenantContext.clear(); // Очищення пам'яті потоку після завершення запиту
        }
    }
}