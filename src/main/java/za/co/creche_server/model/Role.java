// src/main/java/za/co/creche_server/model/Role.java
package za.co.creche_server.model;

import javax.persistence.*;

@Entity
@Table(name = "roles")
public class Role {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // Example values: "PARENT", "STAFF"
    @Column(nullable = false, unique = true, length = 32)
    private String name;

    public Role() {}
    public Role(String name) { this.name = name; }

    public Long getId() { return id; }
    public String getName() { return name; }

    public void setId(Long id) { this.id = id; }
    public void setName(String name) { this.name = name; }
}
