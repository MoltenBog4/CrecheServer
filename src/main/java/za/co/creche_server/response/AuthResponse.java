// src/main/java/za/co/creche_server/response/AuthResponse.java
package za.co.creche_server.response;

public class AuthResponse {
    public String token;
    public String role;

    public AuthResponse(String token, String role) {
        this.token = token;
        this.role = role;
    }
}
