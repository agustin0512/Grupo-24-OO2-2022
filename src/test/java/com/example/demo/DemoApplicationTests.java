package com.example.demo;

import java.time.LocalDateTime;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.example.entities.User;
import com.example.entities.UserRole;
import com.example.service.implementation.UserRoleService;
import com.example.service.implementation.UserService;

@SpringBootTest
public class DemoApplicationTests {

	@Autowired
	private UserService userService;
	@Autowired
	private UserRoleService userRoleService;

	private BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

	@Test
	public void UsuarioTest() {
		// Creamos el Roles para el Usuario
		// Rol User
	
		UserRole rolUser = new UserRole(); // Rol Admin
		UserRole rolAdmin = new UserRole(); // Rol Admin
		UserRole rolAudit = new UserRole(); // Rol Admin
		rolUser.setRole("ROLE_USER");
		rolAdmin.setRole("ROLE_ADMIN");
		rolAudit.setRole("ROLE_AUDIT");
		userRoleService.guardar(rolUser);
		userRoleService.guardar(rolAdmin);
		userRoleService.guardar(rolAudit);
		// Creamos el Usuario
		User user = new User();
		user.setNombre("Juan");
		user.setApellido("Perez");
		user.setMail("prueba@gmail.com");
		user.setTipodoc("DNI");
		user.setDni(403414);
		user.setUsername("polo");
		user.setPassword(encoder.encode("9999"));
		user.setEnabled(true);
		user.setCreatedAt(LocalDateTime.now());
		user.setUpdatedAt(LocalDateTime.now());
		user.setRol(rolAdmin);
		
		
		
		User user2 = new User();
		user2.setNombre("Martin");
		user2.setApellido("Garcia");
		user2.setMail("prueba2@gmail.com");
		user2.setTipodoc("DNI");
		user2.setDni(456789);
		user2.setUsername("martin");
		user2.setPassword(encoder.encode("1234"));
		user2.setEnabled(true);
		user2.setCreatedAt(LocalDateTime.now());
		user2.setUpdatedAt(LocalDateTime.now());
		user2.setRol(rolUser);
		
		
		User user3 = new User();
		user3.setNombre("Federico");
		user3.setApellido("Dilorenzo");
		user3.setMail("prueba3@gmail.com");
		user3.setTipodoc("DNI");
		user3.setDni(57589);
		user3.setUsername("federico");
		user3.setPassword(encoder.encode("123456"));
		user3.setEnabled(true);
		user3.setCreatedAt(LocalDateTime.now());
		user3.setUpdatedAt(LocalDateTime.now());
		user3.setRol(rolAudit);
		
		
		// Guardamos User y sus Roles en la BD
		userService.guardar(user);
		userService.guardar(user2);
		userService.guardar(user3);


	}
	
}