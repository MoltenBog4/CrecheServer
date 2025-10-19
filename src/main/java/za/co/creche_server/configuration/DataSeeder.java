// src/main/java/za/co/creche_server/configuration/DataSeeder.java
package za.co.creche_server.configuration;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import za.co.creche_server.model.Role;
import za.co.creche_server.repository.RoleRepository;

@Component
public class DataSeeder implements CommandLineRunner {

    private final RoleRepository roles;

    public DataSeeder(RoleRepository roles) {
        this.roles = roles;
    }

    @Override
    public void run(String... args) {
        roles.findByName("PARENT").orElseGet(() -> roles.save(new Role("PARENT")));
        roles.findByName("STAFF").orElseGet(() -> roles.save(new Role("STAFF")));
    }
}
