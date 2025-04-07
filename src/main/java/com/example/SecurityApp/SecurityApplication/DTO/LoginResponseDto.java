package com.example.SecurityApp.SecurityApplication.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@AllArgsConstructor
@NoArgsConstructor
@Data
public class LoginResponseDto {

    private Long  id;
    private  String  accessToken ;
    private  String refreshToken;

}
