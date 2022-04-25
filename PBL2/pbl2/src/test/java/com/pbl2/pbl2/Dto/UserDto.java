//package com.pbl2.pbl2.Dto;
//
//import com.pbl2.pbl2.dto.UserDto;
//import org.assertj.core.api.Assertions;
//import org.junit.jupiter.api.AfterAll;
//import org.junit.jupiter.api.BeforeAll;
//import org.junit.jupiter.api.DisplayName;
//import org.junit.jupiter.api.Test;
//
//import javax.validation.Validation;
//import javax.validation.ValidatorFactory;
//
//public class UserDtoTest {
//    @Test
//    @DisplayName("정상 케이스")
//    void testUserDto() {
//        //given
//        String user_email = "funnykyeon@naver.com";
//        String user_name = "8282";
//        String user_password = "1234aaaa";
//        String user_password_check = "1234aaaa";
//
//        //when
//        UserDto userDto = new UserDto.Request(user_email, user_name, user_password, user_password_check);
//
//        //then
//        Assertions.assertThat(userDto)
//        assertEquals(user_email, userDto.getUser_email());
//        assertEquals(user_name, userDto.getUser_name());
//        assertEquals(user_password, userDto.getUser_password());
//        assertEquals(user_password_check, userDto.getUser_password_check());
//    }
//
//    private static ValidatorFactory validatorFactory;
//    private static Validator validator;
//
//    @BeforeAll
//    public static void init() {
//        validatorFactory = Validation.buildDefaultValidatorFactory();
//        validator = validatorFactory.getValidator();
//    }
//
//    @AfterAll
//    public static void close() {
//        validatorFactory.close();
//    }
//
//    @Test
//    @DisplayName("유효성 논리 동작여부")
//    public void testUserDto_Valid() {
//        UserDto.Request dto = new UserDto.Request();
//        dto.setUser_email("ucom@asd.com");
//        dto.setUser_name("username");
//        dto.setUser_password("password");
////        dto.setUser_password_check("password");
//
//
//        Set<ConstraintViolation<UserDto.Request>> violations = validator.validate(dto);
//
//        Assertions.assertThat(violations).isEmpty();
//    }
//}
