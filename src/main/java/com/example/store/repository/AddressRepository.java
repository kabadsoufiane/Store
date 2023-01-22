package com.example.store.repository;

import com.example.store.domain.Address;
import com.example.store.domain.Geolocation;
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
public class AddressRepository {

    public void insertData(@NonNull Address addresses) {
        SqlParameterSource parameters = new MapSqlParameterSource()
                .addValue("country", addresses.getCountry(), Types.VARCHAR)
                .addValue("city", addresses.getCity(), Types.VARCHAR)
                .addValue("number", addresses.getNumber(), Types.INTEGER)
                .addValue("zipcode", addresses.getZipcode(), Types.VARCHAR)
                .addValue("lat", addresses.getGeolocation(), Types.DECIMAL)
                .addValue("longitude", addresses.getGeolocation(), Types.DECIMAL);
        jdbc.update(INSERT_SQL, parameters);
    }

    public List<Address> getAddresses(){
        return jdbc.query(SELECT_ALL_ADRESSES, (rs, rowNum) -> buildFragment(rs));
    }

    public List<Address> getAddressById(@NonNull int id_user){
        return jdbc.query(SELECT_ALL_ADDRESSES_BYID ,
                new MapSqlParameterSource()
                        .addValue("id_user", id_user, Types.INTEGER),
                (rs, rowNum) -> buildFragment(rs));
    }

    public void updateAddressById(@NonNull int id_user, String country, String city, int number, String zipcode,
                                  double latitude, double longitude){
        SqlParameterSource parameters = new MapSqlParameterSource()
                .addValue("id_user", id_user, Types.INTEGER)
                .addValue("country", country, Types.VARCHAR)
                .addValue("city", city, Types.VARCHAR)
                .addValue("number", number, Types.INTEGER)
                .addValue("zipcode", zipcode, Types.VARCHAR)
                .addValue("latitude", latitude, Types.NUMERIC)
                .addValue("longitude", longitude, Types.NUMERIC);
        jdbc.update(UPDATE_ADDRESS_BY_ID, parameters);
    }

    public void deleteAddressById(@NonNull int id_user){
        jdbc.query(DELETE_BY_ID,
                new MapSqlParameterSource()
                        .addValue("id_user", id_user, Types.INTEGER),
                (rs, rowNum) -> buildFragment(rs));
    }

    private static final String DELETE_BY_ID = "DELETE FROM address WHERE id_user= :id_user";
    private static final String SELECT_ALL_ADRESSES = "SELECT * FROM address";
    private static final String SELECT_ALL_ADDRESSES_BYID = "SELECT * FROM address WHERE id_user= :id_user";
    private static final String UPDATE_ADDRESS_BY_ID = "UPDATE address SET country= :country, " +
            "city= :city, number= :number, zipcode= :zipcode, latitude= :latitude, longitude= :longitude " +
            "WHERE id_user= :id_user";
    private static final String INSERT_SQL = "INSERT INTO " +
            "address(country, city, number, zipcode, latitude, longitude) " +
            "VALUES(:country, :city, :number, :zipcode, :latitude, :longitude)";
    private final NamedParameterJdbcTemplate jdbc;

    public AddressRepository(DataSource ds) {
        this.jdbc = new NamedParameterJdbcTemplate(ds);
    }

    private Address buildFragment(ResultSet rs) throws SQLException {
        return Address.builder()
                .country(rs.getString("country"))
                .city(rs.getString("city"))
                .number(rs.getInt("number"))
                .zipcode(rs.getString("zipcode"))
                .geolocation((List<Geolocation>) rs.getObject("lat"))
                .geolocation((List<Geolocation>) rs.getObject("longitude"))
                .build();
    }
}
