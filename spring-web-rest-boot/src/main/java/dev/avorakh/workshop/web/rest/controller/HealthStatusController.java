package dev.avorakh.workshop.web.rest.controller;

import dev.avorakh.workshop.web.rest.resource.HealthCheckResource;
import lombok.extern.slf4j.Slf4j;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
public class HealthStatusController {
    
    @Value("${spring.application.name}")
    @NotNull String applicationName;

    @GetMapping("/status")
    public @NotNull ResponseEntity<?> getHealthStatus() {

        log.info("Health status - ok");

        return ResponseEntity.ok(new HealthCheckResource(applicationName));
    }

}
