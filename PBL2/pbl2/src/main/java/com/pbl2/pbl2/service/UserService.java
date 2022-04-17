package com.pbl2.pbl2.service;


import com.pbl2.pbl2.dto.UserDto;
import com.pbl2.pbl2.model.User;
import com.pbl2.pbl2.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

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

    public void registerUser(UserDto.Request requestDto) {
        // 회원 ID 중복 확인
        String username = requestDto.getUserName();
        Optional<User> found = userRepository.findByUserName(username);
        if (found.isPresent()) {
            throw new IllegalArgumentException("중복된 사용자 ID 가 존재합니다.");
        }

        String userEmail = requestDto.getUserEmail();
        found = userRepository.findByUserEmail(userEmail);
        if (found.isPresent()) {
            throw new IllegalArgumentException("중복된 사용자 ID 가 존재합니다.");
        }

        // 패스워드 암호화
        String password = passwordEncoder.encode(requestDto.getUserPassword());
        String email = requestDto.getUserEmail();


        User user = new User(new UserDto.Request(email, username, password));
        userRepository.save(user);
    }
}