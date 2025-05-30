package com.example.planeo_back.infrastructure.mapper;

import com.example.planeo_back.domain.entity.Balance;
import com.example.planeo_back.web.DTO.BalanceDTO;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface BalanceMapper {
    BalanceDTO toDTO(Balance balance);

    Balance toEntity(BalanceDTO balanceDTO);
}
