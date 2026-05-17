package com.zderival.springbootpractice;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

// @Configuration - tells Spring Boot this class contains app-wide settings
// Unlike @Service or @Controller which handle specific tasks,
// @Configuration is purely for setup and configuration of the whole application
@Configuration
public class SecurityConfig {
    // @Bean - tells Spring Boot to manage this object and make it available anywhere in the app
    // When AuthService asks for a PasswordEncoder, Spring Boot finds this method
    // and hands it a BCryptPasswordEncoder automatically
    @Bean
    public PasswordEncoder passwordEncoder(){
        // BCrypt is the industry standard for hashing passwords
        // It takes a plain text password like "password123" and turns it into
        // a scrambled string like "$2a$10$xyz..." that can never be reversed
        return new BCryptPasswordEncoder();
    }

    // @Bean — same as above, Spring Boot manages this and uses it to configure security rules
    // HttpSecurity is Spring Security's tool for defining the security rulebook for the app
    // JwtFilter is injected here so we can plug it into the security chain
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http, JwtFilter jwtFilter) throws Exception{

        // CSRF (Cross Site Request Forgery) is a type of security attack
        // Spring Security protects against it by default but it interferes with REST API requests
        // Since we're using JWT for security instead, we safely disable CSRF here
        http.csrf(csrf -> csrf.disable())

                // Define the rules for who can access which endpoints
                .authorizeHttpRequests(auth ->

                        // Anyone can hit any URL starting with /api/v1/auth/ without being logged in
                        // This covers register and login — users need these to be open to get a token
                        auth.requestMatchers("/api/v1/auth/**").permitAll()

                                // Every other endpoint in the app requires the user to be authenticated
                                // meaning they must include a valid JWT token in their request
                                .anyRequest().authenticated())

                // Plug our JwtFilter into Spring Security's filter chain
                // "addFilterBefore" means run JwtFilter BEFORE Spring Security's default login filter
                // This way every request gets checked for a JWT token first
                .addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class);

        // Build and return the final security configuration
        return http.build();
    }
}