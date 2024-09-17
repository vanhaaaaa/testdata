package com.example.back_end.responses;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserApiResponses<T> {
    private T data;
    private String message;
    private int status;
    
}
