package com.holidaymakers.dao.rowmapper;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import com.holidaymakers.model.Tour;


public class TourRowMapper implements RowMapper<Tour> {

    @Override
    public Tour mapRow(ResultSet rs, int rowNum) throws SQLException {

        Tour tour = new Tour();
        tour.setId(rs.getLong("id"));
        tour.setName(rs.getString("name"));
        tour.setDetails(rs.getString("details"));
        tour.setPlaces(rs.getString("places"));
        tour.setNo_of_days(rs.getInt("no_of_days"));
        tour.setPrice(rs.getDouble("price"));

        return tour;

    }
}
