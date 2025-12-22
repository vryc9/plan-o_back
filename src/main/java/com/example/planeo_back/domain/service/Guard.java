package com.example.planeo_back.domain.service;

import com.example.planeo_back.application.exception.BalanceExceptionHandler;
import com.example.planeo_back.application.exception.ExpenseExceptionHandler;
import com.example.planeo_back.web.DTO.BalanceDTO;
import com.example.planeo_back.web.DTO.ExpenseDTO;

import java.lang.reflect.Field;

public class Guard {
    private Guard(){}
    public static void checkIfObjectIsNull(Object obj) throws IllegalAccessException {
        Field[] fields = obj.getClass().getDeclaredFields();
        Class<?> clazz = obj.getClass();
        for (Field field : fields) {
            field.setAccessible(true);
            Object value = field.get(obj);
            if(value == null && !field.getName().equals("id")){
                handleNullException(clazz, field.getName());
            }
        }
    }
    private static void handleNullException(Class<?> clazz, String field) {
        if(clazz.equals(ExpenseDTO.class)) {
            throw new ExpenseExceptionHandler("La valeur du champ "+ field + " ne peut pas être vide");
        }
        else if (clazz.equals(BalanceDTO.class)) {
            throw new BalanceExceptionHandler("La valeur du champ \"+ field + \"ne peut pas être vide");
        }
    }
}
