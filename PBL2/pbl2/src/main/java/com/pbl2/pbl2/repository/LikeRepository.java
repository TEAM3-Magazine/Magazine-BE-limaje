package com.pbl2.pbl2.repository;

import com.pbl2.pbl2.model.Like;
import com.pbl2.pbl2.model.Post;
import com.pbl2.pbl2.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface LikeRepository extends JpaRepository<Like, Long> {
    Optional<Like> findByUser_UserIdAndPost_PostId(Long userId, Long postId);

}