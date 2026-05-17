package com.zderival.springbootpractice;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

// @RestController tells Spring Boot this class is a Controller
// It handles all incoming HTTP requests and sends responses back to the user
// This is the "front door" of the auth side of the app
@RestController

// All endpoints in this class start with /api/v1/auth
// So register = /api/v1/auth/register and login = /api/v1/auth/login
@RequestMapping("api/v1/auth")
public class AuthController {

    // AuthService handles the actual logic for registering and logging in
    // The Controller doesn't do the work itself — it just receives the request
    // and passes it to the Service (separation of concerns)
    private final AuthSerivce authSerivce;

    // Spring Boot automatically injects AuthService here through the constructor
    // We never manually create it — Spring Boot handles that
    public AuthController(AuthSerivce authSerivce){
        this.authSerivce = authSerivce;
    }

    // Listens for POST requests at /api/v1/auth/register
    // This endpoint is open to everyone — no token required (defined in SecurityConfig)
    @PostMapping("/register")
    public void register(
            // @RequestBody tells Spring Boot to read the JSON body of the request
            // and convert it into a User object automatically
            // e.g. { "username": "zach", "password": "password123" } → User object
            @RequestBody User user){

        // Pass the username and password to AuthService to handle registration
        // AuthService will hash the password and save the user to the database
        authSerivce.register(user.getUsername(), user.getPassword());
    }

    // Listens for POST requests at /api/v1/auth/login
    // Returns a String — that String is the JWT token sent back to the user
    // This endpoint is also open to everyone — you need to be able to login without a token
    @PostMapping("/login")
    public String login(
            // Same as register — reads the JSON body and converts it to a User object
            @RequestBody User user){

        // Pass the username and password to AuthService to verify credentials
        // If correct, AuthService returns a JWT token which gets sent back to the user
        // That token becomes the user's identity for all future protected requests
        return authSerivce.login(user.getUsername(), user.getPassword());
    }
}