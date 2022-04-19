package com.pbl2.pbl2.controller;


import com.pbl2.pbl2.exception.*;
import com.pbl2.pbl2.responseEntity.ResponseBody;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ExceptionController {

    @ExceptionHandler({DuplicatedEmail.class})
    public ResponseEntity<ResponseBody> DuplicatedEmail(DuplicatedEmail ex) {
        return new ResponseEntity<>(new ResponseBody("fail", "중복된 이메일 입니다"), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler({DuplicatedNickname.class})
    public ResponseEntity<ResponseBody> DuplicatedNickname(DuplicatedNickname ex) {
        return new ResponseEntity<>(new ResponseBody("fail", "중복된 이메일 입니다"), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler({DuplicatedLike.class})
    public ResponseEntity<ResponseBody> DuplicatedLike(DuplicatedLike ex) {
        return new ResponseEntity<>(new ResponseBody("fail", "이미 좋아요 상태 입니다"), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler({NotFoundPost.class})
    public ResponseEntity<ResponseBody> NotFoundPost(NotFoundPost ex) {
        return new ResponseEntity<>(new ResponseBody("fail", "존재하지 않는 게시글 입니다"), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler({NotFoundUser.class})
    public ResponseEntity<ResponseBody> NotFoundUser(NotFoundUser ex) {
        return new ResponseEntity<>(new ResponseBody("fail", "존재하지 않는 사용자 입니다"), HttpStatus.NOT_FOUND);
    }

}
