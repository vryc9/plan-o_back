package com.example.planeo_back.domain.ports;

import com.example.planeo_back.domain.entity.Expense;
import com.example.planeo_back.domain.entity.User;

public interface UserRepository extends IGenericCrudRepository<User> {
    User findUserByUsername(String username);
}
