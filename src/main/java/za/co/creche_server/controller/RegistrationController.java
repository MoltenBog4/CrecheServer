package za.co.creche_server.controller;

import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import za.co.creche_server.model.Child;
import za.co.creche_server.model.Parent;
import za.co.creche_server.service.RegistrationService;
import java.util.stream.Collectors;


import javax.validation.Valid;
import javax.validation.constraints.*;
import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/creche_service/v1/registrations")
@Validated
public class RegistrationController {

    private final RegistrationService service;

    public RegistrationController(RegistrationService service) { this.service = service; }

    // ----------- Request DTOs (Javax Validation for Java 8) -----------

    public static class ChildReq {
        @NotBlank public String firstName;
        @NotBlank public String lastName;

        @NotNull @Past public LocalDate dateOfBirth;

        public String medicalNotes;
    }

    public static class RegistrationReq {
        @NotBlank public String fullName;
        @Email @NotBlank public String email;
        @NotBlank @Size(min = 7, max = 20) public String phone;

        @NotNull @Size(min = 1, message = "At least one child is required")
        public List<@Valid ChildReq> children;
    }

    // ----------- Endpoint -----------

    @PostMapping
    public Parent register(@RequestBody @Valid RegistrationReq req) {
        // Map ChildReq -> Child model (only fields used by service)

        List<Child> kids = req.children.stream().map(cr -> {
            Child c = new Child();
            c.setFirstName(cr.firstName);
            c.setLastName(cr.lastName);
            c.setDateOfBirth(cr.dateOfBirth);
            c.setMedicalNotes(cr.medicalNotes);
            return c;
        }).collect(Collectors.toList());

        return service.registerParentWithChildren(req.fullName, req.email, req.phone, kids);
    }
}
