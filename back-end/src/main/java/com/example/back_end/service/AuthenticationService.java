package com.example.back_end.service;

import java.text.ParseException;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.back_end.dto.AuthenticationDto;
import com.example.back_end.dto.IntrospectDto;
import com.example.back_end.model.user;
import com.example.back_end.reponsitories.UserReponsitories;
import com.example.back_end.responses.AuthenticationResponses;
import com.example.back_end.responses.IntrospectReponses;
import com.nimbusds.jose.JOSEException;
import com.nimbusds.jose.JWEHeader;
import com.nimbusds.jose.JWSAlgorithm;
import com.nimbusds.jose.JWSHeader;
import com.nimbusds.jose.JWSObject;
import com.nimbusds.jose.JWSVerifier;
import com.nimbusds.jose.Payload;
import com.nimbusds.jose.crypto.MACSigner;
import com.nimbusds.jose.crypto.MACVerifier;
import com.nimbusds.jwt.JWTClaimsSet;
import com.nimbusds.jwt.SignedJWT;

import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.experimental.NonFinal;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = lombok.AccessLevel.PRIVATE)
public class AuthenticationService {
       @Autowired
    UserReponsitories userReponsitories;
        @NonFinal
        @Value("${jwt.signkey}")
        protected String SECRET_KEY;

public IntrospectReponses introspectReponses(IntrospectDto introspectDto) throws ParseException, JOSEException {
    var token = introspectDto.getToken();
   
    JWSVerifier jwsVerifier = new MACVerifier(SECRET_KEY.getBytes());
    SignedJWT signedJWT = SignedJWT.parse(token);

    Date expityTime = signedJWT.getJWTClaimsSet().getExpirationTime();
   var verifyed = signedJWT.verify(jwsVerifier);

   return IntrospectReponses.builder().valid(verifyed && expityTime.after(new Date())).build();

}

    public AuthenticationResponses authenticate(AuthenticationDto authenticationDto) {
        user user = userReponsitories.findByEmail(authenticationDto.getEmail()).orElseThrow(() -> new RuntimeException("User not found"));

        PasswordEncoder passwordEncoder = new BCryptPasswordEncoder(10);
    boolean authenticated =  passwordEncoder.matches(authenticationDto.getPassword(), user.getPassword());

      if(!authenticated)
        throw new RuntimeException("Wrong password");
      var token = generateToken(authenticationDto.getEmail());
      return AuthenticationResponses.builder().token(token).authenticate(true).build();
    }
    
 

private String generateToken(String email) {
            JWSHeader header = new JWSHeader(JWSAlgorithm.HS512);
            JWTClaimsSet jwtClaimsSet = new JWTClaimsSet.Builder()
                    .subject(email)
                    .issuer(email)
                    .issueTime(new Date())
                    .expirationTime(new Date( Instant.now().plus(1, ChronoUnit.HOURS).toEpochMilli()))
                    .build();
        Payload payload = new Payload(jwtClaimsSet.toJSONObject());
                    JWSObject jwsObject = new JWSObject(header, payload);
   try{
    jwsObject.sign(new MACSigner(SECRET_KEY.getBytes()));
    return jwsObject.serialize();
   }
   catch(Exception e){
   throw new RuntimeException(e);
   }
                  
                }
    }