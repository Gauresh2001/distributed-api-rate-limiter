package com.ratelimiter.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "rate_limit_rules")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class RateLimitRule {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String endpoint;

    @Column(name = "request_limit")
    private int limit;

    private int timeWindowInSeconds;

    private String userType;
}
