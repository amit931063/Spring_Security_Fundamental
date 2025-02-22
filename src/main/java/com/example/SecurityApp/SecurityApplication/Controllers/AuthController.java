package com.example.SecurityApp.SecurityApplication.Controllers;
import com.example.SecurityApp.SecurityApplication.DTO.LoginDTO;
import com.example.SecurityApp.SecurityApplication.DTO.SignUpDTO;
import com.example.SecurityApp.SecurityApplication.DTO.UserDTO;
import com.example.SecurityApp.SecurityApplication.Services.AuthService;
import com.example.SecurityApp.SecurityApplication.Services.UserService;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/auth")
public class AuthController {
    private  final UserService userService;
    private final AuthService authService;

    @PostMapping("/signUp")
    public ResponseEntity<UserDTO>SignUp(@RequestBody SignUpDTO signUpDTO){
        UserDTO user=userService.SignUp(signUpDTO);
        return ResponseEntity.ok(user);
    }
@PostMapping("/login")
    public ResponseEntity<String>LogIn(@RequestBody LoginDTO loginDTO, HttpServletRequest request, HttpServletResponse response){
        String token =authService.LogIn(loginDTO);
    Cookie cookie=new Cookie("token",token);
    cookie.setHttpOnly(true);
    response.addCookie(cookie);
        return ResponseEntity.ok(token);

}

}
