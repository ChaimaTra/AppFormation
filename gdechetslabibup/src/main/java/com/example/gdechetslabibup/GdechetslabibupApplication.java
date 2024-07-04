package com.example.gdechetslabibup;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import com.example.gdechetslabibup.service.FilesStorageService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.crypto.password.PasswordEncoder;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import com.example.gdechetslabibup.service.UserService;
import com.example.gdechetslabibup.model.User;
import com.example.gdechetslabibup.model.Role;


@SpringBootApplication
@Slf4j

public class GdechetslabibupApplication implements CommandLineRunner{

    private static final Logger log = LoggerFactory.getLogger(GdechetslabibupApplication.class);

    @Autowired
    private FilesStorageService filesStorageService;

    @Autowired
    private UserService userService;

    @Autowired
    private PasswordEncoder passwordEncoder;

	public static void main(String[] args) {
		SpringApplication.run(GdechetslabibupApplication.class, args);
	}



	@Override
	public void run(String... args) throws Exception {
        log.info("Storage initialisation");
        filesStorageService.init();

        log.info("Creating default admin user");
        createDefaultAdminUser();
    }

    private void createDefaultAdminUser() {
        // Check if admin user exists
        if (userService.getAllUsers().isEmpty()) {
            // Create default admin user
            User admin = User.builder()
                    .firstname("Admin")
                    .lastname("User")
                    .email("admin@example.com")
                    .password(passwordEncoder.encode("adminpassword"))
                    .role(Role.ADMIN)
                    .quartier("AdminQuartier")
                    .build();
            userService.createUser(admin);
            log.info("Default admin user created: admin@example.com / adminpassword");
        } else {
            log.info("Admin user already exists");
        }
    }

}
