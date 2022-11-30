package com.herb.springsecuritydemo.service;

import com.herb.springsecuritydemo.dto.request.UserRequestDTO;
import com.herb.springsecuritydemo.dto.response.UserResponseDTO;
import com.herb.springsecuritydemo.entity.User;
import com.herb.springsecuritydemo.error.CustomException;
import com.herb.springsecuritydemo.mapper.UserMapper;
import com.herb.springsecuritydemo.repo.UserRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class JwtUserDetailsService implements UserDetailsService {

    @Autowired
    private UserRepo repo;
    @Autowired
    private PasswordEncoder encoder;
    @Autowired
    private UserMapper mapper;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = repo.findUserByUsername(username);
        if (user == null) {
            throw new UsernameNotFoundException("User Not Found!");
        }
        return new UserDetailsImpl(user.getUsername(), user.getPassword(), user.getAge(), user.getSalary(), new ArrayList<>());
    }

    public User addUser(UserRequestDTO requestDTO) {
        User user = new User();
        if (repo.findUserByUsername(requestDTO.getUsername()) == null) {
            user.setUsername(requestDTO.getUsername());
            user.setPassword(encoder.encode(requestDTO.getPassword()));
            user.setAge(requestDTO.getAge());
            user.setSalary(requestDTO.getSalary());
        } else {
            throw new CustomException("User already exists!");
        }
        return repo.save(user);
    }

    public List<UserResponseDTO> getAllUsers() {
        List<User> users = repo.findAll();
        if (users.isEmpty()) throw new CustomException("Not Found!");
        return users.stream()
                .map(user -> mapper.toDto(user))
                .collect(Collectors.toList());
    }
}
