package com.example.store.repository;

import com.example.store.domain.Cart;
import com.example.store.domain.ProductQuantity;
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
public class ProductQuantityRepository {

    public void insertData(@NonNull ProductQuantity productQuantities) {
        SqlParameterSource parameters = new MapSqlParameterSource()
                .addValue("product_id", productQuantities.getProduct_id(), Types.INTEGER)
                .addValue("quantity", productQuantities.getQuantity(), Types.INTEGER);
        jdbc.update(INSERT_SQL, parameters);
    }

    private static final String INSERT_SQL = "INSERT INTO productQuantity(productquantity, productQuantity) VALUES(:product_id ,:productQuantity)";
    private final NamedParameterJdbcTemplate jdbc;

    public ProductQuantityRepository(DataSource ds) {
        this.jdbc = new NamedParameterJdbcTemplate(ds);
    }
    private ProductQuantity buildFragment(ResultSet rs) throws SQLException {
        return ProductQuantity.builder()
                .quantity(rs.getInt("quantity"))
                .build();
    }
}
