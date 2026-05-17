package com.zderival.springbootpractice;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.util.Date;

// @Component tells Spring Boot to manage this class
// This allows it to be injected into JwtFilter and AuthService
@Component
public class JwtUtil {
    // SecretKey is the private key used to sign and verify JWT tokens
    // Think of it like a stamp — only your app knows this stamp
    // If someone tampers with the token, the stamp won't match and it gets rejected
    private final SecretKey key;

    // @Value("${jwt.secret}") tells Spring Boot to go read jwt.secret from application.properties
    // and inject it here as a String — this keeps the secret out of the code itself
    // Keys.hmacShaKeyFor() converts that plain text secret into a proper cryptographic key
    public JwtUtil(@Value("${jwt.secret}") String secret){
        this.key = Keys.hmacShaKeyFor(secret.getBytes());
    }

    // Generates a JWT token for a given username after they successfully log in
    // This token is what gets sent back to the user — it becomes their identity for future requests
    public String generateToken(String username){
        return Jwts.builder()
                // Stores the username inside the token so we can read it back later
                .subject(username)
                // Records the exact time the token was created
                .issuedAt(new Date())
                // Sets when the token expires — 86400000 milliseconds = 24 hours from now
                // After 24 hours the token is invalid and the user must log in again
                .expiration(new Date(System.currentTimeMillis() + 86400000))
                // Signs the token with our secret key — this is what prevents tampering
                .signWith(key)
                // Builds and converts everything into the final compact JWT string
                // e.g. "eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJ6YWNoI..."
                .compact();
    }

    // Cracks open a JWT token and reads the username stored inside it
    // This is called by JwtFilter on every request to figure out who is making it
    public String extractUsername(String token){
        return Jwts.parser()
                // Tell the parser which secret key to use to verify the token signature
                // If the token was tampered with, this will fail and throw an exception
                .verifyWith(key)
                // Build the parser
                .build()
                // Parse the token and verify it's valid and not expired
                .parseSignedClaims(token)
                // Get the body of the token (where the username and expiration are stored)
                .getPayload()
                // Pull out the username (what we stored as "subject" when generating the token)
                .getSubject();
    }
}