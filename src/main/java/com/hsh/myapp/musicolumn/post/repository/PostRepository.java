package com.hsh.myapp.musicolumn.post.repository;

import com.hsh.myapp.musicolumn.post.entity.Post;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;



import java.util.List;
import java.util.Optional;

public interface PostRepository extends JpaRepository<Post, Long> {
    @Query(value = "select * from post order by post_no asc", nativeQuery = true)
    List<Post> findPostSortByNo();

    @Query (value = "SELECT p FROM Post p WHERE p.songName LIKE %:keyword% OR p.singer LIKE %:keyword%" )
    Page<Post> findBySearch(String keyword, Pageable page);

    @Query(value = "select * from post where post_no = :no", nativeQuery = true)
    Optional<Post> findPostByPostNo(Long no);

    Optional<Post> findPostByUserNo(Long no);


}
