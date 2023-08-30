package com.hsh.myapp.musicolumn.auth.entity;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByLogin_JoinNo(long joinNo);
}
