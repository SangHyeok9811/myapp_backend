package com.hsh.myapp.musicolumn.auth;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AuthUser {
    private long joinNo;
    private String id;
    private String nickName;
}
