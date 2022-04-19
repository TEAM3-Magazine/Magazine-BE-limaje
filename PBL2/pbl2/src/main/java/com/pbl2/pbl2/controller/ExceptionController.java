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

    @ExceptionHandler({NotFoundAuth.class})
    public ResponseEntity<ResponseBody> NotFoundAuth(NotFoundAuth ex) {
        return new ResponseEntity<>(new ResponseBody("fail", "로그인 정보가 필요합니다"), HttpStatus.UNAUTHORIZED);
    }

    @ExceptionHandler({NotFoundPost.class})
    public ResponseEntity<ResponseBody> NotFoundPost(NotFoundPost ex) {
        return new ResponseEntity<>(new ResponseBody("fail", "존재하지 않는 게시글 입니다"), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler({NotValidEmail.class})
    public ResponseEntity<ResponseBody> NotValidEmail(NotValidEmail ex) {
        return new ResponseEntity<>(new ResponseBody("fail", "유효하지 않은 이메일 형식입니다"), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler({NotValidNickname.class})
    public ResponseEntity<ResponseBody> NotValidNickname(NotValidNickname ex) {
        return new ResponseEntity<>(new ResponseBody("fail", "유효하지 않은 닉네임 형식(최소 3자 이상, 알파벳 대소문자(a~Z), 숫자(0~9))입니다"), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler({NotValidNicknameWithPassword.class})
    public ResponseEntity<ResponseBody> NotValidNicknameWithPassword(NotValidNicknameWithPassword ex) {
        return new ResponseEntity<>(new ResponseBody("fail", "닉네임과 비밀번호가 동일할 수 없습니다."), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler({NotValidPassword.class})
    public ResponseEntity<ResponseBody> NotValidPassword(NotValidPassword ex) {
        return new ResponseEntity<>(new ResponseBody("fail", "유효하지 않은 비밀번호 형식(최소 4자 이상)입니다"), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler({NotValidPasswordCheck.class})
    public ResponseEntity<ResponseBody> NotValidPasswordCheck(NotValidPasswordCheck ex) {
        return new ResponseEntity<>(new ResponseBody("fail", "동일한 비밀번호를 입력해야 합니다."), HttpStatus.BAD_REQUEST);
    }

}
