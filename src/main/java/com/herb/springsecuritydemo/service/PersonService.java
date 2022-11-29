package com.herb.springsecuritydemo.service;

import com.herb.springsecuritydemo.dto.request.PersonRequestDTO;
import com.herb.springsecuritydemo.dto.response.PersonResponseDTO;
import com.herb.springsecuritydemo.entity.Person;
import com.herb.springsecuritydemo.error.CustomException;
import com.herb.springsecuritydemo.mapper.PersonMapper;
import com.herb.springsecuritydemo.repo.PersonRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class PersonService {
    private final PersonRepo repo;
    private final PersonMapper mapper;

    public void addUser(PersonRequestDTO requestDTO) {
        repo.save(mapper.toUser(requestDTO));
    }

    public PersonResponseDTO getUserByID(Long id) {
        return mapper.toDto(repo.findById(id)
                .orElseThrow(() -> new CustomException("User not found")));
    }

    public List<PersonResponseDTO> getAllUsers() {
        List<Person> people = repo.findAll();
        if (people.isEmpty()) throw new CustomException("Custom error");
        return people
                .stream()
                .filter(Person::isStatus)
                .map(mapper::toDto)
                .collect(Collectors.toList());
    }

    public void deleteUserByID(Long id) {
        Person person = repo.findById(id)
                .orElseThrow(() -> new CustomException("User not found"));
        person.setStatus(false);
        repo.save(person);
    }
}
