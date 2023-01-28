package com.example.store.repository;

import com.example.store.domain.Cart;
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
public class CartRepository {

    public void insertData(@NonNull Cart carts) {
        SqlParameterSource parameters = new MapSqlParameterSource()
                .addValue("id_user", carts.getId_user(), Types.INTEGER)
                .addValue("date", carts.getDate(), Types.DATE);
        jdbc.update(INSERT_SQL, parameters);
    }

    public List<Cart> getAllCart(){
        return jdbc.query(SELECT_ALL_CART, (rs, rowNum) -> buildFragment(rs));
    }
    public List<Cart> getCartById(@NonNull int id_cart){
        return jdbc.query(SELECT_BY_ID,
                new MapSqlParameterSource()
                        .addValue("id_cart", id_cart, Types.INTEGER),
                (rs, rowNum) -> buildFragment(rs));
    }

    public List<Cart> getCartByDate(@NonNull LocalDate date){
        return jdbc.query(SELECT_BY_DATE,
                new MapSqlParameterSource()
                        .addValue("date", date, Types.INTEGER),
                (rs, rowNum) -> buildFragment(rs));
    }

    public List<Cart> getCartByDateRange(@NonNull LocalDate date1, @NonNull LocalDate date2){
        return jdbc.query(SELECT_BY_DATE_RANGE,
                new MapSqlParameterSource()
                        .addValue("date", date1, Types.DATE)
                        .addValue("date", date2, Types.DATE),
                (rs, rowNum) -> buildFragment(rs));
    }

    public List<Cart> getCartByUser(@NonNull int id_user){
        return jdbc.query(SELECT_BY_USER,
                new MapSqlParameterSource()
                        .addValue("id_user", id_user, Types.INTEGER),
                (rs, rowNum) -> buildFragment(rs));
    }

    public void setUpdateCartById(@NonNull int id_cart, User id_user, LocalDate date){
        SqlParameterSource parameters = new MapSqlParameterSource()
                .addValue("id_cart", id_cart, Types.INTEGER)
                .addValue("id_user", id_user, Types.INTEGER)
                .addValue("date", date, Types.DATE);
        jdbc.update(UPDATE_CART_BY_ID, parameters);

    }

    public void deleteCartById(@NonNull int id_cart){
        jdbc.query(DELETE_BY_ID,
                new MapSqlParameterSource()
                        .addValue("id_cart", id_cart, Types.INTEGER),
                (rs, rowNum) -> buildFragment(rs));
    }

    private static final String DELETE_BY_ID = "DELETE FROM cart WHERE id_cart= :id_cart";
    private static final String UPDATE_CART_BY_ID = "UPDATE cart SET id_user= : id_user, date= :date" +
            " WHERE id_cart =:id_cart";
    private static final String SELECT_BY_USER = "SELECT * FROM cart WHERE id_user = :id_user";
    private static final String SELECT_BY_DATE_RANGE = "SELECT * FROM cart WHERE date >= :date1 AND date <= :date2";
    private static final String SELECT_BY_DATE = "SELECT * FROM cart WHERE date = :date";
    private static final String SELECT_BY_ID = "SELECT * FROM cart WHERE id_cart = :id_cart";
    private static final String INSERT_SQL = "INSERT INTO cart(id_user, date) VALUES(:id_user, :date)";
    private static final String SELECT_ALL_CART = "SELECT * FROM cart";

    private final NamedParameterJdbcTemplate jdbc;

    public CartRepository(DataSource ds) {
        this.jdbc = new NamedParameterJdbcTemplate(ds);
    }
    private Cart buildFragment(ResultSet rs) throws SQLException {
        return Cart.builder()
                .id_cart(rs.getInt("id_cart"))
                .date(rs.getDate("date").toLocalDate())
                .build();

    }
}
