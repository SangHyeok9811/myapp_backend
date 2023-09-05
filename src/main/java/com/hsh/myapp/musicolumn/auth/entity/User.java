package com.hsh.myapp.musicolumn.auth.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.hsh.myapp.musicolumn.post.entity.Post;
//import com.hsh.myapp.musicolumn.post.entity.PostComment;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long userNumber;

    @Column(nullable = false, unique = true)
    private String nickName;

    @Column(nullable = false)
    private LocalDate birthdate;

    @Column(nullable = false, unique = true)
    private String email;

    @Column(columnDefinition = "LONGTEXT")
    private String userImage;

    @OneToMany(mappedBy = "user") // User 엔티티와 연관된 게시물들을 조회하기 위한 One-to-Many 관계
    @JsonIgnore
    private List<Post> posts; // 유저가 작성한 게시물들

//    @OneToMany(mappedBy = "user") // User 엔티티와 연관된 댓글들을 조회하기 위한 One-to-Many 관계
//    private List<PostComment> comments; // 유저가 작성한 댓글들

    @OneToOne
    private Login login;
}
