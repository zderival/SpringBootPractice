package com.zderival.springbootpractice;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/v1/auth")
public class AuthController {
    private final AuthSerivce authSerivce;

    public AuthController(AuthSerivce authSerivce){
        this.authSerivce = authSerivce;
    }
    @PostMapping("/register")
    public void register(@RequestBody User user){
        authSerivce.register(user.getUsername(),user.getPassword());
    }

    @PostMapping("/login")
    public String login(@RequestBody User user){
        return authSerivce.login(user.getUsername(),user.getPassword());
    }
}
