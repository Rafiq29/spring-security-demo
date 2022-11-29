package com.herb.springsecuritydemo.mapper;

import com.herb.springsecuritydemo.dto.request.PersonRequestDTO;
import com.herb.springsecuritydemo.dto.response.PersonResponseDTO;
import com.herb.springsecuritydemo.entity.Person;
import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring",
        injectionStrategy = InjectionStrategy.CONSTRUCTOR,
        unmappedTargetPolicy = ReportingPolicy.IGNORE,
        unmappedSourcePolicy = ReportingPolicy.IGNORE)
public interface PersonMapper {
    PersonResponseDTO toDto(Person person);

    Person toUser(PersonRequestDTO requestDTO);

    Person toUser(PersonResponseDTO responseDTO);
}
