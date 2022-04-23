package com.pbl2.pbl2.model;

import com.pbl2.pbl2.dto.UserDto;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ProductTest {
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

// when
        User user = new User(request);

// then
        assertNull(user.getUserId());
        assertEquals(userEmail, user.getUserEmail());
        assertEquals(username, user.getUserName());
        assertEquals(password, user.getUserPassword());
    }

}