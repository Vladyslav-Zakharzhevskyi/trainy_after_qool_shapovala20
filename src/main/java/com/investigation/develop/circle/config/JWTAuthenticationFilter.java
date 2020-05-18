package com.investigation.develop.circle.config;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
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

        Authentication originAuthentication = SecurityContextHolder.getContext().getAuthentication();
        if (originAuthentication != null) {
            filterChain.doFilter(request, response);
        }

        // Retrieve authentication from token
        boolean authenticationJWT = isAuthenticationJWT(request);
        if (authenticationJWT) {
            UsernamePasswordAuthenticationToken authentication = getAuthenticationFromToken(request);
            if (authentication != null) {
                SecurityContextHolder.getContext().setAuthentication(authentication);
            }
        }


        filterChain.doFilter(request, response);
    }

    private boolean isAuthenticationJWT(HttpServletRequest request) {
        String header = request.getHeader(AUTHENTICATION);
        if (header == null) {
            return false;
        }

        if (!header.startsWith("Bearer") || !jwtUtils.checkJwtTokenCorrectness(header.substring(7))) {
            return false;
        }

        return true;
    }

    private UsernamePasswordAuthenticationToken getAuthenticationFromToken(HttpServletRequest request) {
        String header = request.getHeader(AUTHENTICATION);

        String userName = jwtUtils.getUserNameFromJwtToken(header.substring(7));

        UserDetails userDetails = userDetailsService.loadUserByUsername(userName);

        return new UsernamePasswordAuthenticationToken(userDetails, userDetails.getPassword(), userDetails.getAuthorities());
    }

}
