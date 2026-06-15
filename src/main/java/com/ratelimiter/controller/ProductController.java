package com.ratelimiter.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController
public class ProductController {

    @GetMapping("/api/products")
    public List<Map<String, Object>> getProducts() {
        return List.of(
                Map.of("id", 1, "name", "Laptop", "price", 55000),
                Map.of("id", 2, "name", "Mobile", "price", 25000),
                Map.of("id", 3, "name", "Headphones", "price", 3000)
        );
    }
}
