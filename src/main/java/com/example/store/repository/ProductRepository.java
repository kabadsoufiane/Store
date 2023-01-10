package com.example.store.repository;

import com.example.store.domain.Product;
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
public class ProductRepository {
    public void insertData(@NonNull Product products) {
        SqlParameterSource parameters = new MapSqlParameterSource()
                .addValue("title", products.getTitle(), Types.VARCHAR)
                .addValue("price", products.getPrice(), Types.DECIMAL)
                .addValue("category", products.getCategory(), Types.VARCHAR)
                .addValue("description", products.getDescription(), Types.VARCHAR)
                .addValue("image", products.getImage(), Types.VARCHAR);
        jdbc.update(INSERT_SQL, parameters);
    }

    public List<Product> getAllProduct(){
        return jdbc.query(SELECT_ALL_PRODUCTS, (rs, rowNum) -> buildFragment(rs));
    }

    public List<Product> getProductById(@NonNull int id_product){
        return jdbc.query(SELECT_BY_ID,
        new MapSqlParameterSource()
                .addValue("id_product", id_product, Types.INTEGER),
        (rs, rowNum) -> buildFragment(rs));
    }

    public List<Product> getAllCategories(){
        return jdbc.query(SELECT_ALL_CATEGORIES, (rs, rowNum) -> buildFragment(rs));
    }

    public List<Product> getProductByCategory(@NonNull String category){
        return jdbc.query(SELECT_BY_CATEGORY,
                new MapSqlParameterSource()
                        .addValue("category", category, Types.VARCHAR),
                (rs, rowNum) -> buildFragment(rs));
    }

    public void updateProductById(@NonNull int id_product, String title, double price, String category,
                                  String description, String image){
        SqlParameterSource parameters = new MapSqlParameterSource()
                .addValue("id_product", id_product, Types.INTEGER)
                .addValue("title", title, Types.VARCHAR)
                .addValue("price", price, Types.DECIMAL)
                .addValue("category", category, Types.VARCHAR)
                .addValue("description", description, Types.VARCHAR)
                .addValue("image", image, Types.VARCHAR);
        jdbc.update(UPDATE_PRODUCT_BY_ID, parameters);

    }

    public void deleteProductsById(@NonNull int id_product){
        jdbc.query(DELETE_BY_ID,
                new MapSqlParameterSource()
                        .addValue("id_product", id_product, Types.INTEGER),
                (rs, rowNum) -> buildFragment(rs));
    }

    private static final String DELETE_BY_ID = "DELETE * FROM products WHERE id_product= :id_product";
    private static final String UPDATE_PRODUCT_BY_ID = "UPDATE products SET title= : title, price= :price, " +
            "category= :category, description= :description, image =:image WHERE id_product =:id_product";
    private static final String SELECT_BY_CATEGORY = "SELECT * FROM products WHERE category = :category";
    private static final String SELECT_ALL_CATEGORIES = "SELECT category FROM products";
    private static final String SELECT_BY_ID = "SELECT * FROM products WHERE id_product = :id_product";
    private static final String SELECT_ALL_PRODUCTS = "SELECT * FROM products";
    private static final String INSERT_SQL = "INSERT INTO " +
            "products(title, price, category, description, image) " +
            "VALUES(:title, :price, :category, :description, :image)";
    private final NamedParameterJdbcTemplate jdbc;

    public ProductRepository(DataSource ds) {
        this.jdbc = new NamedParameterJdbcTemplate(ds);
    }
    private Product buildFragment(ResultSet rs) throws SQLException {
        return Product.builder()
                .id_product(rs.getInt("id_product"))
                .title(rs.getString("title"))
                .category(rs.getString("category"))
                .image(rs.getString("image"))
                .price(rs.getDouble("price"))
                .description(rs.getString("description"))
                .build();
    }
}
