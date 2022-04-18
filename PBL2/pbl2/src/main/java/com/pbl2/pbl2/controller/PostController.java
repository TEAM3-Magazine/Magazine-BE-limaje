package com.pbl2.pbl2.controller;


import com.pbl2.pbl2.dto.PostDto;
import com.pbl2.pbl2.model.Post;
import com.pbl2.pbl2.repository.PostRepository;
import com.pbl2.pbl2.service.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
public class PostController {
    private final PostRepository postRepository;
    private final PostService postService;

    //    CREATE
    @PostMapping("/api/post")
    public Post createPosting(@RequestBody PostDto.Request request) {
        Post post = new Post(request);
        return postRepository.save(post);
    }

    //    READ
    @GetMapping("/api/post")
    public List<Post> getPostings() {
        return postRepository.findAllByOrderByModifiedAtDesc();
    }

    //    READ
    @GetMapping("/api/post/{id}")
    public Post getPostings(@PathVariable Long id) {
        Post post = postRepository.findById(id).orElseThrow(
                () -> new IllegalArgumentException("게시물 존재하지 않습니다.")
        );
        return post;
    }

    //  UPDATE
    @PutMapping("/api/post/{postId}")
    public Long updatePosting(@PathVariable Long id, @RequestBody PostDto.Request request) {
        postService.update(id, request);
        return id;
    }

    //DELETE
    @DeleteMapping("/api/posting/{id}")
    public Long deletePosting(@PathVariable Long id) {
        postRepository.deleteById(id);
        return id;
    }


}
