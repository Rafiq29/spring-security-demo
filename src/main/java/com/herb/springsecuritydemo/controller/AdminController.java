package com.herb.springsecuritydemo.controller;

import com.herb.springsecuritydemo.dto.request.AdminRequestDTO;
import com.herb.springsecuritydemo.dto.response.AdminResponseDTO;
import com.herb.springsecuritydemo.service.AdminService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/admin")
public class AdminController {

    private final AdminService service;

    @PostMapping
    public String add(@RequestHeader("Authorization") String auth,@RequestBody AdminRequestDTO requestDTO) {
        System.out.println(auth);
        service.addAdmin(requestDTO);
        return "User created!";
    }

    @GetMapping
    public List<AdminResponseDTO> getAll() {
        return service.getAllAdmins();
    }

    @GetMapping("/{id}")
    public AdminResponseDTO getByID(@PathVariable("id") Long id) {
        return service.getAdminByID(id);
    }

    @DeleteMapping("/{id}")
    public String deleteByID(@PathVariable("id") Long id) {
        service.deleteAdminByID(id);
        return "User deleted!";
    }
}
