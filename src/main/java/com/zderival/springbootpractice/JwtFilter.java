package com.zderival.springbootpractice;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.List;

// @Component - tells Spring Boot "manage this class for me"
@Component
// OncePerRequestFilter is a builtin Spring class that guarantees this filter
// runs exactly once per request — we extend it so we inherit that guarantee
public class JwtFilter extends OncePerRequestFilter {

    // JwtUtil is the class that handles everything JWT related
    // (generating tokens, reading tokens) — we need it here to read the token from requests

    private final JwtUtil jwtUtil;
    // Spring Boot automatically provides JwtUtil here because it's marked @Component
    // We never manually create it — Spring Boot hands it to us
    public JwtFilter(JwtUtil jwtUtil){
        this.jwtUtil = jwtUtil;
    }

    // This method is the actual filter logic — it runs on EVERY request that hits the app
    // It is a security checkpoint every request (POST, PUT, GET, DELETE) must pass through
    // before it ever reaches a Controller
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {

        // Every HTTP request has "headers" — small pieces of metadata attached to it
        // The Authorization header is where the JWT token lives
        // It looks like: Authorization: Bearer eyJhbGci...
        String authHeader = request.getHeader("Authorization");

        // Before doing anything, check:
        // 1. Does the Authorization header actually exist?
        // 2. Does it start with "Bearer "? (this is the standard format for JWT tokens)
        // If either check fails, we skip the token logic entirely
        if(authHeader != null && authHeader.startsWith("Bearer ")){
            // "Bearer " is 7 characters — we cut it off to get just the raw token
            // Before: "Bearer eyJhbGci..."
            // After:  "eyJhbGci..."
            String token = authHeader.substring(7);

            // Ask JwtUtil to crack open the token and tell us who it belongs to
            // The username was baked into the token when the user logged in
            String username = jwtUtil.extractUsername(token);

            // Two checks before marking the user as authenticated:
            // 1. Did we actually get a username out of the token?
            // 2. Is Spring Security not already aware of this user for this request?
            // (We don't want to authenticate twice)
            if(username != null && SecurityContextHolder.getContext().getAuthentication() == null){
                // SecurityContextHolder is Spring Security's memory for the current request
                // It holds onto "who is making this request"

                // UsernamePasswordAuthenticationToken is Spring Security's way of representing an authenticated user
                // null = we don't need the password again (token already proved who they are)
                // List.of() = no specific roles or permissions assigned yet
                UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken
                        (username, null, List.of());

                // Store the authenticated user in Spring Security's memory
                // After this line, Spring Security knows who is making this request
                // and will allow them through to protected endpoints
                SecurityContextHolder.getContext().setAuthentication(authentication);
            }
        }

        // This line passes the request forward to the next step
        // (either another filter or the actual Controller)
        // If we forget this line, every request gets stuck here and never reaches the app
        filterChain.doFilter(request, response);
    }

    // A getter for JwtUtil in case anything outside this class needs access to it
    // In this project it's not really used externally — JwtUtil is only needed inside this filter
    private JwtUtil getJwtUtil() {
        return jwtUtil;
    }
}