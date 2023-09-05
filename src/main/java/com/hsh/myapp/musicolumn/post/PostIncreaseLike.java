package com.hsh.myapp.musicolumn.post;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class PostIncreaseLike {
    public long like;
    public long postNo;
}
