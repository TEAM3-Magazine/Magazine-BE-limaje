package com.pbl2.pbl2.service;


import com.pbl2.pbl2.dto.UserDto;
import com.pbl2.pbl2.exception.*;
import com.pbl2.pbl2.model.User;
import com.pbl2.pbl2.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.validation.Errors;
import org.springframework.validation.FieldError;
import org.springframework.web.server.ResponseStatusException;

import javax.transaction.Transactional;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import static org.springframework.http.HttpStatus.NOT_FOUND;

@Service
public class UserService {
    private final PasswordEncoder passwordEncoder;
    private final UserRepository userRepository;
    private static final String ADMIN_TOKEN = "AAABnv/xRVklrnYxKZ0aHgTBcXukeZygoC";

    @Autowired
    public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public void validateHandling(Errors errors) {
        for (FieldError error : errors.getFieldErrors()) {
            if(error.getField().equals("user_email")){
                throw new NotValidEmail();
            }
            else if(error.getField().equals("user_name")){
                throw new NotValidNickname();
            }
            else if(error.getField().equals("user_password")){
                throw new NotValidPassword();
            }
        }
    }

    @Transactional
    public void registerUser(UserDto.Request requestDto) {

        System.out.println(requestDto.getUser_email());
        System.out.println(requestDto.getUser_name());
        System.out.println(requestDto.getUser_password());
        //비밀번호 확인 입력 검사
        if(!requestDto.getUser_password().equals(requestDto.getUser_password_check())){
            throw new NotValidPasswordCheck();
        }

        // 비밀번호<>닉네임 검사
        if(requestDto.getUser_password().equals(requestDto.getUser_name())){
            throw new NotValidNicknameWithPassword();
        }

        // 회원 ID 중복 확인
        String userEmail = requestDto.getUser_email();
        if (userRepository.findByUserEmail(userEmail).isPresent()) {
            throw new DuplicatedEmail();
        }

        String username = requestDto.getUser_name();
        if (userRepository.findByUserName(username).isPresent()) {
            throw new DuplicatedNickname();
        }


        // 패스워드 암호화
        String password = passwordEncoder.encode(requestDto.getUser_password());
        String email = requestDto.getUser_email();

        User user = new User(new UserDto.Request(email, username, password, password));
        userRepository.save(user);
    }

}