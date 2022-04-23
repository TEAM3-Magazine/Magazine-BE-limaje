package com.pbl2.pbl2.controller;


import com.pbl2.pbl2.exception.*;
import com.pbl2.pbl2.responseEntity.ResponseBody;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ExceptionController {
    @ExceptionHandler({DuplicatedLogin.class})
    public ResponseEntity<ResponseBody> DuplicatedLogin(DuplicatedLogin ex) {
        return new ResponseEntity<>(new ResponseBody("fail", "이미 로그인 상태 입니다"), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler({DuplicatedEmail.class})
    public ResponseEntity<ResponseBody> DuplicatedEmail(DuplicatedEmail ex) {
        return new ResponseEntity<>(new ResponseBody("fail", "중복된 이메일 입니다"), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler({DuplicatedNickname.class})
    public ResponseEntity<ResponseBody> DuplicatedNickname(DuplicatedNickname ex) {
        return new ResponseEntity<>(new ResponseBody("fail", "중복된 닉네임 입니다"), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler({DuplicatedLike.class})
    public ResponseEntity<ResponseBody> DuplicatedLike(DuplicatedLike ex) {
        return new ResponseEntity<>(new ResponseBody("fail", "이미 좋아요 상태 입니다"), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler({NotFoundAuth.class})
    public ResponseEntity<ResponseBody> NotFoundAuth(NotFoundAuth ex) {
        return new ResponseEntity<>(new ResponseBody("fail", "유효한 로그인 토큰이 필요합니다"), HttpStatus.UNAUTHORIZED);
    }

    @ExceptionHandler({NotFoundPost.class})
    public ResponseEntity<ResponseBody> NotFoundPost(NotFoundPost ex) {
        return new ResponseEntity<>(new ResponseBody("fail", "존재하지 않는 게시글 입니다"), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler({NotFoundUser.class})
    public ResponseEntity<ResponseBody> NotFoundUser(NotFoundUser ex) {
        return new ResponseEntity<>(new ResponseBody("fail", "존재하지 않는 회원 입니다"), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler({NotFoundLike.class})
    public ResponseEntity<ResponseBody> NotFoundLike(NotFoundLike ex) {
        return new ResponseEntity<>(new ResponseBody("fail", "존재하지 않는 좋아요 상태 입니다"), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler({ExistLike.class})
    public ResponseEntity<ResponseBody> ExistLike(ExistLike ex) {
        return new ResponseEntity<>(new ResponseBody("fail", "이미 존재하는 좋아요 상태 입니다"), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler({InvalidEmail.class})
    public ResponseEntity<ResponseBody> InvalidEmail(InvalidEmail ex) {
        return new ResponseEntity<>(new ResponseBody("fail", "유효하지 않은 이메일 형식입니다"), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler({InvalidNickname.class})
    public ResponseEntity<ResponseBody> InvalidNickname(InvalidNickname ex) {
        return new ResponseEntity<>(new ResponseBody("fail", "유효하지 않은 닉네임 형식(최소 3자 이상, 알파벳 대소문자(a~Z), 숫자(0~9))입니다"), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler({InvalidNicknameWithPassword.class})
    public ResponseEntity<ResponseBody> InvalidNicknameWithPassword(InvalidNicknameWithPassword ex) {
        return new ResponseEntity<>(new ResponseBody("fail", "닉네임과 비밀번호가 동일할 수 없습니다."), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler({InvalidPassword.class})
    public ResponseEntity<ResponseBody> InvalidPassword(InvalidPassword ex) {
        return new ResponseEntity<>(new ResponseBody("fail", "유효하지 않은 비밀번호 형식(최소 4자 이상)입니다"), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler({InvalidPasswordCheck.class})
    public ResponseEntity<ResponseBody> InvalidPasswordCheck(InvalidPasswordCheck ex) {
        return new ResponseEntity<>(new ResponseBody("fail", "동일한 비밀번호를 입력해야 합니다."), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler({InvalidToken.class})
    public ResponseEntity<ResponseBody> InvalidToken(InvalidToken ex) {
        return new ResponseEntity<>(new ResponseBody("fail", "유효한 토큰이 아닙니다."), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler({InvalidLogin.class})
    public ResponseEntity<ResponseBody> InvalidLogin(InvalidLogin ex) {
        return new ResponseEntity<>(new ResponseBody("fail", "아이디와 비밀번호가 유효하지 않습니다."), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler({InvalidPostDeleteAuthorization.class})
    public ResponseEntity<ResponseBody> InvalidPostDeleteAuthorization(InvalidPostDeleteAuthorization ex) {
        return new ResponseEntity<>(new ResponseBody("fail", "본인의 글만 삭제가 가능합니다."), HttpStatus.FORBIDDEN);
    }

    @ExceptionHandler({InvalidPostPutAuthorization.class})
    public ResponseEntity<ResponseBody> InvalidPostPutAuthorization(InvalidPostPutAuthorization ex) {
        return new ResponseEntity<>(new ResponseBody("fail", "본인의 글만 수정이 가능합니다."), HttpStatus.FORBIDDEN);
    }

}
