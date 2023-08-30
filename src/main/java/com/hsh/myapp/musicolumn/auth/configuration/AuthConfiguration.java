package com.hsh.myapp.musicolumn.auth.configuration;

import com.hsh.myapp.musicolumn.auth.util.HashUtil;
import com.hsh.myapp.musicolumn.auth.util.JwtUtil;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AuthConfiguration {

    @Bean
    public HashUtil hashUtil() {
        return new HashUtil();
    }

    @Bean
    public JwtUtil jwtUtil() {
        return new JwtUtil();
    }
}
