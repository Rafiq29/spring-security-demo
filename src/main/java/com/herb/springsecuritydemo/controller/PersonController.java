package com.herb.springsecuritydemo.controller;

import com.herb.springsecuritydemo.dto.request.PersonRequestDTO;
import com.herb.springsecuritydemo.dto.response.PersonResponseDTO;
import com.herb.springsecuritydemo.service.PersonService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/user")
public class PersonController {

    private final PersonService service;

    @PostMapping("/add")
    public String add(@RequestBody PersonRequestDTO requestDTO) {
        service.addUser(requestDTO);
        return "User created!";
    }

    @GetMapping("/all")
    public List<PersonResponseDTO> getAll() {
        return service.getAllUsers();
    }

    @GetMapping("/{id}")
    public PersonResponseDTO getByID(@PathVariable("id") Long id) {
        return service.getUserByID(id);
    }

    @DeleteMapping("/{id}")
    public String deleteByID(@PathVariable("id") Long id) {
        service.deleteUserByID(id);
        return "User deleted!";
    }
}
