// src/main/java/za/co/creche_server/controller/AuthController.java
package za.co.creche_server.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import za.co.creche_server.request.AuthRequest;
import za.co.creche_server.request.RegisterRequest;
import za.co.creche_server.response.AuthResponse;
import za.co.creche_server.service.AuthService;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    private final AuthService auth;

    public AuthController(AuthService auth) {
        this.auth = auth;
    }

    @PostMapping("/register")
    public ResponseEntity<?> register(@Validated @RequestBody RegisterRequest req) {
        auth.register(req);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/login")
    public ResponseEntity<AuthResponse> login(@Validated @RequestBody AuthRequest req) {
        return ResponseEntity.ok(auth.login(req));
    }
}
