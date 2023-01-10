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
