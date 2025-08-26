package za.co.creche_server.model;

import java.time.LocalDateTime;
import java.util.List;

public class Parent {
    private Long id;
    private String fullName;
    private String email;
    private String phone;
    private LocalDateTime createdAt;
    private List<Child> children;

    public Parent() {}

    public Parent(Long id, String fullName, String email, String phone, LocalDateTime createdAt) {
        this.id = id; this.fullName = fullName; this.email = email; this.phone = phone; this.createdAt = createdAt;
    }

    // getters/setters
    public Long getId() { return id; } public void setId(Long id) { this.id = id; }
    public String getFullName() { return fullName; } public void setFullName(String fullName) { this.fullName = fullName; }
    public String getEmail() { return email; } public void setEmail(String email) { this.email = email; }
    public String getPhone() { return phone; } public void setPhone(String phone) { this.phone = phone; }
    public LocalDateTime getCreatedAt() { return createdAt; } public void setCreatedAt(LocalDateTime createdAt) { this.createdAt = createdAt; }
    public List<Child> getChildren() { return children; } public void setChildren(List<Child> children) { this.children = children; }
}
