package com.hsh.myapp.musicolumn.auth.request;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
public class SignUpRequest {
    private String id;
    private String password;
    private String nickName;
    private LocalDate birthdate;
    private String email;
}
