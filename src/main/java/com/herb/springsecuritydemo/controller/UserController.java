package com.herb.springsecuritydemo.controller;

import com.herb.springsecuritydemo.dto.request.UserRequestDTO;
import com.herb.springsecuritydemo.dto.response.UserResponseDTO;
import com.herb.springsecuritydemo.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/user")
public class UserController {

    private final UserService service;

    @PostMapping("/add")
    public String add(@RequestBody UserRequestDTO requestDTO) {
        service.addUser(requestDTO);
        return "User created!";
    }

    @GetMapping("/all")
    public List<UserResponseDTO> getAll() {
        return service.getAllUsers();
    }

    @GetMapping("/{id}")
    public UserResponseDTO getByID(@PathVariable("id") Long id) {
        return service.getUserByID(id);
    }

    @DeleteMapping("/{id}")
    public String deleteByID(@PathVariable("id") Long id) {
        service.deleteUserByID(id);
        return "User deleted!";
    }
}
