package com.pbl2.pbl2.controller;

import com.pbl2.pbl2.exception.NotFoundAuth;
import com.pbl2.pbl2.model.Like;
import com.pbl2.pbl2.model.User;
import com.pbl2.pbl2.repository.LikeRepository;
import com.pbl2.pbl2.responseEntity.ResponseBody;
import com.pbl2.pbl2.security.UserDetailsImpl;

import com.pbl2.pbl2.service.LikeService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@RequiredArgsConstructor
@RestController
public class LikeController {
    private final LikeService likesService;
    private final LikeRepository likeRepository;


    @PostMapping("/api/post/{postId}/like")
    public ResponseEntity<ResponseBody> likeBoard(@AuthenticationPrincipal UserDetailsImpl userDetails, @PathVariable Long postId) {
        User user = userDetails.getUser();
        if(user.getUserName().equals("x")){
            throw new NotFoundAuth();
        }
        String now = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
        System.out.println(now + " 좋아요 userId = " + user.getUserId() + " postId 요청" + postId);

        likesService.addlikes(user.getUserId(), postId);
        return new ResponseEntity<>(new ResponseBody("success","좋아요를 생성했습니다"), HttpStatus.OK);
    }

    @DeleteMapping("/api/post/{postId}/like")
    public ResponseEntity<ResponseBody> cancelLikeBoard(@AuthenticationPrincipal UserDetailsImpl userDetails, @PathVariable Long postId) {
        User user = userDetails.getUser();
        if(user.getUserName().equals("x")){
            throw new NotFoundAuth();
        }

        String now = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
        System.out.println(now + "좋아요 취소 userId = " + user.getUserId() + " postId 요청" + postId);

        likesService.delete(user.getUserId(), postId);
        return new ResponseEntity<>(new ResponseBody("success","좋아요를 취소했습니다"), HttpStatus.OK);
    }

}