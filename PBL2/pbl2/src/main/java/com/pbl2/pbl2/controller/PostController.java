package com.pbl2.pbl2.controller;


import com.pbl2.pbl2.dto.PostDto;
import com.pbl2.pbl2.exception.NotFoundPost;
import com.pbl2.pbl2.model.Post;
import com.pbl2.pbl2.model.User;
import com.pbl2.pbl2.repository.PostRepository;
import com.pbl2.pbl2.responseEntity.ResponseBody;
import com.pbl2.pbl2.security.UserDetailsImpl;
import com.pbl2.pbl2.service.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.springframework.http.HttpStatus.BAD_REQUEST;

@RequiredArgsConstructor
@RestController
public class PostController {
    private final PostRepository postRepository;
    private final PostService postService;

    //READ All
    @GetMapping("/api/post")
    public ResponseEntity<List<PostDto.Response>> getPostings() {
        List<Post> posts = postRepository.findAllByOrderByCreatedAtDesc();
        List<PostDto.Response> body = new ArrayList<>();

        for (Post post : posts) {
            body.add(new PostDto.Response(post));
        }

        return new ResponseEntity<>(body, HttpStatus.OK);
    }

    //READ
    @GetMapping("/api/post/{id}")
    public ResponseEntity<PostDto.Response> getPostings(@PathVariable Long id) {
        Post post = postService.get(id);
        return new ResponseEntity<>(new PostDto.Response(post), HttpStatus.OK);
    }

    //CREATE
    @PostMapping("/api/post")
    public ResponseEntity<ResponseBody> createPosting(@RequestBody PostDto.Request request, @AuthenticationPrincipal UserDetailsImpl userDetails) {
        User user = userDetails.getUser();
        postService.save(user, request);
        return new ResponseEntity<>(new ResponseBody("success","게시글을 생성했습니다"), HttpStatus.CREATED);
    }

    //UPDATE
    @PutMapping("/api/post/{postId}")
    public ResponseEntity<ResponseBody> updatePosting(@PathVariable Long postId, @RequestBody PostDto.Request request) {
        postService.update(postId, request);
        return new ResponseEntity<>(new ResponseBody("success","게시글을 수정했습니다"), HttpStatus.OK);
    }

    //DELETE
    @DeleteMapping("/api/post/{id}")
    public ResponseEntity<ResponseBody> deletePosting(@PathVariable Long id) {
        postRepository.deleteById(id);
        return new ResponseEntity<>(new ResponseBody("success","게시글을 삭제했습니다"), HttpStatus.OK);
    }

}
