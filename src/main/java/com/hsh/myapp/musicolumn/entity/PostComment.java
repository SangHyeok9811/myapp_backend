package com.hsh.myapp.musicolumn.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
public class PostComment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long commentNo;

    private String commentContent;

    private String ownerNickName;

    @ManyToOne
    private Post post;

    @ManyToOne // Many-to-One 관계 추가
    private User user; // 댓글을 작성한 유저
}