package com.example.store.repository;

import com.example.store.domain.Address;
import com.example.store.domain.Name;
import com.example.store.domain.User;
import lombok.NonNull;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.List;

@Repository
public class UserRespository {

    public void insertData(@NonNull User users) {
        SqlParameterSource parameters = new MapSqlParameterSource()
                .addValue("email", users.getEmail(), Types.VARCHAR)
                .addValue("password", users.getPassword(), Types.VARCHAR)
                .addValue("first_name", users.getNames(), Types.VARCHAR)
                .addValue("last_name", users.getNames(), Types.VARCHAR)
                .addValue("phone", users.getPhone(), Types.INTEGER);
        jdbc.update(INSERT_SQL, parameters);
    }

    private static final String INSERT_SQL = "INSERT INTO " +
            "users(email, password, first_name, first_name, phone) " +
            "VALUES(:email, :password, :first_name, :first_name, :phone)";
    private final NamedParameterJdbcTemplate jdbc;

    public UserRespository(DataSource ds) {
        this.jdbc = new NamedParameterJdbcTemplate(ds);
    }
    private User buildFragment(ResultSet rs) throws SQLException {
        return User.builder()
                .id_user(rs.getInt("id_user"))
                .email(rs.getString("email"))
                .password(rs.getString("password"))
                .names((List<Name>) rs.getObject("firstname"))
                .names((List<Name>) rs.getObject("lastname"))
                .build();
    }
}
