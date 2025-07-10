package com.diemdt.identity_service.config;

import com.diemdt.identity_service.entity.User;
import com.diemdt.identity_service.enums.Role;
import com.diemdt.identity_service.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.ApplicationRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@Configuration
@RequiredArgsConstructor
public class ApplicationInitConfig {
    public final PasswordEncoder passwordEncoder ;
    @Bean
    ApplicationRunner applicationRunner(UserRepository userRepository) {

        return args -> {
            List<String> roles = new ArrayList<>();
            roles.add(Role.ADMIN.name());
            if(userRepository.existsByUsername("admin") == false ){
                User user = User.builder()
                        .username("admin")
                        .password(passwordEncoder.encode("12345678"))
//                        .roles(roles)
                        .build();


                userRepository.save(user);
                log.warn("admin user has been created with default ");
            }
        };
    }
}