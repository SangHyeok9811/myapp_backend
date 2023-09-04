package com.hsh.myapp.musicolumn.auth;

import com.hsh.myapp.musicolumn.auth.entity.Login;
import com.hsh.myapp.musicolumn.auth.entity.LoginRepository;
import com.hsh.myapp.musicolumn.auth.entity.User;
import com.hsh.myapp.musicolumn.auth.entity.UserRepository;
import com.hsh.myapp.musicolumn.auth.request.SignUpRequest;
import com.hsh.myapp.musicolumn.auth.util.HashUtil;
import com.hsh.myapp.musicolumn.auth.util.JwtUtil;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/auth")
public class AuthController {
    @Autowired
    private LoginRepository loginRepo;

    @Autowired
    private UserRepository userRepo;

    @Autowired
    private AuthService service;

    @Autowired
    private HashUtil hash;

    @Autowired
    private JwtUtil jwt;

    @GetMapping(value = "/logins")
    public List<Login> getLogins() {
        return loginRepo.findAll();
    }

    @PostMapping(value = "/signup")
    public ResponseEntity signUp(@RequestBody SignUpRequest req){
        System.out.println(req);

        long userNo = service.createIdentity(req);

        return ResponseEntity.status(HttpStatus.CREATED).body(userNo);
    }

    @PostMapping(value = "/signin")
    public ResponseEntity signIn(@RequestParam String id, @RequestParam String password, HttpServletResponse res) {
        System.out.println(id);
        System.out.println(password);
        System.out.println(res);

        Optional<Login> login = loginRepo.findById(id);

        if(!login.isPresent()){
            return ResponseEntity
                    .status(HttpStatus.FOUND)
                    .location(ServletUriComponentsBuilder
                            .fromHttpUrl("http://localhost:5500/login.html?err=Unauthorized")
                            .build().toUri())
                    .build();
        }

        Login l = login.get();
        Optional<User> user = userRepo.findByLogin_JoinNo(l.getJoinNo());

        if(!user.isPresent()) {
            return ResponseEntity
                    .status(HttpStatus.FOUND)
                    .location(ServletUriComponentsBuilder
                            .fromHttpUrl("http://localhost:5500?err=Conflict")
                            .build().toUri())
                    .build();
        }
        String token = jwt.createToken(
                l.getJoinNo(),l.getId(),
                user.get().getNickName(),
                user.get().getBirthdate(),
                user.get().getEmail(),
                user.get().getUserImage());
        System.out.println("토큰: " + token);

        Cookie cookie = new Cookie("token",token);
        cookie.setPath("/");
        cookie.setMaxAge((int)(jwt.TOKEN_TIME / 1000L));
        cookie.setDomain("localhost");

        res.addCookie(cookie);

        return ResponseEntity.status(HttpStatus.FOUND)
                .location(ServletUriComponentsBuilder
                        .fromHttpUrl("http://localhost:5500?err=Conflict")
                        .build().toUri())
                .build();
    }
}
