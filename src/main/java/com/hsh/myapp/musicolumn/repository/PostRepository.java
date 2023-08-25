package com.hsh.myapp.musicolumn.repository;

import com.hsh.myapp.musicolumn.entity.Post;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;



import java.util.List;

public interface PostRepository extends JpaRepository<Post, Long> {
    @Query(value = "select * from post order by post_no asc", nativeQuery = true)
    List<Post> findPostSortByNo();

    @Query (value = "SELECT p FROM Post p WHERE p.title LIKE %:keyword% OR p.singer LIKE %:keyword%" )
    Page<Post> findBySearch(String keyword, Pageable page);
}
