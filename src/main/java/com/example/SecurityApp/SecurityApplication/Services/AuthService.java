package com.example.SecurityApp.SecurityApplication.Services;

import com.example.SecurityApp.SecurityApplication.DTO.LoginDTO;
import com.example.SecurityApp.SecurityApplication.Entities.User;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthService {
    private final AuthenticationManager authenticationManager;
    private final JwtService jwtService;


    public  String LogIn(LoginDTO loginDTO) {
      Authentication authentication= authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(loginDTO.getEmail(),loginDTO.getPassword()));
User user=(User) authentication.getPrincipal();
 return jwtService.generateToken(user);
   }


}
