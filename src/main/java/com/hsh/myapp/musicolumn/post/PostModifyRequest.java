package com.hsh.myapp.musicolumn.post;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class PostModifyRequest {
    private String title;
    private String content;
    private String genre;
    private String singer;
    private String songName;
    private String image;
    private String audio;
    private String video;
}
