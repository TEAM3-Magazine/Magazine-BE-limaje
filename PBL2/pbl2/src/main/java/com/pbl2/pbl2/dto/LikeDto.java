package com.pbl2.pbl2.dto;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

public class LikeDto {

    @Getter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class Request{
        private Long post_id;
    }

    @Getter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class Response{
        private Long user_id;
        private Long post_id;
    }

}