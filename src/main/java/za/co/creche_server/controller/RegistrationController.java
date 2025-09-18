package za.co.creche_server.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import za.co.creche_server.dto.ChildRegistrationRequest;
import za.co.creche_server.dto.ChildResponse;
import za.co.creche_server.dto.ParentRegistrationRequest;
import za.co.creche_server.dto.ParentResponse;
import za.co.creche_server.service.RegistrationService;

import javax.validation.Valid;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
@Validated
public class RegistrationController {

    private final RegistrationService registrationService;

    /** Register a parent and one or more children in one request. */
    @PostMapping("/registration/parent")
    public ResponseEntity<ParentResponse> registerParent(
            @Valid @RequestBody ParentRegistrationRequest request) {
        return ResponseEntity.ok(registrationService.registerParentAndChildren(request));
    }

    /** Get a parent with all their children. */
    @GetMapping("/parents/{id}")
    public ResponseEntity<ParentResponse> getParent(@PathVariable("id") Long id) {
        return ResponseEntity.ok(registrationService.getParentWithChildren(id));
    }

    /** Add a new child to an existing parent. */
    @PostMapping("/parents/{parentId}/children")
    public ResponseEntity<ChildResponse> addChild(
            @PathVariable("parentId") Long parentId,
            @Valid @RequestBody ChildRegistrationRequest child) {
        return ResponseEntity.ok(registrationService.addChild(parentId, child));
    }

    /** Delete a parent (children will be removed by FK cascade if configured). */
    @DeleteMapping("/parents/{id}")
    public ResponseEntity<Void> deleteParent(@PathVariable("id") Long id) {
        registrationService.deleteParent(id);
        return ResponseEntity.noContent().build();
    }
}
