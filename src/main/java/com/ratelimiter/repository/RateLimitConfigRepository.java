package com.ratelimiter.repository;

import com.ratelimiter.entity.RateLimitRule;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RateLimitConfigRepository extends JpaRepository<RateLimitRule, Long> {

    Optional<RateLimitRule> findFirstByEndpointAndUserTypeOrderByIdDesc(String endpoint, String userType);

}
