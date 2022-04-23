package com.pbl2.pbl2.model;

import com.pbl2.pbl2.dto.PostDto;
import com.pbl2.pbl2.dto.UserDto;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PostTest {

    @Test
    @DisplayName("정상 케이스")
    void createTest1() {
// given
        String userEmail = "aaa@aaa.com";
        String username = "aaa";
        String password = "1234";
        String password_check = "1234";

        UserDto.Request request = new UserDto.Request(
                userEmail,
                username,
                password,
                password_check
        );
        User user = new User(request);

        String contents = "contents Test";
        String image_url = "aaa";

        PostDto.Request postRequest = new PostDto.Request( contents, image_url );

// when
        Post post = new Post(user, postRequest);

// then
        assertNull(post.getPostId());
        assertEquals(user, post.getUser());
        assertEquals(contents, post.getContents());
        assertEquals(image_url, post.getImageUrl());
    }

}