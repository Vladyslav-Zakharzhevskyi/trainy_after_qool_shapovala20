package com.example.demo.config;

import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
@Order(Ordered.HIGHEST_PRECEDENCE)
public class MyCorsFilter extends OncePerRequestFilter {

    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain filterChain) throws ServletException, IOException {

        response.addHeader("Access-Control-Allow-Origin", determineOrigin(request));
        response.addHeader("Access-Control-Allow-Methods", "GET, POST, PUT, OPTIONS, DELETE");
        response.addHeader("Access-Control-Allow-Headers", "Authentication, Authorization, Content-Type");
        response.addHeader("Access-Control-Allow-Credentials", "false");
        response.addHeader("Access-Control-Expose-Headers", "Authentication, Authorization");
        response.addHeader("Access-Control-Max-Age", "3600");

        if (!request.getMethod().equals("OPTIONS")) {
            filterChain.doFilter(request, response);
        }

    }

    private String determineOrigin(HttpServletRequest request) {
        String origin = request.getHeader("origin");
        if (!StringUtils.isEmpty(origin)) {
            return origin;
        }

        String referer = request.getHeader("referer");
        if (!StringUtils.isEmpty(origin)) {
            return referer;
        }

        return "*";
    }
}
