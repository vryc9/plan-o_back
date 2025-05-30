package com.example.planeo_back.application.service.user;

import com.example.planeo_back.application.service.IGenericService;
import com.example.planeo_back.web.DTO.UserDTO;

public interface IUserService extends IGenericService<UserDTO> {
    UserDTO getUser(String username);
}
