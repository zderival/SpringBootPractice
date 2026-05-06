package com.zderival.springbootpractice;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthSerivce {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtUtil jwtUil;

    public AuthSerivce(UserRepository userRepository, PasswordEncoder passwordEncoder, JwtUtil jwtUil){
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.jwtUil = jwtUil;
    }

    public void register(String username, String password){
        User user = new User();
        user.setUsername(username);
        user.setPassword(passwordEncoder.encode(password));
        userRepository.save(user);
    }

    public String login(String username, String password){
        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new RuntimeException("User not found"));
        if(!passwordEncoder.matches(password, user.getPassword())){
            throw new RuntimeException("Invalid password");
        }
        return jwtUil.generateToken(username);
    }
}
