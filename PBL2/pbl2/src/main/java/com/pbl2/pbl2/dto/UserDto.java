package com.pbl2.pbl2.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;


public class UserDto {

    @Getter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class Request{
        @NotBlank @Email
        private String user_email;

        @NotBlank @Pattern(regexp="^(?=.*[A-z0-9])[A-z0-9]{3,}$")
        private String user_name;

        @NotBlank @Size(min=4)
        private String user_password;

        private String user_password_check;
    }

    @Getter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class LoginRequest{
        @NotBlank(message = "아이디를 입력해주세요.")
        private String user_email;

        @NotBlank(message = "비밀번호를 입력해주세요.")
        private String user_password;
    }


    @Getter
    @Setter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class Response{
        private Long user_id;
        private String user_email;
        private String user_name;
    }

}
