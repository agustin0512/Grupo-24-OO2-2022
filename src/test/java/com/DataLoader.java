package com;

import java.time.LocalDateTime;

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

    @Autowired
    private UserService userService;

    @Autowired
    private UserRoleService userRoleService;

    private BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

   @Override
public void run(String... args) throws Exception {

    if (userService.count() > 0) return;

    UserRole rolAdmin = new UserRole();
    rolAdmin.setRole("ROLE_ADMIN");
    userRoleService.guardar(rolAdmin);

    User user = new User();
    user.setUsername("polo");
    user.setPassword(encoder.encode("9999"));
    user.setEnabled(true);
    user.setCreatedAt(LocalDateTime.now());
    user.setUpdatedAt(LocalDateTime.now());
    user.setRol(rolAdmin);

    userService.guardar(user); // 👈 CLAVE
}
}
