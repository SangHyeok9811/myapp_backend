package com.hsh.myapp.musicolumn;

import com.hsh.myapp.musicolumn.entity.Post;
import com.hsh.myapp.musicolumn.repository.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping(value = "/musicolumn")
public class PostController {

    @Autowired
    PostRepository postRepo;

    @GetMapping
    public List<Post> getPostList() {

        // 최신순
        List<Post> list = postRepo.findPostSortByNo();
        return list;
    }

    @GetMapping(value = "/paging/keyWord")
    public Page<Post> getPagingSearByKeyWord
            (@RequestParam int page,
             @RequestParam int size,
             @RequestParam String keyWord) {
        System.out.println(page);
        System.out.println(size);
        System.out.println(keyWord);

        // 기본적으로 key 정렬(default)
        Sort sort = Sort.by("postNo").descending();

        // 페이지 매개변수 객체
        PageRequest pageRequest = PageRequest.of(page, size, sort);

        return postRepo.findBySearch(keyWord, pageRequest);
    }

    @PostMapping
    public ResponseEntity<Map<String, Object>> addPost
            (@RequestBody Post post) {

        post.setCreatedTime(new Date().getTime());
        post.setLike(0);
        post.setCreatorName("유저");

        System.out.println(post);
        postRepo.save(post);
        System.out.println(postRepo);

        Optional<Post> savedPost = postRepo.findById(post.getPostNo());

        if (savedPost.isPresent()) {   // 값이 있을경우 true, 없으면 false
            Map<String, Object> res = new HashMap<>();
            res.put("data", savedPost);
            res.put("message", "created");

            // HTTP Status Code: 201 Created
            // 리소스가 정상적으로 생성되었음.
            return ResponseEntity.status(HttpStatus.CREATED).body(res);
        }
        return ResponseEntity.ok().build();
    }



}
