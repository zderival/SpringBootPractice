package com.zderival.springbootpractice;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
public class SecurityConfig {

    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

    // This method gives the guidelines to who can enter the app.
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http, JwtFilter jwtFilter) throws Exception{
        // This disables a security attack and keeps from interfering with API requests
        http.csrf(csrf -> csrf.disable())
                // Defines who is allowed to make specific http requests to specific endpoints
                .authorizeHttpRequests(auth ->
                        // any Url with /api/v1/auth is allowed to access, no login required
                        auth.requestMatchers("/api/v1/auth/**").permitAll()
                                //Any other endpoint must log in to access
                                .anyRequest().authenticated())
                .addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class);
        return http.build();
    }
}
