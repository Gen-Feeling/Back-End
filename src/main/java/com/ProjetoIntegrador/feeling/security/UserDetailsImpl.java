package com.ProjetoIntegrador.feeling.security;

/*
 * implementação do UserDetails do spring security
 * the code implements Spring Security to UserDetails
 */

import java.util.Collection;

import com.ProjetoIntegrador.feeling.model.UserModel;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

public class UserDetailsImpl implements UserDetails {

    private static final long serialVersionUID = 1L; 

    private String email;
    private String password;
    

    public UserDetailsImpl(UserModel user) {
        this.email = user.getEmail();
        this.password = user.getPassword();
    }

    public UserDetailsImpl () {}

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return null;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return email;
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

    
    
}
