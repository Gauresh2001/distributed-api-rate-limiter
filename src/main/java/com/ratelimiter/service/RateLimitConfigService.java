package com.ratelimiter.service;

import com.ratelimiter.dto.RateLimitConfigRequest;
import com.ratelimiter.dto.RateLimitConfigResponse;
import com.ratelimiter.entity.RateLimitRule;
import com.ratelimiter.repository.RateLimitConfigRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class RateLimitConfigService {

    private final RateLimitConfigRepository repository;

    public RateLimitConfigResponse createRule(RateLimitConfigRequest request) {

        RateLimitRule rule = new RateLimitRule();

        rule.setEndpoint(request.getEndpoint());
        rule.setLimit(request.getLimit());
        rule.setTimeWindowInSeconds(request.getTimeWindowInSeconds());
        rule.setUserType(request.getUserType());

        RateLimitRule savedRule = repository.save(rule);

        return mapToResponse(savedRule);
    }

    public List<RateLimitConfigResponse> getAllRules() {
        return repository.findAll()
                .stream()
                .map(this::mapToResponse)
                .toList();
    }

    public RateLimitConfigResponse updateRule(Long id, RateLimitConfigRequest request) {

        RateLimitRule rule = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Rate limit rule not found"));

        rule.setEndpoint(request.getEndpoint());
        rule.setLimit(request.getLimit());
        rule.setTimeWindowInSeconds(request.getTimeWindowInSeconds());
        rule.setUserType(request.getUserType());

        RateLimitRule updatedRule = repository.save(rule);

        return mapToResponse(updatedRule);
    }

    public String deleteRule(Long id) {
        repository.deleteById(id);
        return "Rate limit rule deleted successfully";
    }

    private RateLimitConfigResponse mapToResponse(RateLimitRule rule) {
        return new RateLimitConfigResponse(
                rule.getId(),
                rule.getEndpoint(),
                rule.getLimit(),
                rule.getTimeWindowInSeconds(),
                rule.getUserType()
        );
    }
}
