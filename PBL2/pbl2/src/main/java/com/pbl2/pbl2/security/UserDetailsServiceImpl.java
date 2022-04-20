//package com.pbl2.pbl2.security;
//
//
//import com.pbl2.pbl2.exception.RestException;
//import com.pbl2.pbl2.model.User;
//import com.pbl2.pbl2.repository.UserRepository;
//import lombok.RequiredArgsConstructor;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.http.HttpStatus;
//import org.springframework.security.core.userdetails.UserDetails;
//import org.springframework.security.core.userdetails.UserDetailsService;
//import org.springframework.security.core.userdetails.UsernameNotFoundException;
//import org.springframework.stereotype.Service;
//
//@Service
//@RequiredArgsConstructor
//public class UserDetailsServiceImpl implements UserDetailsService {
//
//    private final UserRepository userRepository;
//
//    //TODO:에러 메세지 출력 확인
//    public UserDetails loadUserByUsername(String username) {
//        return userRepository.findByUserName(username)
//                .orElseThrow(() -> new RestException(HttpStatus.BAD_REQUEST, "해당 사용자를 찾을 수 없습니다."));
//    }
//}

package com.pbl2.pbl2.security;


        import com.pbl2.pbl2.model.User;
        import com.pbl2.pbl2.repository.UserRepository;
        import org.springframework.beans.factory.annotation.Autowired;
        import org.springframework.security.core.userdetails.UserDetails;
        import org.springframework.security.core.userdetails.UserDetailsService;
        import org.springframework.security.core.userdetails.UsernameNotFoundException;
        import org.springframework.stereotype.Service;

        import java.util.Optional;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    private final UserRepository userRepository;

    @Autowired
    public UserDetailsServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        System.out.println(username);
        Optional<User> user = userRepository.findByUserName(username);
        return user.map(UserDetailsImpl::new).orElseGet(() -> new UserDetailsImpl(new User("fail")));
    }
}