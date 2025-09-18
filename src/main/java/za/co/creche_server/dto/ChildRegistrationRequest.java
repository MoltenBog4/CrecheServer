package za.co.creche_server.dto;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;

@Getter @Setter
public class ChildRegistrationRequest {
    @NotBlank private String firstName;
    @NotBlank private String lastName;
    @NotBlank private String dateOfBirth; // "YYYY-MM-DD"
    private String gender;
    private String classGroup;
    private String allergies;
    private String medicalNotes;
}
