package com.pbl2.pbl2.service;

import com.pbl2.pbl2.dto.PostDto;
import com.pbl2.pbl2.dto.UserDto;
import com.pbl2.pbl2.model.Post;
import com.pbl2.pbl2.model.User;
import com.pbl2.pbl2.repository.PostRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.AdditionalAnswers;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import java.util.ArrayList;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;


@ExtendWith(MockitoExtension.class)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class PostServiceTest {
    @Mock
    PostRepository postRepository;

    User user = new User(UserDto.Request.builder()
            .user_email("aaa@aaa")
            .user_name("asdf")
            .build());

    Post post;
    List<Post> posts = new ArrayList<>();

    @Test
    @Order(1)
    @DisplayName("게시글 업로드 테스트")
    void createPost() {
        // given
        PostService postService = new PostService(postRepository);
        when(postRepository.save(any(Post.class))).then(AdditionalAnswers.returnsFirstArg());


        // when
        post = postService.save(user, new PostDto.Request("contents", "image"));
        posts.add(post);

        // then
//        System.out.println("post.getContents() = " + post.getContents());
//        System.out.println("post.getPostId() = " + post.getPostId());
//        System.out.println("post.getUser().getUserName() = " + post.getUser().getUserName());

        Assertions.assertThat(post.getContents()).isEqualTo("contents");
        Assertions.assertThat(post.getImageUrl()).isEqualTo("image");
        Assertions.assertThat(post.getUser().getUserName()).isEqualTo("asdf");
    }

    @Test
    @Order(2)
    @DisplayName("게시글 찾기 테스트")
    void getPost() {
        // given
        createPost();
        PostService postService = new PostService(postRepository);
        when(postRepository.findAllByOrderByCreatedAtDesc()).thenReturn(posts);

        // when
        List<Post> postsReturn = postService.getAll();

        // then
        Assertions.assertThat(postsReturn.get(0).getContents()).isEqualTo("contents");
        Assertions.assertThat(postsReturn.get(0).getImageUrl()).isEqualTo("image");
        Assertions.assertThat(postsReturn.get(0).getUser().getUserName()).isEqualTo("asdf");
    }

}
