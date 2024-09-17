package com.example.back_end.service;

import java.util.List;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.back_end.dto.UserDto;
import com.example.back_end.model.user;
import com.example.back_end.reponsitories.UserReponsitories;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserService implements IuserService {
    private final UserReponsitories userReponsitories;
    @Override
    public void deleteById(Long id) {
        userReponsitories.deleteById(id);
    }

    @Override
    public List<user> findAll() {
       
        return userReponsitories.findAll();
    }

    @Override
    public user findById(Long id) {
       
        return userReponsitories.findById(id).get();
    }

    @Override
    public List<user> findByRole(String role) {
       return null;
        // return userReponsitories.findByRole(role);
    }

    @Override
    public user save(UserDto userDto) {
        PasswordEncoder passwordEncoder = new BCryptPasswordEncoder(10);
        user userExtring = user.builder()
                .username(userDto.getUsername())
                .password( passwordEncoder.encode( userDto.getPassword()))
                .ngay_sinh(userDto.getNgay_sinh())
                .email(userDto.getEmail())
                .address(userDto.getAddress())
                .role(userDto.getRole())
                .build();
      
        return userReponsitories.save(userExtring);
    }

    @Override
    public user update(Long id,UserDto userDto) {
       user userExisting = findById(id);
     
                userExisting.setUsername(userDto.getUsername());
                userExisting.setPassword(userDto.getPassword());
                userExisting.setNgay_sinh(userDto.getNgay_sinh());
                userExisting.setEmail(userDto.getEmail());
                userExisting.setAddress(userDto.getAddress());
                userExisting.setRole(userDto.getRole());
                return userReponsitories.save(userExisting);
       
       
    }
    
}
