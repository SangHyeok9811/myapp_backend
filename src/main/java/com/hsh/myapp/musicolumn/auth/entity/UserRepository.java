package com.hsh.myapp.musicolumn.auth.entity;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByLogin_JoinNo(long joinNo);
    @Query(value = "select * from user where user_number = :userNumber", nativeQuery = true)
    Optional<User> findUserByUserNumber(long userNumber);
}
