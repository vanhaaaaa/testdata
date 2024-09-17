package com.example.back_end.responses;

import lombok.*;
import lombok.experimental.FieldDefaults;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@FieldDefaults(level  =AccessLevel.PRIVATE)
public class IntrospectReponses {
    boolean valid;
}
