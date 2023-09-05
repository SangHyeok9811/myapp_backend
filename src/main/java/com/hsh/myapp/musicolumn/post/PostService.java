package com.hsh.myapp.musicolumn.post;

import com.hsh.myapp.musicolumn.auth.entity.User;
import com.hsh.myapp.musicolumn.auth.entity.UserRepository;
import com.hsh.myapp.musicolumn.post.entity.Post;
//import com.hsh.myapp.musicolumn.post.entity.PostComment;
//import com.hsh.myapp.musicolumn.post.repository.PostCommentRepository;
import com.hsh.myapp.musicolumn.post.repository.PostRepository;

import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PostService {
    @Autowired
    PostRepository postRepo;

//    @Autowired
//    PostCommentRepository commentRepo;

    @Autowired
    UserRepository userRepo;

//    @Transactional
//    public void createComment(Post post, User user, PostComment comment) {
//        postRepo.save(post);
//        userRepo.save(user);
//        commentRepo.save(comment);
//    }
}