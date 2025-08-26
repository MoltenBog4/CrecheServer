package za.co.creche_server.service;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import za.co.creche_server.model.Child;
import za.co.creche_server.model.Parent;

import java.sql.Timestamp;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
public class RegistrationService {

    private final JdbcTemplate jdbc;

    public RegistrationService(JdbcTemplate jdbc) {
        this.jdbc = jdbc;
    }

    @Transactional
    public Parent registerParentWithChildren(String fullName, String email, String phone, List<Child> childrenReq) {
        // Insert parent and get id via RETURNING
        Map<String, Object> p = jdbc.queryForMap(
                "INSERT INTO parent (full_name, email, phone) VALUES (?, ?, ?) " +
                        "RETURNING id, full_name, email, phone, created_at",
                fullName, email, phone
        );

        Long parentId = ((Number)p.get("id")).longValue();
        Parent parent = new Parent(
                parentId,
                (String)p.get("full_name"),
                (String)p.get("email"),
                (String)p.get("phone"),
                ((Timestamp)p.get("created_at")).toLocalDateTime()
        );

        // Insert children (if any)
        List<Child> savedKids = new ArrayList<>();
        if (childrenReq != null) {
            for (Child c : childrenReq) {
                LocalDate dob = c.getDateOfBirth();
                Map<String, Object> row = jdbc.queryForMap(
                        "INSERT INTO child (parent_id, first_name, last_name, date_of_birth, medical_notes) " +
                                "VALUES (?, ?, ?, ?, ?) " +
                                "RETURNING id, parent_id, first_name, last_name, date_of_birth, medical_notes, created_at",
                        parentId, c.getFirstName(), c.getLastName(), dob, c.getMedicalNotes()
                );
                Child saved = new Child(
                        ((Number)row.get("id")).longValue(),
                        ((Number)row.get("parent_id")).longValue(),
                        (String)row.get("first_name"),
                        (String)row.get("last_name"),
                        ((java.sql.Date)row.get("date_of_birth")).toLocalDate(),
                        (String)row.get("medical_notes"),
                        ((Timestamp)row.get("created_at")).toLocalDateTime()
                );
                savedKids.add(saved);
            }
        }
        parent.setChildren(savedKids);
        return parent;
    }
}
