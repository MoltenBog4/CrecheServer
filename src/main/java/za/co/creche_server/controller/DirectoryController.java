package za.co.creche_server.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import za.co.creche_server.dto.ParentSummary;
import za.co.creche_server.dto.ChildSummary;
import za.co.creche_server.service.RegistrationService;

import java.util.List;

@RestController
@RequestMapping("/api") // -> /api/parents and /api/children
@RequiredArgsConstructor
public class DirectoryController {

    private final RegistrationService registrationService;

    @GetMapping("/parents")
    public ResponseEntity<List<ParentSummary>> listParents() {
        return ResponseEntity.ok(registrationService.listParents());
    }

    @GetMapping("/children")
    public ResponseEntity<List<ChildSummary>> listChildren() {
        return ResponseEntity.ok(registrationService.listChildren());
    }
}
