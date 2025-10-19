package za.co.creche_server.model;

import lombok.*;
import javax.persistence.*;  // ✅ use javax, not jakarta

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "children")
public class Child {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String fullName;
    private int age;
    private String allergies;

    @ManyToOne
    @JoinColumn(name = "parent_id")
    private User parent;
}
