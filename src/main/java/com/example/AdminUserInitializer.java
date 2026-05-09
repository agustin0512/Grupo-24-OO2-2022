package com.example;

import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import com.example.entities.User;
import com.example.entities.UserRole;
import com.example.service.implementation.UserRoleService;
import com.example.service.implementation.UserService;

@Component
public class AdminUserInitializer implements CommandLineRunner {

    private final UserService userService;
    private final UserRoleService userRoleService;
    private final PasswordEncoder passwordEncoder;

    @Value("${ADMIN_USER:admin}")
    private String adminUser;

    @Value("${ADMIN_PASSWORD:admin123}")
    private String adminPassword;

    @Value("${ADMIN_MAIL:admin@system.com}")
    private String adminMail;

    @Value("${ADMIN_DNI:99999999}")
    private long adminDni;

    public AdminUserInitializer(UserService userService, UserRoleService userRoleService, PasswordEncoder passwordEncoder) {
        this.userService = userService;
        this.userRoleService = userRoleService;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public void run(String... args) {
        UserRole rolAdmin = userRoleService.findByRole("ROLE_ADMIN");

        if (rolAdmin == null) {
            rolAdmin = new UserRole();
            rolAdmin.setRole("ROLE_ADMIN");
            rolAdmin = userRoleService.guardar(rolAdmin);
        }

        if (userService.findByUsername(adminUser) != null) {
            System.out.println("El usuario administrador '" + adminUser + "' ya existe.");
            return;
        }

        User user = new User();
        user.setNombre("Admin");
        user.setApellido("System");
        user.setMail(adminMail);
        user.setTipodoc("DNI");
        user.setDni(adminDni);
        user.setUsername(adminUser);
        user.setPassword(passwordEncoder.encode(adminPassword));
        user.setEnabled(true);
        user.setCreatedAt(LocalDateTime.now());
        user.setUpdatedAt(LocalDateTime.now());
        user.setRol(rolAdmin);

        userService.guardar(user);
        System.out.println("Usuario administrador '" + adminUser + "' creado correctamente.");
    }
}
