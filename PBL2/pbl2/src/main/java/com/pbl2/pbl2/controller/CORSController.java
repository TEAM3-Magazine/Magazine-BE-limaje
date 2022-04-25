package com.pbl2.pbl2.controller;

import com.pbl2.pbl2.dto.PostDto;
import com.pbl2.pbl2.exception.NotFoundAuth;
import com.pbl2.pbl2.model.Post;
import com.pbl2.pbl2.model.User;
import com.pbl2.pbl2.repository.LikeRepository;
import com.pbl2.pbl2.repository.PostRepository;
import com.pbl2.pbl2.repository.UserRepository;
import com.pbl2.pbl2.responseEntity.ResponseBody;
import com.pbl2.pbl2.security.UserDetailsImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
@RestController
public class CORSController {


//    Don't use!!!!
    private final UserRepository userRepository;
    private final PostRepository postRepository;
    private final LikeRepository likeRepository;
    @GetMapping("/api/post/clear")
    @Transactional
    public ResponseEntity<ResponseBody> clearAll() {
        likeRepository.deleteAll();
        postRepository.deleteAll();
        userRepository.deleteAll();
        return new ResponseEntity<>(new ResponseBody("success", "DELETE ALL"), HttpStatus.OK);
    }


    @GetMapping("/cors/test")
    public ResponseEntity<ResponseBody> getCors() {
        return new ResponseEntity<>(new ResponseBody("success", "CORS GET PASS"), HttpStatus.ACCEPTED);
    }

    @GetMapping("/cors/test/2")
    public ResponseEntity<ResponseBody> getCors2() {
        return new ResponseEntity<>(new ResponseBody("success", "CORS GET PASS 2"), HttpStatus.ACCEPTED);
    }

    @PostMapping("/cors/test")
    public ResponseEntity<ResponseBody> postCors() {
        return new ResponseEntity<>(new ResponseBody("success", "CORS POST PASS 3"), HttpStatus.ACCEPTED);
    }

    @PutMapping("/cors/test")
    public ResponseEntity<ResponseBody> putCors() {
        return new ResponseEntity<>(new ResponseBody("success", "CORS PUT PASS 4"), HttpStatus.ACCEPTED);
    }

    @DeleteMapping("/cors/test")
    public ResponseEntity<ResponseBody> deleteCors() {
        return new ResponseEntity<>(new ResponseBody("success", "CORS DELETE PASS"), HttpStatus.ACCEPTED);
    }
}
