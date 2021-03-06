package com.pbl2.pbl2.security;


import com.pbl2.pbl2.model.User;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;

public class UserDetailsImpl implements UserDetails {

    private final User user;

    public UserDetailsImpl(User user) {
        this.user = user;
    }

    public User getUser() {
        return user;
    }

    @Override
    public String getPassword() {
        return user.getUserPassword();
    }

    @Override
    public String getUsername() {
        return user.getUserEmail();
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    //    @Override
//    public Collection<? extends GrantedAuthority> getAuthorities() {
////        UserRoleEnum role = user.getRole();
////        String authority = role.getAuthority();
////
////        SimpleGrantedAuthority simpleGrantedAuthority = new SimpleGrantedAuthority(authority);
////        Collection<GrantedAuthority> authorities = new ArrayList<>();
////        authorities.add(simpleGrantedAuthority);
//
//        return Collections.emptyList();
//    }
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return null;
    }
}