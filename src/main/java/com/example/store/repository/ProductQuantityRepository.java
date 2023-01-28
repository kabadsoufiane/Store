package com.example.store.repository;

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
import java.util.List;

@Repository
public class ProductQuantityRepository {

    public void insertData(@NonNull ProductQuantity productQuantities) {
        SqlParameterSource parameters = new MapSqlParameterSource()
                .addValue("product_id", productQuantities.getProduct_id(), Types.INTEGER)
                .addValue("quantity", productQuantities.getQuantity(), Types.INTEGER);
        jdbc.update(INSERT_SQL, parameters);
    }

    public List<ProductQuantity> getProductQuantities(){
        return jdbc.query(SELECT_ALL_PRODUCTSQUANTITY, (rs, rowNum) -> buildFragment(rs));
    }

    public List<ProductQuantity> getProductQuantitiesById(@NonNull int id_product){
        return jdbc.query(SELECT_ALL_PRODUCTSQUANTITY_BYID,
                new MapSqlParameterSource()
                        .addValue("id_product", id_product, Types.INTEGER),
                (rs, rowNum) -> buildFragment(rs));
    }

    public void updateProductQuantitiesById(@NonNull int id_product, int productquantity){
        SqlParameterSource parameters = new MapSqlParameterSource()
                .addValue("id_product", id_product, Types.INTEGER)
                .addValue("productquantity", productquantity, Types.INTEGER);
        jdbc.update(UPDATE_QUANTITY_BY_ID, parameters);
    }

    public void deleteProductQuantitiesById(@NonNull int id_product){
        jdbc.query(DELETE_BY_ID,
                new MapSqlParameterSource()
                        .addValue("id_product", id_product, Types.INTEGER),
                (rs, rowNum) -> buildFragment(rs));
    }

    private static final String DELETE_BY_ID = "DELETE FROM productquantity WHERE id_product= :id_product";
    private static final String SELECT_ALL_PRODUCTSQUANTITY = "SELECT * FROM productquantity";
    private static final String SELECT_ALL_PRODUCTSQUANTITY_BYID = "SELECT * FROM productquantity WHERE id_product= :id_product";
    private static final String UPDATE_QUANTITY_BY_ID = "UPDATE productquantity SET productquantity= : productquantity";
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
