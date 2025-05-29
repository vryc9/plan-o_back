package com.example.planeo_back.application.service;

import com.example.planeo_back.domain.entity.User;
import com.example.planeo_back.domain.ports.UserRepository;
import com.example.planeo_back.web.DTO.UserDTO;
import com.example.planeo_back.web.DTO.UserMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;

@Service
public class UserService {

    private final UserRepository repository;
    private final UserMapper mapper;

    public UserService(UserRepository repository, UserMapper mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }

    public UserDTO getUserById(Long id) {
        return repository.findById(id)
                .map(mapper::toDTO)
                .orElseThrow(() -> new NoSuchElementException("User not found"));
    }

    public List<UserDTO> getAllUsers() {
        return repository.findAll()
                .stream()
                .map(mapper::toDTO)
                .toList();
    }

    public UserDTO createUser(UserDTO dto) {
        User user = mapper.toEntity(dto);
        return mapper.toDTO(repository.save(user));
    }

    public void deleteUser(UserDTO userDTO) {
        User user = mapper.toEntity(userDTO);
        repository.delete(user);
    }
}
