package com.zenthrex.security.model;

import com.zenthrex.core.entites.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.experimental.SuperBuilder;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.stream.Collectors;

public class Account extends User implements UserDetails {

    public Account(User user) {
        super();
        this.setId(user.getId());
        this.setFirstname(user.getFirstname());
        this.setLastname(user.getLastname());
        this.setPhone(user.getPhone());
        this.setEmail(user.getEmail());
        this.setPassword(user.getPassword());
        this.setStatus(user.getStatus());
        this.setRole(user.getRole());
        this.setTokens(user.getTokens());
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        var authorities = super.getRole().getPermissions().stream()
                .map(permission -> new SimpleGrantedAuthority(permission.getPermission()))
                .collect(Collectors.toList());
        authorities.add(new SimpleGrantedAuthority("ROLE_" + super.getRole().name()));
        return authorities;
    }

    @Override
    public String getUsername() {
        return super.getEmail();
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

    @Override
    protected Object clone() throws CloneNotSupportedException {
        return super.clone();
    }

    @Override
    public String getPassword() {
        return super.getPassword();
    }
}
