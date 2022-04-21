package com.pbl2.pbl2.responseEntity;

import lombok.Getter;

@Getter
public class TokenBody {

    private String result;
    private String msg;
    private String token;

    public TokenBody(String result, String msg, String token) {
        this.result = result;
        this.msg = msg;
        this.token = token;
    }

}