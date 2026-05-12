package com.example;

import java.time.LocalDateTime;

import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.beans.factory.annotation.Value;

import com.example.entities.User;
import com.example.entities.UserRole;
import com.example.service.implementation.UserRoleService;
import com.example.service.implementation.UserService;

@Component
public class DataLoader implements CommandLineRunner {

    private final UserService userService;
    private final UserRoleService userRoleService;
    private final PasswordEncoder encoder;

    @Value("${ADMIN_USER:admin}")
    private String adminUser;

    @Value("${ADMIN_PASSWORD:admin123}")
    private String adminPassword;

  public DataLoader(UserService userService,
                  UserRoleService userRoleService,
                  PasswordEncoder encoder) {
    this.userService = userService;
    this.userRoleService = userRoleService;
    this.encoder = encoder;
}

    @Override
    public void run(String... args) {

        try {
            System.out.println("🔥 DATA LOADER STARTED 🔥");

            if (adminUser == null || adminPassword == null) {
                System.out.println("⚠ ADMIN vars not set, using defaults");
            }

            UserRole rolAdmin = userRoleService.findByRole("ROLE_ADMIN");

            if (rolAdmin == null) {
                rolAdmin = new UserRole();
                rolAdmin.setRole("ROLE_ADMIN");
                rolAdmin = userRoleService.guardar(rolAdmin);
            }

            User existing = userService.findByUsername(adminUser);

            if (existing == null) {

                User user = new User();
                user.setNombre("Admin");
                user.setApellido("System");
                user.setMail("admin@system.com");
                user.setTipodoc("DNI");
                user.setDni(99999999);

                user.setUsername(adminUser);
                user.setPassword(encoder.encode(adminPassword));
                user.setEnabled(true);
                user.setCreatedAt(LocalDateTime.now());
                user.setUpdatedAt(LocalDateTime.now());
                user.setRol(rolAdmin);

                userService.guardar(user);

                System.out.println("✅ ADMIN USER CREATED");
            } else {
                System.out.println("ℹ ADMIN USER ALREADY EXISTS");
            }

        } catch (Exception e) {
            System.out.println("❌ DATA LOADER ERROR: " + e.getMessage());
            e.printStackTrace();
        }
    }
}