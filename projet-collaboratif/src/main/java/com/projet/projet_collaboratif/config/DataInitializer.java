package com.projet.projet_collaboratif.config;

import com.projet.projet_collaboratif.model.Role;
import com.projet.projet_collaboratif.repository.RoleRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class DataInitializer implements CommandLineRunner {

    private final RoleRepository roleRepository;

    public DataInitializer(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    @Override
    public void run(String... args) {
        if (roleRepository.count() == 0) {
            Role admin = new Role();
            admin.setNom("ADMIN");

            Role user = new Role();
            user.setNom("USER");

            roleRepository.save(admin);
            roleRepository.save(user);

            System.out.println("Rôles créés : ADMIN, USER");
        }
    }
}
