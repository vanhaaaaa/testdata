package com.example.back_end.dto;

import lombok.*;
import lombok.experimental.FieldDefaults;


@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@FieldDefaults(level  =AccessLevel.PRIVATE)
public class IntrospectDto {
     String token;
}
