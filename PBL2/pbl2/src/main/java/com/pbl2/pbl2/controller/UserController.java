package com.pbl2.pbl2.controller;


import com.pbl2.pbl2.dto.UserDto;
import com.pbl2.pbl2.responseEntity.ResponseBody;
import com.pbl2.pbl2.security.UserDetailsImpl;
import com.pbl2.pbl2.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

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
    public ResponseEntity<ResponseBody>  registerUser(@RequestBody UserDto.Request requestDto) {

        userService.registerUser(requestDto);
        return new ResponseEntity<>(new ResponseBody("success","회원 가입 성공"), HttpStatus.OK);
    }

    //     회원 관련 정보 받기
    @PostMapping("/user/userinfo")
    @org.springframework.web.bind.annotation.ResponseBody
    public UserDto.Response getUserInfo(@AuthenticationPrincipal UserDetailsImpl userDetails) {
        Long userId = userDetails.getUser().getUserId();
        String userEmail = userDetails.getUser().getUserEmail();
        String userName = userDetails.getUser().getUserName();
        return new UserDto.Response(userId, userEmail, userName);
    }
}