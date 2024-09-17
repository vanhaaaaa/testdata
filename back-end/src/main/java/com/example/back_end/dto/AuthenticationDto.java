package com.example.back_end.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AuthenticationDto {
    @NotBlank(message = "Email phải là một email")
     String email;
    @NotBlank(message = "Password phải là một password")
     String password;
}
