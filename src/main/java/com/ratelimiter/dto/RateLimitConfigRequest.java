package com.ratelimiter.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class RateLimitConfigRequest {

    @NotBlank(message = "Endpoint is required")
    private String endpoint;

    @Min(value = 1, message = "Limit must be at least 1")
    private int limit;

    @Min(value = 1, message = "Time window must be at least 1 second")
    private int timeWindowInSeconds;

    @NotBlank(message = "User type is required")
    private String userType;
}
