package com.example.store.repository;

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
import java.util.List;

@Repository
public class RatingsRepository {

    public void insertData(@NonNull Rating ratings) {
        SqlParameterSource parameters = new MapSqlParameterSource()
                .addValue("count", ratings.getCount(), Types.INTEGER)
                .addValue("rate", ratings.getRate(), Types.DECIMAL);
        jdbc.update(INSERT_SQL, parameters);
    }

    public List<Rating> getRatings(){
        return jdbc.query(SELECT_ALL_RATINGS, (rs, rowNum) -> buildFragment(rs));
    }

    public List<Rating> getRatingById(@NonNull int id_product){
        return jdbc.query(SELECT_RATING_BYID,
                new MapSqlParameterSource()
                        .addValue("id_product", id_product, Types.VARCHAR),
                (rs, rowNum) -> buildFragment(rs));
    }

    public void updateRatingById(@NonNull int id_product, int count, double rate){
        SqlParameterSource parameters = new MapSqlParameterSource()
                .addValue("id_product", id_product, Types.INTEGER)
                        .addValue("count", count, Types.INTEGER)
                                .addValue("rate", rate, Types.NUMERIC);
        jdbc.update(UPDATE_RATING_BY_ID, parameters);
    }

    public void deleteRatingsById(@NonNull int id_product){
        jdbc.query(DELETE_BY_ID,
                new MapSqlParameterSource()
                        .addValue("id_product", id_product, Types.INTEGER),
                (rs, rowNum) -> buildFragment(rs));
    }

    private static final String SELECT_RATING_BYID = "SELECT * FROM ratings WHERE id_product= :id_product";
    private static final String DELETE_BY_ID = "DELETE FROM ratings WHERE id_product= :id_product";
    private static final String INSERT_SQL = "INSERT INTO ratings(count, rate) VALUES(:count, :rate)";
    private static final String SELECT_ALL_RATINGS = "SELECT * FROM ratings";
    private static final String UPDATE_RATING_BY_ID = "UPDATE ratings SET count= : count, rate= :rate WHERE id_product= :id_product";
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
