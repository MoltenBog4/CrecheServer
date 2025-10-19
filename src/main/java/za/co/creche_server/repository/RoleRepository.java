// src/main/java/za/co/creche_server/repository/RoleRepository.java
package za.co.creche_server.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import za.co.creche_server.model.Role;

import java.util.Optional;

public interface RoleRepository extends JpaRepository<Role, Long> {
    Optional<Role> findByName(String name);
}
