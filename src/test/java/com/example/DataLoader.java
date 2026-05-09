package com.example;

import java.time.LocalDateTime;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

import com.example.entities.User;
import com.example.entities.UserRole;
import com.example.service.implementation.UserRoleService;
import com.example.service.implementation.UserService;

@Component
public class DataLoader implements CommandLineRunner {
    System.out.println("🔥 DATA LOADER EJECUTANDOSE 🔥");
    @Autowired
    private UserService userService;

     @Value("${ADMIN_USER}")
    private String adminUser;

    @Value("${ADMIN_PASSWORD}")
    private String adminPassword;

    @Autowired
    private UserRoleService userRoleService;

    private BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

   @Override
public void run(String... args) throws Exception {

UserRole rolAdmin = userRoleService.findByRole("ROLE_ADMIN");

if(rolAdmin == null){
    rolAdmin = new UserRole();
    rolAdmin.setRole("ROLE_ADMIN");
    rolAdmin = userRoleService.guardar(rolAdmin);
}

if(userService.findByUsername(adminUser) == null) {

    User user = new User();

    user.setNombre("Juan");
    user.setApellido("Perez");
    user.setMail("test@gmail.com");
    user.setTipodoc("DNI");
    user.setDni(12345678);

    user.setUsername(adminUser);
    user.setPassword(encoder.encode(adminPassword));
    user.setEnabled(true);
    user.setCreatedAt(LocalDateTime.now());
    user.setUpdatedAt(LocalDateTime.now());
    user.setRol(rolAdmin);

    userService.guardar(user);

    System.out.println(">>> USER CREATED <<<");
}
}
}