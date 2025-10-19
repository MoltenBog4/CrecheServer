// src/main/java/za/co/creche_server/repository/UserRepository.java
package za.co.creche_server.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import za.co.creche_server.model.User;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByEmail(String email);
    boolean existsByEmail(String email);
}
