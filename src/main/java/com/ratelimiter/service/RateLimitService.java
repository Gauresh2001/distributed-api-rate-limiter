package com.ratelimiter.service;

import com.ratelimiter.entity.RateLimitRule;
import com.ratelimiter.repository.RateLimitConfigRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.RedisConnectionFailureException;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.Optional;
import java.util.concurrent.TimeUnit;

@Service
@RequiredArgsConstructor
public class RateLimitService {

    private final StringRedisTemplate redisTemplate;
    private final RateLimitConfigRepository configRepository;

    public RateLimitResult isAllowed(String clientId, String endpoint, String userType) {

        Optional<RateLimitRule> optionalRule =
                configRepository.findFirstByEndpointAndUserTypeOrderByIdDesc(endpoint, userType);

        int limit = optionalRule.map(RateLimitRule::getLimit).orElse(100);
        int window = optionalRule.map(RateLimitRule::getTimeWindowInSeconds).orElse(60);

        String key = "rate_limit:user:" + clientId + ":" + endpoint + ":" + userType;

        try {
            Long count = redisTemplate.opsForValue().increment(key);

            if (count != null && count == 1) {
                redisTemplate.expire(key, window, TimeUnit.SECONDS);
            }

            Long ttl = redisTemplate.getExpire(key, TimeUnit.SECONDS);

            int remaining = Math.max(0, limit - count.intValue());
            long resetTime = Instant.now().getEpochSecond() + Math.max(ttl, 0);

            boolean allowed = count <= limit;

            return new RateLimitResult(allowed, limit, remaining, resetTime);

        } catch (RedisConnectionFailureException e) {
            return new RateLimitResult(true, limit, limit, Instant.now().getEpochSecond() + window);
        }
    }

    public record RateLimitResult(
            boolean allowed,
            int limit,
            int remaining,
            long resetTime
    ) {
    }
}
