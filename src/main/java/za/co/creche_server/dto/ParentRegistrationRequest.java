package za.co.creche_server.dto;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.List;

@Getter @Setter
public class ParentRegistrationRequest {
    @NotBlank private String firstName;
    @NotBlank private String lastName;
    private String idNumber;
    private String cellNumber;
    @Email @NotBlank private String email;
    private String address;

    @NotNull
    private List<ChildRegistrationRequest> children;
}
