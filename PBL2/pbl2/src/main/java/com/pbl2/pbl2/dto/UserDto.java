package com.pbl2.pbl2.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;


public class UserDto {

    @Getter
    @Setter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class Request{
//        @NotBlank(message = "이메일은 필수 입력 값입니다.")
//        @Email(message = "이메일 형식에 맞지 않습니다.")
        private String user_email;

//        @NotBlank(message = "닉네임은 필수 입력 값입니다.")
//        @Pattern(regexp="^(?=.*[A-z0-9])[A-z0-9]{3,}$", message = "닉네임는 3자 이상의 영문자,숫자로 이루어진 문자열입니다")
        private String user_name;

//        @NotBlank(message = "비밀번호는 필수 입력 값입니다.")
//        @Pattern(regexp="^{4,}$", message = "비밀번호는 4자 이상의 비밀번호여야 합니다.")
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
