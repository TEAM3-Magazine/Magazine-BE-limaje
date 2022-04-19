package com.pbl2.pbl2.responseEntity;

import lombok.Getter;

@Getter
public class ResponseBody {
    private String result;
    private String msg;

    public ResponseBody(String result, String msg) {
        this.result = result;
        this.msg = msg;
    }
}