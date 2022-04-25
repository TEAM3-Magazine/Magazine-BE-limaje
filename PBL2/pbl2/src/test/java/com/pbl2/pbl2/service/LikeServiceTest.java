package com.pbl2.pbl2.service;

import com.pbl2.pbl2.dto.PostDto;
import com.pbl2.pbl2.dto.UserDto;
import com.pbl2.pbl2.model.Like;
import com.pbl2.pbl2.model.Post;
import com.pbl2.pbl2.model.User;
import com.pbl2.pbl2.repository.LikeRepository;
import com.pbl2.pbl2.repository.PostRepository;
import com.pbl2.pbl2.repository.UserRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.AdditionalAnswers;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;


@ExtendWith(MockitoExtension.class)
//@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class LikeServiceTest {

    @Mock
    UserRepository userRepository;
    @Mock
    PostRepository postRepository;
    @Mock
    LikeRepository likeRepository;

    User user = new User(UserDto.Request.builder()
            .user_email("aaa@aaa")
            .user_name("asdf")
            .build());

    Post post = new Post(user, new PostDto.Request("contents", "imageUrl"));

    @Test
    @DisplayName("게시글 좋아요 테스트")
    void createLike() {
        // given
        LikeService likeService = new LikeService(postRepository,userRepository,likeRepository);
        when(userRepository.findById(any(Long.class))).thenReturn(Optional.ofNullable(user));
        when(postRepository.findById(any(Long.class))).thenReturn(Optional.ofNullable(post));
        when(likeRepository.save(any(Like.class))).then(AdditionalAnswers.returnsFirstArg());
//        임시 data
        Long userId = 10L;
        Long postId = 20L;

        // when
        Like like = likeService.addlikes(userId, postId);

        // then
        Assertions.assertThat(like.getPost()).isEqualTo(post);
        Assertions.assertThat(like.getUser()).isEqualTo(user);
    }

    @Test
    @DisplayName("게시글 취소 테스트")
    void deleteLike() {
//        // given
//
//        // when
//
//        // then
    }
}
