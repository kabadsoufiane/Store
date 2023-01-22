package com.example.store.repository;

import com.example.store.domain.Address;
import com.example.store.domain.Cart;
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
import java.time.LocalDate;
import java.util.List;

@Repository
public class UserRepository {

    public void insertData(@NonNull User users) {
        SqlParameterSource parameters = new MapSqlParameterSource()
                .addValue("email", users.getEmail(), Types.VARCHAR)
                .addValue("password", users.getPassword(), Types.VARCHAR)
                .addValue("first_name", users.getNames(), Types.VARCHAR)
                .addValue("last_name", users.getNames(), Types.VARCHAR)
                .addValue("phone", users.getPhone(), Types.INTEGER);
        jdbc.update(INSERT_SQL, parameters);
    }


    public List<User> getAllUsers(){
        return jdbc.query(SELECT_ALL_USER, (rs, rowNum) -> buildFragment(rs));
    }
    public List<User> getUserById(@NonNull int id_user){
        return jdbc.query(SELECT_BY_ID,
                new MapSqlParameterSource()
                        .addValue("id_user", id_user, Types.INTEGER),
                (rs, rowNum) -> buildFragment(rs));
    }

    public void deleteUserById(@NonNull int id_user){
        jdbc.query(DELETE_BY_ID,
                new MapSqlParameterSource()
                        .addValue("id_user", id_user, Types.INTEGER),
                (rs, rowNum) -> buildFragment(rs));
    }

    public void updateUserById(@NonNull int id_user, String email, String password,
                               String first_name, String last_name, int phone){
        SqlParameterSource parameters = new MapSqlParameterSource()
                .addValue("id_user", id_user, Types.INTEGER)
                .addValue("email", email, Types.VARCHAR)
                .addValue("password", password, Types.VARCHAR)
                .addValue("first_name", first_name, Types.VARCHAR)
                .addValue("last_name", last_name, Types.VARCHAR)
                .addValue("phone", phone, Types.INTEGER);
        jdbc.update(UPDATE_USER_BY_ID, parameters);

    }

    private static final String UPDATE_USER_BY_ID = "UPDATE users SET email= :email, password= :password," +
            " first_name= :first_name, last_name= :last_name, phone= :phone WHERE id_user =:id_user";
    private static final String DELETE_BY_ID = "DELETE FROM users WHERE id_user= :id_user";
    private static final String SELECT_ALL_USER = "SELECT * FROM users";
    private static final String SELECT_BY_ID = "SELECT * FROM users WHERE id_user = :id_user";
    private static final String INSERT_SQL = "INSERT INTO " +
            "users(email, password, first_name, first_name, phone) " +
            "VALUES(:email, :password, :first_name, :first_name, :phone)";
    private final NamedParameterJdbcTemplate jdbc;

    public UserRepository(DataSource ds) {
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
