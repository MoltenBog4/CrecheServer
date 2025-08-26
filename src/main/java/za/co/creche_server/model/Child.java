package za.co.creche_server.model;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class Child {
    private Long id;
    private Long parentId;
    private String firstName;
    private String lastName;
    private LocalDate dateOfBirth;
    private String medicalNotes;
    private LocalDateTime createdAt;

    public Child() {}

    public Child(Long id, Long parentId, String firstName, String lastName, LocalDate dateOfBirth, String medicalNotes, LocalDateTime createdAt) {
        this.id = id; this.parentId = parentId; this.firstName = firstName; this.lastName = lastName;
        this.dateOfBirth = dateOfBirth; this.medicalNotes = medicalNotes; this.createdAt = createdAt;
    }

    // getters/setters
    public Long getId() { return id; } public void setId(Long id) { this.id = id; }
    public Long getParentId() { return parentId; } public void setParentId(Long parentId) { this.parentId = parentId; }
    public String getFirstName() { return firstName; } public void setFirstName(String firstName) { this.firstName = firstName; }
    public String getLastName() { return lastName; } public void setLastName(String lastName) { this.lastName = lastName; }
    public LocalDate getDateOfBirth() { return dateOfBirth; } public void setDateOfBirth(LocalDate dateOfBirth) { this.dateOfBirth = dateOfBirth; }
    public String getMedicalNotes() { return medicalNotes; } public void setMedicalNotes(String medicalNotes) { this.medicalNotes = medicalNotes; }
    public LocalDateTime getCreatedAt() { return createdAt; } public void setCreatedAt(LocalDateTime createdAt) { this.createdAt = createdAt; }
}
