package com.hsh.myapp.musicolumn.auth;

import com.hsh.myapp.musicolumn.auth.entity.Login;
import com.hsh.myapp.musicolumn.auth.entity.LoginRepository;
import com.hsh.myapp.musicolumn.auth.entity.User;
import com.hsh.myapp.musicolumn.auth.entity.UserRepository;
import com.hsh.myapp.musicolumn.auth.request.SignUpRequest;
import com.hsh.myapp.musicolumn.auth.util.HashUtil;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AuthService {

    private LoginRepository loginRepo;
    private UserRepository userRepo;

    @Autowired
    private HashUtil hash;

    @Autowired
    public AuthService(LoginRepository loginRepo, UserRepository userRepo){
        this.loginRepo = loginRepo;
        this.userRepo = userRepo;
    }

    @Transactional
    public long createIdentity(SignUpRequest req) {
        Login toSaveLogin =
                Login.builder()
                        .id(req.getId())
                        .secretPassword(hash.createHash(req.getPassword())).build();
        Login savedLogin = loginRepo.save(toSaveLogin);

        User toSaveUser =
                User.builder()
                        .nickName(req.getNickName())
                        .email(req.getEmail())
                        .birthdate(req.getBirthdate())
                        .login(savedLogin)
                        .build();
        long userNo = userRepo.save(toSaveUser).getUserNo();

        savedLogin.setUserNo(userNo);
        loginRepo.save(savedLogin);

        return userNo;
    }

}
