package com.ratelimiter.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
public class OrderController {

    @PostMapping("/api/orders")
    public Map<String, Object> createOrder() {
        return Map.of(
                "message", "Order created successfully",
                "status", "SUCCESS"
        );
    }
}
