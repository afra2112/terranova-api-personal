package com.terranova.api.v1.shared.security.model;

import com.terranova.api.v1.user.domain.model.User;
import com.terranova.api.v1.user.infrastructure.adapter.out.persistence.entity.UserEntity;
import lombok.Getter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import java.util.Collection;

@Getter
public class CustomUserDetails implements UserDetails {

    private final User user;

    public CustomUserDetails(User user) {
        this.user = user;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return user.roles().stream()
                .map(role -> new SimpleGrantedAuthority(String.valueOf(role))).toList();
    }

    @Override
    public String getPassword() {return user.password();}

    @Override
    public String getUsername() {return user.email();}

    @Override public boolean isAccountNonExpired() { return true; }
    @Override public boolean isAccountNonLocked() { return true; }
    @Override public boolean isCredentialsNonExpired() { return true; }
    @Override public boolean isEnabled() { return true; }
}
