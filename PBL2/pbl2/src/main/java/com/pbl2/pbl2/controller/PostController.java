package com.pbl2.pbl2.controller;


import com.pbl2.pbl2.dto.PostDto;
import com.pbl2.pbl2.exception.NotFoundAuth;
import com.pbl2.pbl2.exception.NotFoundPost;
import com.pbl2.pbl2.model.Like;
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

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.springframework.http.HttpStatus.BAD_REQUEST;

@RequiredArgsConstructor
@RestController
public class PostController {
    private final PostService postService;

    //READ All
    @GetMapping("/api/post")
    public ResponseEntity<List<PostDto.Response>> getPostings() {
        String now = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
        System.out.println(now + " 모든 게시글 요청");

        List<Post> posts = postService.getAll();
        List<PostDto.Response> body = new ArrayList<>();

        for (Post post : posts) {
            //        builder 적용
            List<Long> likeList = new ArrayList<>();
            for(Like like : post.getLikelist()){
                likeList.add(like.getUser().getUserId());
            }
            PostDto.Response postResponse = PostDto.Response.builder()
                    .post_id(post.getPostId())
                    .user_id(post.getUser().getUserId())
                    .user_name(post.getUser().getUserName())
                    .contents(post.getContents())
                    .image_url(post.getImageUrl())
                    .created_at(post.getCreatedAt().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")))
                    .post_like(likeList)
                .build();
//            body.add(new PostDto.Response(post));
            body.add(postResponse);
        }

        return new ResponseEntity<>(body, HttpStatus.OK);
    }

    //READ
    @GetMapping("/api/post/{id}")
    public ResponseEntity<PostDto.Response> getPostings(@PathVariable Long id) {
        String now = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
        System.out.println(now + " 특정 게시글 요청 : " + id);

        Post post = postService.get(id);

        return new ResponseEntity<>(new PostDto.Response(post), HttpStatus.OK);
    }

    //CREATE
    @PostMapping("/api/post")
    public ResponseEntity<ResponseBody> createPosting(@RequestBody PostDto.Request request, @AuthenticationPrincipal UserDetailsImpl userDetails) {
        String now = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
        System.out.println(now + " 게시글 생성 contents : " + request.getContents() + " image_url : " + request.getImage_url());

        if(userDetails.getUser().getUserName().equals("x")){
            throw new NotFoundAuth();
        }

        User user = userDetails.getUser();
        Long postId = postService.save(user, request).getPostId();
        return new ResponseEntity<>(new ResponseBody("success","게시글을 생성했습니다"), HttpStatus.CREATED);
    }

    //UPDATE
    @PutMapping("/api/post/{postId}")
    public ResponseEntity<ResponseBody> updatePosting(@PathVariable Long postId, @RequestBody PostDto.Request request, @AuthenticationPrincipal UserDetailsImpl userDetails) {
        String now = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
        System.out.println(now + " 게시글 수정 : " + postId + " contents : "+ request.getContents() + " image_url : " + request.getImage_url());

        User user = userDetails.getUser();
        if(user.getUserName().equals("x")){
            throw new NotFoundAuth();
        }

        postService.update(postId, user.getUserId(), request);
        return new ResponseEntity<>(new ResponseBody("success","게시글을 수정했습니다"), HttpStatus.OK);
    }

    //DELETE
    @DeleteMapping("/api/post/{id}")
    public ResponseEntity<ResponseBody> deletePosting(@PathVariable Long id, @AuthenticationPrincipal UserDetailsImpl userDetails) {
        String now = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
        System.out.println(now + " 게시글 삭제 : " + id);

        User user = userDetails.getUser();
        if(user.getUserName().equals("x")){
            throw new NotFoundAuth();
        }

        postService.delete(id, user.getUserId());
        return new ResponseEntity<>(new ResponseBody("success","게시글을 삭제했습니다"), HttpStatus.OK);
    }

}
