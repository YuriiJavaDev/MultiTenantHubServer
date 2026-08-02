/**
 * @file TenantTestController.java
 * @description REST controller to verify active tenant context and dynamic database switching.
 */

package com.yurii.pavlenko.hub.web.controller;

import com.yurii.pavlenko.hub.tenant.TenantContext;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/tenant")
public class TenantTestController {

    @GetMapping("/status")
    public ResponseEntity<String> getActiveTenantStatus() {
        String activeTenant = TenantContext.getCurrentTenant();
        return ResponseEntity.ok("Successfully connected. Current active database tenant context is: " + activeTenant);
    }
}