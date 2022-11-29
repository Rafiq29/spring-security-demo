package com.herb.springsecuritydemo.mapper;

import com.herb.springsecuritydemo.dto.request.UserRequestDTO;
import com.herb.springsecuritydemo.dto.response.UserResponseDTO;
import com.herb.springsecuritydemo.entity.User;
import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.control.MappingControl;

@Mapper(componentModel = "spring",
        injectionStrategy = InjectionStrategy.CONSTRUCTOR,
        unmappedTargetPolicy = ReportingPolicy.IGNORE,
        unmappedSourcePolicy = ReportingPolicy.IGNORE)
public interface UserMapper {
    UserResponseDTO toDto(User user);

    User toUser(UserRequestDTO requestDTO);

    User toUser(UserResponseDTO responseDTO);
}
