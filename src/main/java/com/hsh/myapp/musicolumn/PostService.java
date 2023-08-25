package com.hsh.myapp.musicolumn;

import com.hsh.myapp.musicolumn.repository.PostRepository;

import org.springframework.beans.factory.annotation.Autowired;

public class PostService {
    @Autowired
    PostRepository postRepo;
}
