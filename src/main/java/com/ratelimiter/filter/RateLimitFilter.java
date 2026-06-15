package com.ratelimiter.filter;


import com.ratelimiter.service.RateLimitService;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.List;

@Component
@RequiredArgsConstructor
public class RateLimitFilter extends OncePerRequestFilter {

    private static final Logger log = LoggerFactory.getLogger(RateLimitFilter.class);

    private final RateLimitService rateLimitService;

    private final List<String> whitelist = List.of(
            "/swagger-ui",
            "/v3/api-docs",
            "/actuator",
            "/rate-limit/config"
    );

    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain filterChain)
            throws ServletException, IOException {

        if ("OPTIONS".equalsIgnoreCase(request.getMethod())) {
            filterChain.doFilter(request, response);
            return;
        }

        String uri = request.getRequestURI();

        if (isWhitelisted(uri)) {
            filterChain.doFilter(request, response);
            return;
        }

        String apiKey = request.getHeader("X-API-KEY");
        String userType = request.getHeader("X-USER-TYPE");

        if (apiKey == null || apiKey.isBlank()) {
            apiKey = request.getRemoteAddr();
        }

        if (userType == null || userType.isBlank()) {
            userType = "FREE";
        }

        RateLimitService.RateLimitResult result =
                rateLimitService.isAllowed(apiKey, uri, userType);

        response.setHeader("X-RateLimit-Limit", String.valueOf(result.limit()));
        response.setHeader("X-RateLimit-Remaining", String.valueOf(result.remaining()));
        response.setHeader("X-RateLimit-Reset", String.valueOf(result.resetTime()));

        if (!result.allowed()) {
            log.warn("Rate limit exceeded for API Key: {}, Endpoint: {}", apiKey, uri);

            response.setStatus(429);
            response.setContentType("application/json");
            response.getWriter().write("""
                    {
                      "status": 429,
                      "message": "Too Many Requests. Rate limit exceeded."
                    }
                    """);
            return;
        }

        filterChain.doFilter(request, response);
    }

    private boolean isWhitelisted(String uri) {
        return whitelist.stream().anyMatch(uri::startsWith);
    }
}
