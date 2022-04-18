package com.pbl2.pbl2.repository;

import com.pbl2.pbl2.model.Like;
import com.pbl2.pbl2.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface LikeRepository extends JpaRepository<Like, Long> {


}