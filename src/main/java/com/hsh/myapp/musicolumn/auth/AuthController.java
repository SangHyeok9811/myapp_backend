package com.hsh.myapp.musicolumn.auth;

import com.hsh.myapp.musicolumn.auth.entity.LoginRepository;
import com.hsh.myapp.musicolumn.auth.entity.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class AuthController {
    @Autowired
    private LoginRepository loginRepo;

    @Autowired
    private UserRepository userRepo;

//    @Autowired

}
