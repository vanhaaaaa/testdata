package com.example.back_end.dto;

import java.time.LocalDate;



import com.fasterxml.jackson.annotation.JsonFormat;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Past;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserDto {
    
     @NotBlank(message = "Tên không được để trống")
    @Size(min = 2, max = 50, message = "Tên phải có từ 2 đến 50 ký tự")
    private String username;

    @NotBlank
    @Size(min = 6, max = 100, message = "Password phải là một password")
    private String password;
      @Past(message = "Ngày tháng năm sinh phải là một ngày trong quá khứ")
 @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate ngay_sinh;

    @Email(message = "Email phải là một email")
    private String email;
    @NotBlank(message = "Địa chiề phải là một điềa chiề")
    private String address;

    private String role;
}
