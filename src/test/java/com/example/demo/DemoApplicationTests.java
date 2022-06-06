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

	/*@Test
	public void UsuarioTest() {

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
		
		// Creamos el Roles para el Usuario
		UserRole rolUser = new UserRole();	// Rol User
		UserRole rolAdmin = new UserRole(); // Rol Admin
		rolUser.setRole("ROLE_USER");
		rolAdmin.setRole("ROLE_ADMIN");
		rolUser.setCreatedAt(LocalDateTime.now());
		rolAdmin.setCreatedAt(LocalDateTime.now());
		rolUser.setUpdatedAt(LocalDateTime.now());
		rolAdmin.setUpdatedAt(LocalDateTime.now());
		rolUser.setUser(user);
		rolAdmin.setUser(user);
		user.getUserRoles().add(rolUser);
		user.getUserRoles().add(rolAdmin);
		
		
		// Guardamos User y sus Roles en la BD
		userService.guardar(user);
		userRoleService.guardar(rolAdmin);
		userRoleService.guardar(rolUser);
	}
	*/
}
