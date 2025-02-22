package com.example.SecurityApp.SecurityApplication.DTO;


import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class LoginDTO {
    private  String email;
   private   String password;
}
