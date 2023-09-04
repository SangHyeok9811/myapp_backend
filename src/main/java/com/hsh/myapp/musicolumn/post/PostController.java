package com.hsh.myapp.musicolumn.post;

import com.hsh.myapp.musicolumn.auth.Auth;
import com.hsh.myapp.musicolumn.auth.AuthUser;
import com.hsh.myapp.musicolumn.auth.entity.LoginRepository;
import com.hsh.myapp.musicolumn.post.entity.Post;
import com.hsh.myapp.musicolumn.post.repository.PostRepository;
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

    @Autowired
    LoginRepository loginRepo;

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

    @Auth
    @PostMapping
    public ResponseEntity<Map<String, Object>> addPost
            (@RequestBody Post post, @RequestAttribute AuthUser authUser) {

        System.out.println(authUser);
        System.out.println(post);

        if (post.getTitle() == null || post.getContent() == null || post.getSinger() == null || post.getSongName() == null ||
                post.getTitle().isEmpty() || post.getContent().isEmpty() || post.getSinger().isEmpty() || post.getSongName().isEmpty()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }

        post.setCreatorNo(authUser.getJoinNo());
        post.setCreatorName(authUser.getNickName());
        post.setCreatedTime(new Date().getTime());
        post.setLike(0);

        Post savedPost = postRepo.save(post);

        if (savedPost != null) {   // 값이 있을경우 true, 없으면 false
            Map<String, Object> res = new HashMap<>();
            res.put("data", savedPost);
            res.put("message", "created");

            // HTTP Status Code: 201 Created
            // 리소스가 정상적으로 생성되었음.
            return ResponseEntity.status(HttpStatus.CREATED).body(res);
        }
        return ResponseEntity.ok().build();

    }

    @Auth
    @DeleteMapping(value = "/{no}")
    public ResponseEntity removePost(@PathVariable long postNo, @RequestAttribute AuthUser authUser) {
        System.out.println(postNo);

        Optional<Post> post = postRepo.findPostByPostNo(postNo);


        if (!post.isPresent()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }

        if (post.get().getPostNo() != postNo) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
        }

        postRepo.deleteById(postNo);
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    @Auth
    @PutMapping(value = "/{no}")
    public ResponseEntity modifyPost(@PathVariable long postNo, @RequestBody PostModifyRequest post, @RequestAttribute AuthUser authUser) {
        System.out.println(postNo);
        System.out.println(post);

        // 1. 키값으로 조회해옴
        Optional<Post> findedPost = postRepo.findByJoinNo(authUser.getJoinNo());
        // 2. 해당 레코드가 있는지 확인
        if (!findedPost.isPresent()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        //수정해서 저장할 포스트
        Post toModifyPost = findedPost.get();

        if (post.getTitle() != null && !post.getTitle().isEmpty()) {
            toModifyPost.setTitle(post.getTitle());
        }
        if (post.getContent() != null && !post.getContent().isEmpty()) {
            toModifyPost.setContent(post.getContent());
        }
        //update
        repo.save(toModifyPost);

        //ok 처리
        return ResponseEntity.ok().build();
    }


}
