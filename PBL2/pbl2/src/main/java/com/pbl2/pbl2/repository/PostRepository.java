package com.pbl2.pbl2.repository;

import com.pbl2.pbl2.model.Post;
import com.pbl2.pbl2.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface PostRepository extends JpaRepository<Post, Long> {
    List<Post> findAllByOrderByModifiedAtDesc();
//    Optional<Post> findByPostId(String userName);
//    Optional<Post> findByUserEmail(String userEmail);
}