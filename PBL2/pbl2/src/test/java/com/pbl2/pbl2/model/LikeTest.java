package com.pbl2.pbl2.model;

import com.pbl2.pbl2.dto.PostDto;
import com.pbl2.pbl2.dto.UserDto;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class LikeTest {
    @Test
    @DisplayName("Like 정상 생성 케이스")
    void createTest1() {
// given
        UserDto.Request request1 = new UserDto.Request("aaa@aaa.com", "aaa", "1234", "1234");
        User user1 = new User(request1);

        UserDto.Request request2 = new UserDto.Request("bbb@bbb.com", "bbb", "4567", "4567");
        User user2 = new User(request2);

        PostDto.Request postRequest1 = new PostDto.Request( "contents Test1", "aaa" );
        PostDto.Request postRequest2 = new PostDto.Request( "contents Test2", "bbb" );

        Post post1 = new Post(user1, postRequest1);
        Post post2 = new Post(user1, postRequest1);

// when
        Like like1 = new Like();
        like1.setUser(user2);
        like1.setPost(post1);

// then
        assertNull(like1.getLikeId());
        assertEquals(user2, like1.getUser());
        assertEquals(post1, like1.getPost());

    }

}