//package com.hsh.myapp.musicolumn.post.entity;
//
//import com.hsh.myapp.musicolumn.auth.entity.User;
//import jakarta.persistence.*;
//import lombok.Data;
//import lombok.NoArgsConstructor;
//
//@Entity
//@Data
//@NoArgsConstructor
//public class PostComment {
//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    private long commentNo;
//
//    private String commentContent;
//
//    private String ownerNickName;
//
////    private long ownerNo;
//
////    private long postNo;
//
//    @ManyToOne
////    @JoinColumn(name = "postNo")
//    private Post post;
//
//    @ManyToOne // Many-to-One 관계 추가
////    @JoinColumn(name = "userNumber")
//    private User user; // 댓글을 작성한 유저
//}