package com.example.back_end.controller.user;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.example.back_end.dto.UserDto;
import com.example.back_end.exceptions.ResourceNotFoundException;
import com.example.back_end.model.user;

import lombok.RequiredArgsConstructor;

import com.example.back_end.responses.ApiResponse;
import com.example.back_end.service.UserService;

import jakarta.validation.Valid;

@RequestMapping("/api/user")
@RestController
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;

    @PostMapping("")
    public ResponseEntity<ApiResponse> created(@Valid @RequestBody UserDto userDto, BindingResult Result) {

        if (Result.hasErrors()) {
            List<String> errors = Result.getFieldErrors().stream()
                    .map(FieldError::getDefaultMessage).toList();
            ApiResponse apiResponse = ApiResponse.builder()
                    .data(errors)
                    .message("Validation failed")
                    .status(HttpStatus.BAD_REQUEST.value())
                    .build();
            return ResponseEntity.badRequest().body(apiResponse);

        }
        user user = userService.save(userDto);
        ApiResponse apiResponse = ApiResponse.builder()
                .data(user)
                .message("User created successfully")
                .status(HttpStatus.OK.value())
                .build();
        return ResponseEntity.ok(apiResponse);
    }

    @GetMapping("")
    public ResponseEntity<ApiResponse> index() {
        List<user> users = userService.findAll();
        ApiResponse apiResponse = ApiResponse.builder()
                .data(users)
                .message("Get all users")
                .status(HttpStatus.OK.value())
                .build();
        return ResponseEntity.ok(apiResponse);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse> updateUser(@PathVariable Long id, @Valid @RequestBody UserDto userDto,
            BindingResult Result) {
        if (Result.hasErrors()) {
            List<String> errors = Result.getFieldErrors().stream()
                    .map(FieldError::getDefaultMessage).toList();
            ApiResponse apiResponse = ApiResponse.builder()
                    .data(errors)
                    .message("Validation failed")
                    .status(HttpStatus.BAD_REQUEST.value())
                    .build();
            return ResponseEntity.badRequest().body(apiResponse);

        }

        user user = userService.update(id, userDto);
        if (user == null) {
            throw new ResourceNotFoundException("Student không tìm thấy với id: " + id);

        }
        ApiResponse apiResponse = ApiResponse.builder()
                .data(user)
                .message("User updated successfully")
                .status(HttpStatus.OK.value())
                .build();
        return ResponseEntity.ok(apiResponse);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse> deleteUser(@PathVariable Long id) {
     user user = userService.findById(id);
        if (user == null) {
            throw new ResourceNotFoundException("user không tìm thấy với id : " + id);
        }
        userService.deleteById(id);
        ApiResponse apiResponse = ApiResponse.builder()
                .message("User deleted successfully")
                .status(HttpStatus.OK.value())
                .build();
        return ResponseEntity.ok(apiResponse);
    }

}
