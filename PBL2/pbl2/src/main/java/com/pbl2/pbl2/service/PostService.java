package com.pbl2.pbl2.service;

import com.pbl2.pbl2.dto.PostDto;
import com.pbl2.pbl2.exception.NotFoundPost;
import com.pbl2.pbl2.model.Post;
import com.pbl2.pbl2.model.User;
import com.pbl2.pbl2.repository.PostRepository;
import com.pbl2.pbl2.security.UserDetailsImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import javax.transaction.Transactional;

@RequiredArgsConstructor
@Service
public class PostService {
    private final PostRepository postRepository;

    @Transactional
    public Post save(User user, PostDto.Request request){
        Post post = new Post(user, request);
        user.addUserToPost(post);
        postRepository.save(post);
        return post;
    }

    @Transactional
    public Long update(Long id, PostDto.Request request) {
        Post post = postRepository.findById(id).orElseThrow(
                NotFoundPost::new
        );
        post.update(request);
        return post.getPostId();
    }

    @Transactional
    public Post get(@PathVariable Long id) {
        Post post = postRepository.findById(id).orElseThrow(
                NotFoundPost::new
        );
        return post;
    }



}
