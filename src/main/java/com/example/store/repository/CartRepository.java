package com.example.store.repository;

import com.example.store.domain.Cart;
import lombok.NonNull;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;

@Repository
public class CartRepository {

    public void insertData(@NonNull Cart carts) {
        SqlParameterSource parameters = new MapSqlParameterSource()
                .addValue("id_user", carts.getId_user(), Types.INTEGER)
                .addValue("date", carts.getDate(), Types.DATE);
        jdbc.update(INSERT_SQL, parameters);
    }

    private static final String INSERT_SQL = "INSERT INTO cart(id_user, date) VALUES(:id_user, :date)";

    private final NamedParameterJdbcTemplate jdbc;

    public CartRepository(DataSource ds) {
        this.jdbc = new NamedParameterJdbcTemplate(ds);
    }
    private Cart buildFragment(ResultSet rs) throws SQLException {
        return Cart.builder()
                .id_cart(rs.getInt("id_cart"))
                .date(rs.getDate("date"))
                .build();

    }
}
