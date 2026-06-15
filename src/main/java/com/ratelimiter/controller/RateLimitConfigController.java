package com.ratelimiter.controller;


import com.ratelimiter.dto.RateLimitConfigRequest;
import com.ratelimiter.dto.RateLimitConfigResponse;
import com.ratelimiter.service.RateLimitConfigService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/rate-limit/config")
@RequiredArgsConstructor
public class RateLimitConfigController {

    private final RateLimitConfigService configService;

    @GetMapping
    public List<RateLimitConfigResponse> getAllRules() {
        return configService.getAllRules();
    }

    @PostMapping
    public RateLimitConfigResponse createRule(@Valid @RequestBody RateLimitConfigRequest request) {
        return configService.createRule(request);
    }

    @PutMapping("/{id}")
    public RateLimitConfigResponse updateRule(@PathVariable Long id,
                                               @Valid @RequestBody RateLimitConfigRequest request) {
        return configService.updateRule(id, request);
    }

    @DeleteMapping("/{id}")
    public String deleteRule(@PathVariable Long id) {
        return configService.deleteRule(id);
    }

}
