package za.co.creche_server.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter @AllArgsConstructor
public class ChildSummary {
    private Long id;
    private String firstName;
    private String lastName;
    private String dateOfBirth; // "YYYY-MM-DD"
    private String gender;
    private String classGroup;
    private Long parentId;
}
