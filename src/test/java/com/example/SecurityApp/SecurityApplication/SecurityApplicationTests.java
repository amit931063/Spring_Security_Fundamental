package com.example.SecurityApp.SecurityApplication;

import com.example.SecurityApp.SecurityApplication.Entities.User;
import com.example.SecurityApp.SecurityApplication.Services.JwtService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class SecurityApplicationTests {


	@Autowired
	private JwtService jwtService;

//	@Test
//	void contextLoads() {
//		User user= new User(1L,"amit123@gmail.com","amit123", "amit");
//		String token=jwtService.generateToken(user);
//		System.out.println(token);
//
//		Long id=jwtService.getUserIdFromToken(token);
//		System.out.println(id);


}
