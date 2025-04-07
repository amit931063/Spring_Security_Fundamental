package com.example.SecurityApp.SecurityApplication.DTO;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class UserDTO {
   private Long id;
     private String email;
    private  String name;
}
