package com.ratelimiter.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RateLimitConfigResponse {

    private Long id;
    private String endpoint;
    private int limit;
    private int timeWindowInSeconds;
    private String userType;
}
