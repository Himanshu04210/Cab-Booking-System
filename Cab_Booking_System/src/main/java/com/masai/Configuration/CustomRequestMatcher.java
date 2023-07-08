package com.masai.Configuration;

import org.springframework.security.web.util.matcher.RequestMatcher;

import jakarta.servlet.http.HttpServletRequest;

public class CustomRequestMatcher implements RequestMatcher {

    private final String[] patterns;

    public CustomRequestMatcher(String... patterns) {
        this.patterns = patterns;
    }

    @Override
    public boolean matches(HttpServletRequest request) {
        String requestUri = request.getRequestURI();
        for (String pattern : patterns) {
            if (requestUri.matches(pattern)) {
                return true;
            }
        }
        return false;
    }

}
