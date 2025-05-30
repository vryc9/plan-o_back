package com.example.planeo_back.infrastructure.mapper;

import com.example.planeo_back.domain.entity.User;
import com.example.planeo_back.web.DTO.UserDTO;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UserMapperDTO {
    UserDTO toDTO(User user);
    User toEntity(UserDTO userDTO);
}