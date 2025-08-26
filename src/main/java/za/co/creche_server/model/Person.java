package za.co.creche_server.model;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
public class Person {
    String firstName;
    String LastName;
    String idNumber;
    String cellNumber;
    String email;
}
