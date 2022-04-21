package com.pbl2.pbl2.controller;

import com.pbl2.pbl2.dto.PostDto;
import com.pbl2.pbl2.exception.NotFoundAuth;
import com.pbl2.pbl2.model.Post;
import com.pbl2.pbl2.model.User;
import com.pbl2.pbl2.responseEntity.ResponseBody;
import com.pbl2.pbl2.security.UserDetailsImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
@RestController
public class CORSController {

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
        return new ResponseEntity<>(new ResponseBody("success", "CORS POST PASS"), HttpStatus.ACCEPTED);
    }

    @PutMapping("/cors/test")
    public ResponseEntity<ResponseBody> putCors() {
        return new ResponseEntity<>(new ResponseBody("success", "CORS PUT PASS"), HttpStatus.ACCEPTED);
    }

    @DeleteMapping("/cors/test")
    public ResponseEntity<ResponseBody> deleteCors() {
        return new ResponseEntity<>(new ResponseBody("success", "CORS DELETE PASS"), HttpStatus.ACCEPTED);
    }
}
