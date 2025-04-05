package com.example.SecurityApp.SecurityApplication.Services;

import com.example.SecurityApp.SecurityApplication.DTO.LoginDTO;
import com.example.SecurityApp.SecurityApplication.DTO.LoginResponseDto;
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
    private final UserService userService;
    private final SessionService sessionService;


    public LoginResponseDto LogIn(LoginDTO loginDTO) {
        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(loginDTO.getEmail(), loginDTO.getPassword()));
        User user = (User) authentication.getPrincipal();
        String AccessToken = jwtService.generateAccessToken(user);
        String RefreshToken = jwtService.generateRefreshToken(user);
        sessionService.generateNewSession(user,RefreshToken);
        return  new LoginResponseDto(user.getId(),AccessToken,RefreshToken);

    }

    public LoginResponseDto refreshToken(String refreshToken) {
        Long userId=jwtService.getUserIdFromToken(refreshToken);
        sessionService.validateSession(refreshToken);

        User user= userService.getUserById(userId);
        String accessToken= jwtService.generateAccessToken(user);
     return new LoginResponseDto(user.getId(),accessToken,refreshToken);


    }
}
