package com.example.demo.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.annotation.web.configurers.FormLoginConfigurer;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.logout.HttpStatusReturningLogoutSuccessHandler;
import org.springframework.security.web.servletapi.SecurityContextHolderAwareRequestFilter;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class WebSecurityConfigurer extends WebSecurityConfigurerAdapter {

    @Autowired
    private JWTUtils jwtUtils;
    @Autowired
    private JWTTokenAuthenticationSuccessHandler jwtTokenProvider;

    @Autowired
    private UserDetailsService authenticationUserDetailsService;

    @Autowired
    private BCryptPasswordEncoderCustom passwordEncoder;

    @Value("${authentication.method}")
    private String authenticationMethod;

    private final String defaultAuthenticationMethod = "basic";

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        if (null == authenticationMethod || authenticationMethod.isEmpty()) {
            authenticationMethod = defaultAuthenticationMethod;
        }

        http.csrf().disable();
        http
                .authorizeRequests()
                .antMatchers("/login").permitAll()
                .antMatchers("/img/favicon.png").permitAll()
                .antMatchers("/api/person/do-login").permitAll()
                .antMatchers("/api/person/register").permitAll()
                .antMatchers("/api/person/checkUserNameAvailability/*").permitAll()
                .antMatchers("/api/getApplicationSettings").permitAll()
                .anyRequest().authenticated();

        FormLoginConfigurer<HttpSecurity> loginConfigurer = http
                .formLogin()
                .loginPage("/login")
                .loginProcessingUrl("/api/person/do-login");

        if (authenticationMethod.equals("basic")) {

            loginConfigurer
                    .successHandler((request, response, authentication) -> { /*investigate nothing*/ })
                    .and()
                    .httpBasic();

        } else if (authenticationMethod.equals("jwt")) {

            loginConfigurer
                    .successHandler(jwtTokenProvider)
                    .and()
                    .addFilterBefore(new JWTAuthenticationFilter(jwtUtils, authenticationUserDetailsService), SecurityContextHolderAwareRequestFilter.class);
        }

        http
                .logout()
                .logoutSuccessHandler((new HttpStatusReturningLogoutSuccessHandler(HttpStatus.OK)))
                .invalidateHttpSession(true)
                .and()
                    .requestCache().disable();
    }


    @Override
    protected void configure(AuthenticationManagerBuilder auth) {
        auth.authenticationProvider(getAuthenticationProvider());
    }

    @Bean
    public DaoAuthenticationProvider getAuthenticationProvider() {
        DaoAuthenticationProvider daoAuthenticationProvider = new DaoAuthenticationProvider();
        daoAuthenticationProvider.setUserDetailsService(authenticationUserDetailsService);
        daoAuthenticationProvider.setPasswordEncoder(passwordEncoder);
        return daoAuthenticationProvider;
    }

}
