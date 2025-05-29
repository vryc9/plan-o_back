package com.example.planeo_back.web.DTO;

import com.example.planeo_back.domain.entity.User;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UserMapper {
    UserDTO toDTO(User user);

    User toEntity(UserDTO userDTO);
}
