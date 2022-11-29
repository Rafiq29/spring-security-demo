package com.herb.springsecuritydemo.mapper;

import com.herb.springsecuritydemo.dto.request.AdminRequestDTO;
import com.herb.springsecuritydemo.dto.response.AdminResponseDTO;
import com.herb.springsecuritydemo.entity.Admin;
import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring",
        injectionStrategy = InjectionStrategy.CONSTRUCTOR,
        unmappedTargetPolicy = ReportingPolicy.IGNORE,
        unmappedSourcePolicy = ReportingPolicy.IGNORE)
public interface AdminMapper {
    AdminResponseDTO toDto(Admin admin);

    Admin toAdmin(AdminRequestDTO requestDTO);

    Admin toAdmin(AdminResponseDTO responseDTO);
}
