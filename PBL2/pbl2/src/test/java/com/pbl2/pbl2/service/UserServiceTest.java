package com.pbl2.pbl2.service;

import com.pbl2.pbl2.dto.TokenDto;
import com.pbl2.pbl2.dto.UserDto;
import com.pbl2.pbl2.model.User;
import com.pbl2.pbl2.repository.UserRepository;
import com.pbl2.pbl2.security.JwtTokenProvider;
import com.pbl2.pbl2.security.UserDetailsServiceImpl;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.AdditionalAnswers;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.List;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;


@ExtendWith(MockitoExtension.class)
class UserServiceTest {
    @Mock
    UserRepository userRepository;
    @Mock
    UserDetailsServiceImpl userDetailsService;
    @Mock
    JwtTokenProvider jwtTokenProvider;
    PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();


    @Test
    @DisplayName("회원가입 테스트")
    void createSignup() {
        // given
        UserService userService = new UserService(userRepository,passwordEncoder, jwtTokenProvider);
        String email = "aaa@aaa";
        String nickname = "aaaa";
        String password = "asdf";
        String test = email.substring(0,email.length()/2);

        UserDto.Request userRequest = new UserDto.Request(email,nickname,password,password);
        User user = new User(userRequest);
        when(userRepository.save(any(User.class))).then(AdditionalAnswers.returnsFirstArg());

        // when
        User user1 = userService.registerUser(userRequest);

        // then
        Assertions.assertThat(user1.getUserEmail()).isEqualTo(email);
        Assertions.assertThat(passwordEncoder.matches(password,user1.getUserPassword())).isTrue();
        Assertions.assertThat(user1.getUserName()).isEqualTo(nickname);
    }

    @Test
    @DisplayName("로그인 테스트")
    void login() {
        // given
        UserService userService = new UserService(userRepository,passwordEncoder, jwtTokenProvider);
        String email = "aaa@aaa";
        String nickname = "aaaa";
        String password = "asdf";
        String test = email.substring(0,email.length()/2);

        UserDto.Request userRequest = new UserDto.Request(email,nickname,password,password);
        User user = new User(userRequest);
        when(userRepository.save(any(User.class))).then(AdditionalAnswers.returnsFirstArg());
        User user1 = userService.registerUser(userRequest);

        when(userRepository.findByUserEmail(any(String.class))).thenReturn(Optional.of(user1));
        when(jwtTokenProvider.createAccessToken(any(String.class))).thenReturn("Token");


        // when
        TokenDto.Response response = userService.login(new UserDto.LoginRequest(email, password));

        // then
        Assertions.assertThat(response.getToken()).isEqualTo("Token");
    }


}

//import com.sparta.springcore.dto.ProductMypriceRequestDto;
//import com.sparta.springcore.dto.ProductRequestDto;
//import com.sparta.springcore.model.Product;
//import com.sparta.springcore.repository.ProductRepository;
//import org.junit.jupiter.api.DisplayName;
//import org.junit.jupiter.api.Test;
//import org.junit.jupiter.api.extension.ExtendWith;
//import org.mockito.Mock;
//import org.mockito.junit.jupiter.MockitoExtension;
//
//import java.util.Optional;
//
//import static com.sparta.springcore.service.ProductService.MIN_MY_PRICE;
//import static org.junit.jupiter.api.Assertions.assertEquals;
//import static org.junit.jupiter.api.Assertions.assertThrows;
//import static org.mockito.Mockito.when;
//
//    @ExtendWith(MockitoExtension.class)
//    class ProductServiceTest {
//        @Mock
//        ProductRepository productRepository;
//
//        @Test
//        @DisplayName("관심 상품 희망가 - 최저가 이상으로 변경")
//        void updateProduct_Normal() {
//            // given
//            Long productId = 100L;
//            int myprice = MIN_MY_PRICE + 1000;
//
//            ProductMypriceRequestDto requestMyPriceDto = new ProductMypriceRequestDto(
//                    myprice
//            );
//
//            Long userId = 777L;
//            ProductRequestDto  requestProductDto = new ProductRequestDto(
//                    "오리온 꼬북칩 초코츄러스맛 160g",
//                    "https://shopping-phinf.pstatic.net/main_2416122/24161228524.20200915151118.jpg",
//                    "https://search.shopping.naver.com/gate.nhn?id=24161228524",
//                    2350
//            );
//
//            Product product = new Product(requestProductDto, userId);
//
//            ProductService productService = new ProductService(productRepository);
//            when(productRepository.findById(productId))
//                    .thenReturn(Optional.of(product));
//
//            // when
//            Product result = productService.updateProduct(productId, requestMyPriceDto);
//
//            // then
//            assertEquals(myprice, result.getMyprice());
//        }
//
//        @Test
//        @DisplayName("관심 상품 희망가 - 최저가 미만으로 변경")
//        void updateProduct_Failed() {
//            // given
//            Long productId = 100L;
//            int myprice = MIN_MY_PRICE - 50;
//
//            ProductMypriceRequestDto requestMyPriceDto = new ProductMypriceRequestDto(
//                    myprice
//            );
//
//            ProductService productService = new ProductService(productRepository);
//
//            // when
//            Exception exception = assertThrows(IllegalArgumentException.class, () -> {
//                productService.updateProduct(productId, requestMyPriceDto);
//            });
//
//            // then
//            assertEquals(
//                    "유효하지 않은 관심 가격입니다. 최소 " + MIN_MY_PRICE + " 원 이상으로 설정해 주세요.",
//                    exception.getMessage()
//            );
//        }
//    }