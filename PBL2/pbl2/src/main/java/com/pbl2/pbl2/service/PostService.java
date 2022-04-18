package com.pbl2.pbl2.service;

import com.pbl2.pbl2.dto.PostDto;
import com.pbl2.pbl2.model.Post;
import com.pbl2.pbl2.repository.PostRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import javax.transaction.Transactional;

@RequiredArgsConstructor
@Service
public class PostService {
    private final PostRepository postRepository;

    @Transactional
    public Long update(Long id, PostDto.Request request) {
        Post posting = postRepository.findById(id).orElseThrow(
                () -> new IllegalArgumentException("게시물 존재하지 않습니다.")
        );
        posting.update(request);
        return posting.getPostId();
    }

    @Transactional
    public Post get(@PathVariable Long id) {
        Post post = postRepository.findById(id).orElseThrow(
                () -> new IllegalArgumentException("게시물 존재하지 않습니다.")
        );
        return post;
    }

}
