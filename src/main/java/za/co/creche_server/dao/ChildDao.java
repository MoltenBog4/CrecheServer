package za.co.creche_server.dao;

import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;
import za.co.creche_server.model.Child;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
@RequiredArgsConstructor
public class ChildDao {
    private final JdbcTemplate jdbc;

    public Long insert(Child c) {
        SimpleJdbcInsert insert = new SimpleJdbcInsert(jdbc)
                .withSchemaName("creche")        // <-- if you set ?currentSchema=creche in URL, you can remove this
                .withTableName("children")
                .usingGeneratedKeyColumns("id");

        Map<String, Object> params = new HashMap<>();
        params.put("first_name",    c.getFirstName());
        params.put("last_name",     c.getLastName());
        params.put("date_of_birth", c.getDateOfBirth()); // "YYYY-MM-DD"
        params.put("gender",        c.getGender());
        params.put("class_group",   c.getClassGroup());
        params.put("allergies",     c.getAllergies());
        params.put("medical_notes", c.getMedicalNotes());
        params.put("parent_id",     c.getParentId());

        Number key = insert.executeAndReturnKey(params);
        return key.longValue();
    }

    public List<Child> findByParentId(Long parentId) {
        String sql = "SELECT id, first_name, last_name, date_of_birth, gender, class_group, " +
                "allergies, medical_notes, parent_id " +
                "FROM creche.children WHERE parent_id = ? ORDER BY id"; // <-- remove 'creche.' if using currentSchema
        return jdbc.query(sql, (rs, i) -> {
            Child c = new Child();
            c.setId(rs.getLong("id"));
            c.setFirstName(rs.getString("first_name"));
            c.setLastName(rs.getString("last_name"));
            c.setDateOfBirth(rs.getString("date_of_birth"));
            c.setGender(rs.getString("gender"));
            c.setClassGroup(rs.getString("class_group"));
            c.setAllergies(rs.getString("allergies"));
            c.setMedicalNotes(rs.getString("medical_notes"));
            c.setParentId(rs.getLong("parent_id"));
            return c;
        }, parentId);
    }

    // 👇 NEW: list all children (used by RegistrationService.listChildren)
    public List<Child> findAll() {
        String sql = "SELECT id, first_name, last_name, date_of_birth, gender, class_group, " +
                "allergies, medical_notes, parent_id " +
                "FROM creche.children ORDER BY id DESC";   // <-- remove 'creche.' if using currentSchema
        return jdbc.query(sql, (rs, i) -> {
            Child c = new Child();
            c.setId(rs.getLong("id"));
            c.setFirstName(rs.getString("first_name"));
            c.setLastName(rs.getString("last_name"));
            c.setDateOfBirth(rs.getString("date_of_birth"));
            c.setGender(rs.getString("gender"));
            c.setClassGroup(rs.getString("class_group"));
            c.setAllergies(rs.getString("allergies"));
            c.setMedicalNotes(rs.getString("medical_notes"));
            c.setParentId(rs.getLong("parent_id"));
            return c;
        });
    }

    public int deleteByParentId(Long parentId) {
        return jdbc.update("DELETE FROM creche.children WHERE parent_id = ?", parentId);
    }

    public int deleteById(Long id) {
        return jdbc.update("DELETE FROM creche.children WHERE id = ?", id);
    }
}
