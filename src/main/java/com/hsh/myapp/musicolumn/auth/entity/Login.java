package com.hsh.myapp.musicolumn.auth.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
public class Login {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long joinNo;

    @Column(unique = true)
    private String id;

    @Column(length = 500)
    private String secretPassword;

    private long userNo;
}
