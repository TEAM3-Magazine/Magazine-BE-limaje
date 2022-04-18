package com.pbl2.pbl2.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

public class PostDto {

    @Getter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class Request{
        private String contents;
        private String image_url;
    }

    @Getter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class Response{
        private Long user_id;
        private Long post_id;
        private String user_name;
        private String contents;
        private String image_url;
    }

}
