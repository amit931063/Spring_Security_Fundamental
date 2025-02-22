package com.example.SecurityApp.SecurityApplication.DTO;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class SignUpDTO {
    private  String email;
   private  String password;
     private String name;
}
