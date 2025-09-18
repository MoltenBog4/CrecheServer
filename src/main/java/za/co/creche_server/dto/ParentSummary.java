package za.co.creche_server.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter @AllArgsConstructor
public class ParentSummary {
    private Long id;
    private String firstName;
    private String lastName;
    private String email;
    private String cellNumber;
}
