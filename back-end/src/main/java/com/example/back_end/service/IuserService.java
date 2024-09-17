package com.example.back_end.service;

import java.util.List;

import com.example.back_end.dto.UserDto;
import com.example.back_end.model.user;

public interface IuserService {

    user save(UserDto userDto);
        user findById(Long id);
        void deleteById(Long id);
        List<user> findAll();
        user update(Long id,UserDto userDto);
        List <user> findByRole(String role);
}
