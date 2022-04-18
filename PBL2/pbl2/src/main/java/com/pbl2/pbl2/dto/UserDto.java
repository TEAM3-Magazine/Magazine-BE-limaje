package com.pbl2.pbl2.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


public class UserDto {

    @Getter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class Request{
        private String user_email;
        private String user_name;
        private String user_password;
    }

    @Getter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class Response{
        private String user_email;
        private String user_name;
        private String user_password;
    }

}
