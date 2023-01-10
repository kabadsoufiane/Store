package com.example.store.repository;

import com.example.store.domain.Cart;
import com.example.store.domain.Rating;
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
public class RatingsRepository {

    public void insertData(@NonNull Rating ratings) {
        SqlParameterSource parameters = new MapSqlParameterSource()
                .addValue("count", ratings.getCount(), Types.INTEGER)
                .addValue("rate", ratings.getRate(), Types.DECIMAL);
        jdbc.update(INSERT_SQL, parameters);
    }

    private static final String INSERT_SQL = "INSERT INTO ratings(count, rate) VALUES(:count, :rate)";
    private final NamedParameterJdbcTemplate jdbc;

    public RatingsRepository(DataSource ds) {
        this.jdbc = new NamedParameterJdbcTemplate(ds);
    }
    private Rating buildFragment(ResultSet rs) throws SQLException {
        return Rating.builder()
                .count(rs.getInt("count"))
                .rate(rs.getDouble("rate"))
                .build();
    }
}
