package com.example.demo;


import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.example.entities.User;
import com.example.model.Usuario;
import com.example.repo.IUserRepository;

@SpringBootTest
public class DemoApplicationTests {
	
	@Autowired
	private IUserRepository repo;
	
	private BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
	
	@Test
	public void UsuarioTest() {//creo usuario
		User u = new User();
		u.setId(1);
		u.setUsername("admin");
		
		u.setPassword(encoder.encode("12345"));//contraseña codificada en la bd
		repo.save(u);
		
	}
}
