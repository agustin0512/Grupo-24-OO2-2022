package com;

import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Profile;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

import com.example.entities.User;
import com.example.entities.UserRole;
import com.example.service.implementation.UserRoleService;
import com.example.service.implementation.UserService;
@Profile("dev")
@Component
public class DataLoader implements CommandLineRunner {

    @Autowired
    private UserService userService;

    @Autowired
    private UserRoleService userRoleService;

    private BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

    @Override
    public void run(String... args) throws Exception {

        if (userService.count() > 0) return;


        UserRole rolUser = new UserRole();
        UserRole rolAdmin = new UserRole();
        UserRole rolAudit = new UserRole();

        rolUser.setRole("ROLE_USER");
        rolAdmin.setRole("ROLE_ADMIN");
        rolAudit.setRole("ROLE_AUDIT");

        userRoleService.guardar(rolUser);
        userRoleService.guardar(rolAdmin);
        userRoleService.guardar(rolAudit);

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

        // ... resto igual
    }
}
