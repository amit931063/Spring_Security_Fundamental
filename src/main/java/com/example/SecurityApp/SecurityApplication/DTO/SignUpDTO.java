package com.example.SecurityApp.SecurityApplication.DTO;

import com.example.SecurityApp.SecurityApplication.Entities.enums.Permission;
import com.example.SecurityApp.SecurityApplication.Entities.enums.Role;
import lombok.*;

import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class SignUpDTO {
    private  String email;
   private  String password;
     private String name;
     private Set<Role>roles;
     private Set<Permission>permissions;
}
