// src/main/java/za/co/creche_server/service/UserService.java
package za.co.creche_server.service;

import org.springframework.stereotype.Service;
import za.co.creche_server.model.User;
import za.co.creche_server.repository.UserRepository;

import java.util.Optional;

@Service
public class UserService {

    private final UserRepository users;

    public UserService(UserRepository users) {
        this.users = users;
    }

    public Optional<User> findByEmail(String email) {
        return users.findByEmail(email);
    }

    public boolean existsByEmail(String email) {
        return users.existsByEmail(email);
    }

    public User save(User u) {
        return users.save(u);
    }
}
