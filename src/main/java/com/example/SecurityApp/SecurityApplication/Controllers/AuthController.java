package com.example.SecurityApp.SecurityApplication.Controllers;
import com.example.SecurityApp.SecurityApplication.DTO.LoginDTO;
import com.example.SecurityApp.SecurityApplication.DTO.LoginResponseDto;
import com.example.SecurityApp.SecurityApplication.DTO.SignUpDTO;
import com.example.SecurityApp.SecurityApplication.DTO.UserDTO;
import com.example.SecurityApp.SecurityApplication.Services.AuthService;
import com.example.SecurityApp.SecurityApplication.Services.UserService;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationServiceException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;

@RestController
@RequiredArgsConstructor
@RequestMapping("/auth")
public class AuthController {
    private  final UserService userService;
    private final AuthService authService;
//    @Value("${deploy.env}")
//    private  String  deployEnv;

    @PostMapping("/signUp")
    public ResponseEntity<UserDTO>SignUp(@RequestBody SignUpDTO signUpDTO){
        UserDTO user=userService.SignUp(signUpDTO);
        return ResponseEntity.ok(user);
    }
@PostMapping("/login")
    public ResponseEntity<LoginResponseDto>LogIn(@RequestBody LoginDTO loginDTO, HttpServletRequest request, HttpServletResponse response){
        LoginResponseDto loginResponseDto =authService.LogIn(loginDTO);
    Cookie cookie=new Cookie("refreshToken",loginResponseDto.getRefreshToken());
    cookie.setHttpOnly(true);
//cookie.setSecure("production".equals(deployEnv));
    response.addCookie(cookie);
        return ResponseEntity.ok(loginResponseDto);

}

@PostMapping("/refresh")
    public ResponseEntity<LoginResponseDto>refresh(HttpServletRequest request){
        String refreshToken = Arrays.stream(request.getCookies()).
                filter(cookie ->"refreshToken".equals(cookie.getName()))
                .findFirst()
                .map(Cookie::getValue)
                .orElseThrow(()->new AuthenticationServiceException("refresh token is not found inside the co okies "));

    LoginResponseDto loginResponseDto =authService.refreshToken(refreshToken);
    return ResponseEntity.ok(loginResponseDto);
}



}
