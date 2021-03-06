package com.pbl2.pbl2.security;


import com.pbl2.pbl2.repository.UserRepository;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.transaction.Transactional;
import java.util.Base64;
import java.util.Date;
import java.util.List;

@Slf4j
@RequiredArgsConstructor
@Component
public class JwtTokenProvider {
    //    private String secretKey = "secret";
    private String SECRET_KEY = "PBL2-SECRET-KEY!@#$%^&*()1234567890";

    // 토큰 유효시간 30분
//    private long tokenValidTime = 30 * 60 * 1000L;
    private final long ACCESS_TOKEN_VALID_TIME = 24 * 60 * 60 * 1000L;   // 30분
    private final long REFRESH_TOKEN_VALID_TIME = 60 * 60 * 24 * 7 * 1000L;   // 1주

    private final UserDetailsService userDetailsService;
//    private final TokenRepository tokenRepository;
    private final UserRepository userRepository;

    // 객체 초기화, secretKey를 Base64로 인코딩한다.
    @PostConstruct
    protected void init() {
        SECRET_KEY = Base64.getEncoder().encodeToString(SECRET_KEY.getBytes());
    }

    // JWT 토큰 생성
    public String createAccessToken(String userPk) {
//        System.out.println(this.createToken("x", 10 * 60 * 60 * 24 * 365 * 1000L));
        return this.createToken(userPk, ACCESS_TOKEN_VALID_TIME);
    }

//    public String createRefreshToken(String userPk, List<String> roles) {
//        return this.createToken(userPk, roles, REFRESH_TOKEN_VALID_TIME);
//    }

    public String createToken(String userPk, long tokenValid) {
        Claims claims = Jwts.claims().setSubject(userPk); // JWT payload 에 저장되는 정보단위
//        claims.put("roles", roles); // 정보는 key / value 쌍으로 저장된다.
        Date now = new Date();
        return Jwts.builder()
                .setClaims(claims) // 정보 저장
                .setIssuedAt(now) // 토큰 발행 시간 정보
                .setExpiration(new Date(now.getTime() + tokenValid)) // set Expire Time
                .signWith(SignatureAlgorithm.HS256, SECRET_KEY)  // 사용할 암호화 알고리즘과
                // signature 에 들어갈 secret값 세팅
                .compact();

    }

    // JWT 토큰에서 인증 정보 조회
    public Authentication getAuthentication(String token) {
        UserDetails userDetails = userDetailsService.loadUserByUsername(this.getUserPk(token));

        return new UsernamePasswordAuthenticationToken(userDetails, "", userDetails.getAuthorities());
    }

    // 토큰에서 회원 정보 추출
    public String getUserPk(String token) {
        return Jwts.parser().setSigningKey(SECRET_KEY).parseClaimsJws(token).getBody().getSubject();
    }

    // Request의 Header에서 token 값을 가져옵니다. "X-AUTH-TOKEN" : "TOKEN값'
    public String resolveAccessToken(HttpServletRequest request) {
        if (request.getHeader("Authorization") != null)
            return request.getHeader("Authorization");
        return null;
    }

//    // Request의 Header에서 token 값을 가져옵니다. "X-AUTH-TOKEN" : "TOKEN값'
//    public String resolveRefreshToken(HttpServletRequest request) {
//        if (request.getHeader("REFRESH_TOKEN") != null)
//            return request.getHeader("REFRESH_TOKEN");
//        return null;
//    }

    // 토큰의 유효성 + 만료일자 확인
    public boolean validateToken(String jwtToken) {
        try {
            Jws<Claims> claims = Jwts.parser().setSigningKey(SECRET_KEY).parseClaimsJws(jwtToken);
            return !claims.getBody().getExpiration().before(new Date());
        } catch (Exception e) {
            System.out.println("validateToken false : " + jwtToken);
            return false;
        }
    }

    // 어세스 토큰 헤더 설정
    public void setHeaderAccessToken(HttpServletResponse response, String accessToken) {
        response.setHeader("Authorization", "Bearer " + accessToken);
    }
//
//    // 리프레시 토큰 헤더 설정
//    public void setHeaderRefreshToken(HttpServletResponse response, String refreshToken) {
//        response.setHeader("REFRESH_TOKEN", refreshToken);
//    }
//
//    // RefreshToken 존재유무 확인
//    public boolean existsRefreshToken(String refreshToken) {
//        return tokenRepository.existsByRefreshToken(refreshToken);
//    }
//    @Transactional
//    public void deleteRefreshToken(String refreshToken) {
//        tokenRepository.deleteByRefreshToken(refreshToken);
//    }
//
//    @Transactional
//    public void saveRefreshToken(String refreshToken){
//        tokenRepository.save(new RefreshToken(refreshToken));
//    }

//    // Email로 권한 정보 가져오기
//    public List<String> getRoles(String username) {
//        return userRepository.findByUsername(username).get().getRoles();
//    }
}
