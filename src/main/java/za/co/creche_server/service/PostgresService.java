package za.co.creche_server.service;

import org.springframework.stereotype.Service;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.List;

@Service
public class PostgresService {

    private final JdbcTemplate jdbc;

    public PostgresService(JdbcTemplate jdbc) {
        this.jdbc = jdbc;
    }

    /** Returns all values of the 'name' column from table 'creche'. */
    public List<String> findAllNames() {
        String sql = "SELECT name FROM creche";
        return jdbc.query(sql, (rs, i) -> rs.getString("name"));
    }



}
