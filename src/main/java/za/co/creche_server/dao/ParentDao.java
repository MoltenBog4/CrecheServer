package za.co.creche_server.dao;

import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;
import za.co.creche_server.model.Parent;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;

@Repository
@RequiredArgsConstructor
public class ParentDao {

    private final JdbcTemplate jdbc;

    private static final RowMapper<Parent> ROW_MAPPER = new RowMapper<Parent>() {
        @Override
        public Parent mapRow(ResultSet rs, int rowNum) throws SQLException {
            Parent p = new Parent();
            p.setId(rs.getLong("id"));
            p.setFirstName(rs.getString("first_name"));
            p.setLastName(rs.getString("last_name"));
            p.setIdNumber(rs.getString("id_number"));
            p.setCellNumber(rs.getString("cell_number"));
            p.setEmail(rs.getString("email"));
            p.setAddress(rs.getString("address"));
            return p;
        }
    };

    /** Insert a parent and return the generated ID. */
    public Long insert(Parent p) {
        SimpleJdbcInsert insert = new SimpleJdbcInsert(jdbc)
                .withTableName("parents")
                .usingGeneratedKeyColumns("id");

        Map<String, Object> params = new HashMap<>();
        params.put("first_name",  p.getFirstName());
        params.put("last_name",   p.getLastName());
        params.put("id_number",   p.getIdNumber());
        params.put("cell_number", p.getCellNumber());
        params.put("email",       p.getEmail());
        params.put("address",     p.getAddress());

        Number key = insert.executeAndReturnKey(params);
        return key.longValue();
    }

    /** Find a parent by ID. */
    public Optional<Parent> findById(Long id) {
        List<Parent> list = jdbc.query(
                "SELECT id, first_name, last_name, id_number, cell_number, email, address " +
                        "FROM parents WHERE id = ?",
                ROW_MAPPER, id
        );
        return list.stream().findFirst();
    }

    /** List all parents (newest first). */
    public List<Parent> findAll() {
        return jdbc.query(
                "SELECT id, first_name, last_name, id_number, cell_number, email, address " +
                        "FROM parents ORDER BY id DESC",
                ROW_MAPPER
        );
    }

    /** Check if a parent exists by email. */
    public boolean existsByEmail(String email) {
        Integer count = jdbc.queryForObject(
                "SELECT COUNT(*) FROM parents WHERE email = ?",
                Integer.class, email
        );
        return count != null && count > 0;
    }

    /** Delete a parent by ID. (Children will cascade from FK if you set it that way) */
    public int deleteById(Long id) {
        return jdbc.update("DELETE FROM parents WHERE id = ?", id);
    }
}
