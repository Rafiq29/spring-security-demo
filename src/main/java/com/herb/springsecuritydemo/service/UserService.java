package com.herb.springsecuritydemo.service;

import com.herb.springsecuritydemo.dto.request.UserRequestDTO;
import com.herb.springsecuritydemo.dto.response.UserResponseDTO;
import com.herb.springsecuritydemo.entity.User;
import com.herb.springsecuritydemo.error.CustomException;
import com.herb.springsecuritydemo.mapper.UserMapper;
import com.herb.springsecuritydemo.repo.UserRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepo repo;
    private final UserMapper mapper;

    public void addUser(UserRequestDTO requestDTO) {
        repo.save(mapper.toUser(requestDTO));
    }

    public UserResponseDTO getUserByID(Long id) {
        return mapper.toDto(repo.findById(id)
                .orElseThrow(() -> new CustomException("User not found")));
    }

    public List<UserResponseDTO> getAllUsers() {
        List<User> users = repo.findAll();
        if (users.isEmpty()) throw new CustomException("Custom error");
        return users
                .stream()
                .filter(User::isStatus)
                .map(mapper::toDto)
                .collect(Collectors.toList());
    }

    public void deleteUserByID(Long id) {
        User user = repo.findById(id)
                .orElseThrow(() -> new CustomException("User not found"));
        user.setStatus(false);
        repo.save(user);
    }
}
