package com.herb.springsecuritydemo.service;

import com.herb.springsecuritydemo.entity.User;
import lombok.Getter;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Getter
@Setter
public class UserDetailsImpl implements UserDetails {
    private String username;
    private String password;
    private Integer age;
    private Double salary;
    private Collection<? extends GrantedAuthority> authorities;

    public UserDetailsImpl(String username, String password, Integer age, Double salary, Collection<? extends GrantedAuthority> authorities) {
        this.username = username;
        this.password = password;
        this.age = age;
        this.salary = salary;
        this.authorities = authorities;
    }

    public static UserDetailsImpl create(User user) {
        List<GrantedAuthority> authoritiesList = new ArrayList<>();
        authoritiesList.add(new SimpleGrantedAuthority("user"));
        return new UserDetailsImpl(user.getUsername(), user.getPassword(), user.getAge(), user.getSalary(), authoritiesList);
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
