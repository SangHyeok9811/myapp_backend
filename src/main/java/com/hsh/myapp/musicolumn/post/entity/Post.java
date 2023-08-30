package com.hsh.myapp.musicolumn.post.entity;

import com.hsh.myapp.musicolumn.auth.entity.User;
import jakarta.persistence.*;
import lombok.*;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Post {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long postNo;
    private String title;

    private String creatorName;
    private String content;
    private String genre;

    private String singer;
    private String songName;
    @Column(columnDefinition = "LONGTEXT")
    private String image;
    @Column(columnDefinition = "LONGTEXT")
    private String audio;
    @Column(columnDefinition = "LONGTEXT")
    private String video;

//    private long joinNo
    @Column(name = "`like`")
    private long like;

    private long createdTime;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;
}
