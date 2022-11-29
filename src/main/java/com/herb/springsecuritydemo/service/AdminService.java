package com.herb.springsecuritydemo.service;

import com.herb.springsecuritydemo.dto.request.AdminRequestDTO;
import com.herb.springsecuritydemo.dto.response.AdminResponseDTO;
import com.herb.springsecuritydemo.entity.Admin;
import com.herb.springsecuritydemo.error.CustomException;
import com.herb.springsecuritydemo.mapper.AdminMapper;
import com.herb.springsecuritydemo.repo.AdminRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class AdminService {
    private final AdminRepo repo;
    private final AdminMapper mapper;

    public void addAdmin(AdminRequestDTO requestDTO) {
        repo.save(mapper.toAdmin(requestDTO));
    }

    public AdminResponseDTO getAdminByID(Long id) {
        return mapper.toDto(repo.findById(id)
                .orElseThrow(() -> new CustomException("User not found")));
    }

    public List<AdminResponseDTO> getAllAdmins() {
        List<Admin> admins = repo.findAll();
        if (admins.isEmpty()) throw new CustomException("Custom error");
        return admins
                .stream()
                .filter(Admin::isStatus)
                .map(mapper::toDto)
                .collect(Collectors.toList());
    }

    public void deleteAdminByID(Long id) {
        Admin admin = repo.findById(id)
                .orElseThrow(() -> new CustomException("User not found"));
        admin.setStatus(false);
        repo.save(admin);
    }
}
