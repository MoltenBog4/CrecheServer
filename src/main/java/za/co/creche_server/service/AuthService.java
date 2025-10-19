// src/main/java/za/co/creche_server/service/AuthService.java
package za.co.creche_server.service;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import za.co.creche_server.model.Role;
import za.co.creche_server.model.User;
import za.co.creche_server.repository.RoleRepository;
import za.co.creche_server.request.AuthRequest;
import za.co.creche_server.request.RegisterRequest;
import za.co.creche_server.response.AuthResponse;

import java.util.Collections;
import java.util.Map;

@Service
public class AuthService {

    private final UserService userService;
    private final RoleRepository roles;
    private final PasswordEncoder encoder;
    private final JwtService jwt;

    public AuthService(UserService userService,
                       RoleRepository roles,
                       PasswordEncoder encoder,
                       JwtService jwt) {
        this.userService = userService;
        this.roles = roles;
        this.encoder = encoder;
        this.jwt = jwt;
    }

    @Transactional
    public void register(RegisterRequest req) {
        if (userService.existsByEmail(req.email)) {
            throw new IllegalArgumentException("Email already in use");
        }

        Role role = roles.findByName(req.role.toUpperCase())
                .orElseThrow(() -> new IllegalArgumentException("Invalid role"));

        User u = new User();
        u.setEmail(req.email.toLowerCase());
        u.setPasswordHash(encoder.encode(req.password));
        u.setRoles(Collections.singleton(role));

        userService.save(u);
    }

    public AuthResponse login(AuthRequest req) {
        User u = userService.findByEmail(req.email.toLowerCase())
                .orElseThrow(() -> new IllegalArgumentException("Invalid email or password"));

        if (!encoder.matches(req.password, u.getPasswordHash())) {
            throw new IllegalArgumentException("Invalid email or password");
        }

        // For simplicity, just use the first role
        String role = u.getRoles().stream()
                .findFirst()
                .map(Role::getName)
                .orElse("PARENT");

        String token = jwt.generate(u.getEmail(), Collections.<String, Object>singletonMap("role", role));
        return new AuthResponse(token, role);
    }
}
