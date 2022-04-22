package com.pbl2.pbl2.controller;


import com.pbl2.pbl2.dto.TokenDto;
import com.pbl2.pbl2.dto.UserDto;
import com.pbl2.pbl2.exception.NotFoundAuth;
import com.pbl2.pbl2.exception.RestException;
import com.pbl2.pbl2.model.User;
import com.pbl2.pbl2.responseEntity.ResponseBody;
import com.pbl2.pbl2.responseEntity.TokenBody;
import com.pbl2.pbl2.security.UserDetailsImpl;
import com.pbl2.pbl2.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.validation.Errors;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.util.Map;

@Controller
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

//    // 회원 로그인 페이지
//    @GetMapping("/user/loginView")
//    public String login() {
//        return "login";
//    }

//    // 회원 가입 페이지
//    @GetMapping("/user/signup")
//    public String signup() {
//        return "signup";
//    }

    //    // 회원 가입 요청 처리
//    @PostMapping("/user/signup")
//    public String registerUser(@RequestBody UserDto.Request requestDto) {
//        userService.registerUser(requestDto);
//        return "redirect:/user/loginView";
//    }
    @PostMapping("/user/signup")
    public ResponseEntity<ResponseBody> registerUser(@Valid @RequestBody UserDto.Request requestDto, Errors errors) {
        userService.validateHandling(errors);
        userService.registerUser(requestDto);
        return new ResponseEntity<>(new ResponseBody("success","회원 가입 성공"), HttpStatus.OK);
    }

    //     회원 관련 정보 받기
    @GetMapping("/user/userinfo")
    public ResponseEntity<UserDto.Response> getUserInfo(@AuthenticationPrincipal UserDetailsImpl userDetails) {
        User user = userDetails.getUser();
        if(user.getUserName().equals("x")){
            throw new NotFoundAuth();
        }
        Long userId = user.getUserId();
        String userEmail = user.getUserEmail();
        String userName = user.getUserName();
        return new ResponseEntity<>(new UserDto.Response(userId, userEmail, userName),HttpStatus.OK);
    }

    @PostMapping("/user/login")
    public ResponseEntity<TokenBody> login(@RequestBody UserDto.LoginRequest loginRequest, HttpServletResponse response, Errors errors) {
//        if (user != null){
//            return new ResponseEntity<>(new Success(false, "이미 로그인 중입니다."), HttpStatus.BAD_REQUEST);
//        }
        if (errors.hasErrors()) {
            for (FieldError error : errors.getFieldErrors()) {
                throw new RestException(HttpStatus.BAD_REQUEST, error.getDefaultMessage());
            }
        }

        TokenDto.Response token = userService.login(loginRequest);

        response.setHeader("Authorization", "Bearer " + token.getToken());
//        response.setHeader("REFRESH_TOKEN", token.getREFRESH_TOKEN());


        return new ResponseEntity<>(new TokenBody("success","로그인 성공", token.getToken()), HttpStatus.OK);
    }

    @PostMapping("/user/logout")
    public ResponseEntity<ResponseBody> logout(HttpServletRequest request) {
        userService.logout(request);
        return new ResponseEntity<>(new ResponseBody("success","로그아웃 성공"), HttpStatus.OK);
    }
}