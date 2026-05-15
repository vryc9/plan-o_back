package com.example.planeo_back.application.service.balance;

import com.example.planeo_back.application.service.IGenericService;
import com.example.planeo_back.web.DTO.BalanceResponseDTO;
import com.example.planeo_back.web.DTO.balance.BalanceDTO;

public interface IBalanceService extends IGenericService<BalanceDTO, BalanceResponseDTO> {
    BalanceResponseDTO update();
    BalanceResponseDTO getBalance(String username);
    boolean balanceExistForUser();
}
