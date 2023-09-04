package com.hsh.myapp.musicolumn.auth;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AuthUser {
    private long userNumber;
    private String id;
    private String nickName;
    private String email;
    private LocalDate birthdate;
    private String image;
}
