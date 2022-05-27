package com.example.demo;


import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.example.model.Usuario;
import com.example.repo.IUsuarioRepo;

@SpringBootTest
public class DemoApplicationTests {

	private IUsuarioRepo repo;
	
	private BCryptPasswordEncoder encoder;
	
@Test
	public void UsuarioTest() {//creo usuario
		Usuario u = new Usuario();
		u.setId(1);
		u.setUsuario("admin");
		u.setClave(encoder.encode("12345"));//contraseña codificada en la bd
		
		
	}
}
