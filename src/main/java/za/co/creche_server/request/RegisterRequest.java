// src/main/java/za/co/creche_server/request/RegisterRequest.java
package za.co.creche_server.request;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

public class RegisterRequest {
    @Email @NotBlank
    public String email;

    @NotBlank @Size(min = 8, max = 64)
    public String password;

    // "PARENT" or "STAFF"
    @NotBlank
    public String role;
}
