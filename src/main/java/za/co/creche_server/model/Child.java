package za.co.creche_server.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Child {
    private Long id;
    private String firstName;
    private String lastName;
    private String dateOfBirth;   // can use String for simplicity, or LocalDate if you want stricter typing
    private String gender;        // e.g., "Male", "Female"
    private String classGroup;    // e.g., "Nursery", "Playgroup"
    private String allergies;
    private String medicalNotes;
    private Long parentId;        // reference to the Parent (foreign key)
}
