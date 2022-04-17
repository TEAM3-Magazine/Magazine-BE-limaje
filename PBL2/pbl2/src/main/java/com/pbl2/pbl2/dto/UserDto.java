package com.pbl2.pbl2.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;


public class UserDto {

    @Getter
    @AllArgsConstructor
    public static class Request{
        private String userEmail;
        private String userName;
        private String userPassword;
    }

    @Getter
    @AllArgsConstructor
    @NoArgsConstructor
    public static class Response{
        private Long userId;
        private String username;
    }

}
