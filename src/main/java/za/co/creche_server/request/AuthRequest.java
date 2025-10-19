// src/main/java/za/co/creche_server/request/AuthRequest.java
package za.co.creche_server.request;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

public class AuthRequest {
    @Email @NotBlank
    public String email;

    @NotBlank
    public String password;
}
