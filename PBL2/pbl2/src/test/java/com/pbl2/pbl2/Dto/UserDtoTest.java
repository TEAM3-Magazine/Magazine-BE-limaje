package com.pbl2.pbl2.Dto;

import com.pbl2.pbl2.dto.UserDto;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import javax.validation.ConstraintViolation;
import javax.validation.*;
import java.util.Set;

public class UserDtoTest {
    @Test
    @DisplayName("정상 케이스")
    void testUserDto() {
        //given
        String user_email = "funnykyeon@naver.com";
        String user_name = "8282";
        String user_password = "1234aaaa";
        String user_password_check = "1234aaaa";

        //when
        UserDto.Request request = new UserDto.Request(user_email, user_name, user_password, user_password_check);

        //then
        Assertions.assertThat(request.getUser_email()).isEqualTo(user_email);
        Assertions.assertThat(request.getUser_name()).isEqualTo(user_name);
        Assertions.assertThat(request.getUser_password()).isEqualTo(user_password);
        Assertions.assertThat(request.getUser_password_check()).isEqualTo(user_password_check);
    }

    private static ValidatorFactory validatorFactory;
    private static Validator validator;

    @BeforeAll
    public static void init() {
        validatorFactory = Validation.buildDefaultValidatorFactory();
        validator = validatorFactory.getValidator();
    }

    @AfterAll
    public static void close() {
        validatorFactory.close();
    }

    @Test
    @DisplayName("유효성 검사")
    public void testUserDto_Valid() {
        String user_email = "aaa@aaa";
        String user_name = "8282";
        String user_password = "asdf";
        String user_password_check = "asdf";
        UserDto.Request request = new UserDto.Request(user_email,user_name,user_password,user_password_check);

        Set<ConstraintViolation<UserDto.Request>> violations = validator.validate(request);

        Assertions.assertThat(violations).isEmpty();
    }


    @Test
    @DisplayName("유효성 검사(오류)")
    public void testUserDto_Valid2() {
        String user_email = "aaa@aaa";
        String user_name = "8282";
//        password err
        String user_password = "asd";
        String user_password_check = "asd";

        UserDto.Request request = new UserDto.Request(user_email,user_name,user_password,user_password_check);

        Set<ConstraintViolation<UserDto.Request>> violations = validator.validate(request);

        Assertions.assertThat(violations).isNotEmpty();
    }
}
