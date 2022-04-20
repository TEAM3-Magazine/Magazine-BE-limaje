package com.pbl2.pbl2.security;

import com.pbl2.pbl2.exception.NotFoundAuth;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@RequiredArgsConstructor
public class JwtAuthenticationFilter extends OncePerRequestFilter {

    private final JwtTokenProvider jwtTokenProvider;

    @Override
    public void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws IOException, ServletException {
        // 헤더에서 JWT 를 받아옵니다.
        String accessToken = jwtTokenProvider.resolveAccessToken(request);
//        String refreshToken = jwtTokenProvider.resolveRefreshToken(request);
        // 유효한 토큰인지 확인합니다.
        if (accessToken != null) {
            if (jwtTokenProvider.validateToken(accessToken)) {
                this.setAuthentication(accessToken);
            }
//            else if (!jwtTokenProvider.validateToken(accessToken) && refreshToken != null) {
//                // 재발급 후, 컨텍스트에 다시 넣기
//                /// 리프레시 토큰 검증
//                boolean validateRefreshToken = jwtTokenProvider.validateToken(refreshToken);
//                /// 리프레시 토큰 저장소 존재유무 확인
//                boolean isRefreshToken = jwtTokenProvider.existsRefreshToken(refreshToken);
//                if (validateRefreshToken && isRefreshToken) {
//                    /// 리프레시 토큰으로 user 정보 가져오기
//                    String username = jwtTokenProvider.getUserPk(refreshToken);
//
//                    /// 토큰 발급
//                    String newAccessToken = jwtTokenProvider.createAccessToken(username, roles);
//                    /// 헤더에 어세스 토큰 추가
//                    jwtTokenProvider.setHeaderAccessToken(response, newAccessToken);
//                    /// 컨텍스트에 넣기
//                    this.setAuthentication(newAccessToken);
//
//                }
//            }
        }
        chain.doFilter(request, response);
    }

    // SecurityContext 에 Authentication 객체를 저장합니다.
    public void setAuthentication(String token) {
        // 토큰으로부터 유저 정보를 받아옵니다.
        Authentication authentication = jwtTokenProvider.getAuthentication(token);
        // SecurityContext 에 Authentication 객체를 저장합니다.
        SecurityContextHolder.getContext().setAuthentication(authentication);
    }
}
