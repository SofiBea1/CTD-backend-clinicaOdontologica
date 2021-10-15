package com.dh.clinica.securityConfig;

import com.dh.clinica.persistence.entities.AppUser;
import com.dh.clinica.persistence.entities.AppUserRole;
import com.dh.clinica.persistence.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class DataLoader implements ApplicationRunner {
    private UserRepository userRepository;

    @Autowired
    public DataLoader(UserRepository userRepository){
        this.userRepository = userRepository;
    }

    @Override
    public void run(ApplicationArguments args) throws Exception {
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        String password = passwordEncoder.encode("miContrasenia");
        String password2 = passwordEncoder.encode("miContrasenia");

        userRepository.save(new AppUser("sofi", "sofi", "sofi@digital.com", password, AppUserRole.USER));
        userRepository.save(new AppUser("sofiAdmin", "sofiAdmin", "sofiAdmin@digital.com", password2, AppUserRole.ADMIN));
    }
}
