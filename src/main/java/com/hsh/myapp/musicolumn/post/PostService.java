package com.hsh.myapp.musicolumn.post;

import com.hsh.myapp.musicolumn.post.repository.PostRepository;

import org.springframework.beans.factory.annotation.Autowired;

public class PostService {
    @Autowired
    PostRepository postRepo;
}
