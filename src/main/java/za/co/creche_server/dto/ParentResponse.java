package za.co.creche_server.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;

@Getter
@AllArgsConstructor
public class ParentResponse {
    private Long id;
    private String firstName;
    private String lastName;
    private String idNumber;
    private String cellNumber;
    private String email;
    private String address;
    private List<ChildResponse> children;
}
