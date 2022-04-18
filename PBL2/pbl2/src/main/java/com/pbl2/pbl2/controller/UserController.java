package com.pbl2.pbl2.controller;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.pbl2.pbl2.dto.UserDto;
import com.pbl2.pbl2.security.UserDetailsImpl;
import com.pbl2.pbl2.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    // 회원 로그인 페이지
    @GetMapping("/user/loginView")
    public String login() {
        return "login";
    }

    // 회원 가입 페이지
    @GetMapping("/user/signup")
    public String signup() {
        return "signup";
    }

    // 회원 가입 요청 처리
    @PostMapping("/user/signup")
    public ResponseEntity<String> registerUser(@RequestBody UserDto.Request requestDto) {
        userService.registerUser(requestDto);

        HttpHeaders responseHeaders = new HttpHeaders();
        return ResponseEntity.ok()
                .headers(responseHeaders)
                .body("");
    }

    // 회원 관련 정보 받기
//    @PostMapping("/user/userinfo")
//    @ResponseBody
//    public UserInfoDto getUserInfo(@AuthenticationPrincipal UserDetailsImpl userDetails) {
//        String username = userDetails.getUser().getUsername();
//        UserRoleEnum role = userDetails.getUser().getRole();
//        boolean isAdmin = (role == UserRoleEnum.ADMIN);
//
//        return new UserInfoDto(username, isAdmin);
//    }

}