package com.example.back_end.controller.user;


import java.text.ParseException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;


import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.back_end.dto.AuthenticationDto;
import com.example.back_end.responses.ApiResponse;
import com.example.back_end.responses.AuthenticationResponses;
import com.example.back_end.responses.IntrospectReponses;
import com.example.back_end.responses.UserApiResponses;
import com.example.back_end.service.AuthenticationService;
import com.nimbusds.jose.JOSEException;
import com.example.back_end.dto.IntrospectDto;

import lombok.experimental.FieldDefaults;

@RestController
@RequestMapping("/api/auth")
@FieldDefaults(level = lombok.AccessLevel.PRIVATE)
public class AuthenticationController {
    @Autowired
    AuthenticationService authenticationService;

    @PostMapping("")
    public ApiResponse<AuthenticationResponses> authenticate(@RequestBody AuthenticationDto authenticationDto) {
        var result = authenticationService.authenticate(authenticationDto);
        return ApiResponse.<AuthenticationResponses>builder()
               .data(result)
               .status(HttpStatus.OK.value())
               .message("Login successfully")
               .build();
    }
    @PostMapping("/introspect")
    public ApiResponse<IntrospectReponses> authenticate(@RequestBody IntrospectDto introspectDto) throws ParseException, JOSEException {
        var result = authenticationService.introspectReponses(introspectDto);
        return ApiResponse.<IntrospectReponses>builder()
               .data(result)
               
               .build();
    }
   


}
