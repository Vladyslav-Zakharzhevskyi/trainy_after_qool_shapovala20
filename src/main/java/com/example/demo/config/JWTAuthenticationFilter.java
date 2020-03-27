package com.example.demo.config;

import com.example.demo.service.AuthenticationUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class JWTAuthenticationFilter extends OncePerRequestFilter {

    private final static String AUTHENTICATION = "Authentication";

    private final JWTUtils jwtUtils;
    private final UserDetailsService userDetailsService;

    public JWTAuthenticationFilter(JWTUtils jwtUtils, UserDetailsService userDetailsService) {
        this.jwtUtils = jwtUtils;
        this.userDetailsService = userDetailsService;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain filterChain) throws ServletException, IOException {

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication != null) {
            filterChain.doFilter(request, response);
        }

        // Retrieve authentication from token
        UsernamePasswordAuthenticationToken authenticationFromToken = getAuthenticationFromToken(request);
        if (authenticationFromToken != null) {
            SecurityContextHolder.getContext().setAuthentication(authenticationFromToken);
        }

        filterChain.doFilter(request, response);
    }

    private UsernamePasswordAuthenticationToken getAuthenticationFromToken(HttpServletRequest request) {
        String header = request.getHeader(AUTHENTICATION);
        if (header == null) {
            return null;
        }

        if (!header.startsWith("Bearer") || !jwtUtils.checkJwtTokenCorrectness(header.substring(7))) {
            return null;
        }

        String userName = jwtUtils.getUserNameFromJwtToken(header.substring(7));

        UserDetails userDetails = userDetailsService.loadUserByUsername(userName);
        UsernamePasswordAuthenticationToken authenticationToken =
                new UsernamePasswordAuthenticationToken(userDetails, userDetails.getPassword(), userDetails.getAuthorities());

        return authenticationToken;
    }

}
