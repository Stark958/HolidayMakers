package com.holidaymakers.dao.rowmapper;

import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import com.holidaymakers.model.Bookings;



public class BookingsRowMapper implements RowMapper<Bookings> {

    @Override
    public Bookings mapRow(ResultSet rs, int rowNum) throws SQLException {

        Bookings bookings = new Bookings();
        bookings.setId(rs.getLong("ID"));
        bookings.setStart_date(rs.getDate("start_date"));
        bookings.setNo_of_persons(rs.getInt("no_of_persons"));
        bookings.setFood_type(rs.getString("food_type"));
        bookings.setAccomodation(rs.getString("accomodation"));
        bookings.setPayment_mode(rs.getString("payment_mode"));
        bookings.setPayment_info(rs.getString("payment_info"));
        bookings.setPrice(rs.getDouble("price"));
        bookings.setBooked_date(rs.getDate("booked_date"));
        bookings.setCancel_msg(rs.getString("cancel_msg"));
        bookings.setStatus(rs.getString("status"));
        bookings.setTour_id(rs.getLong("tour_id"));
        bookings.setUser_id(rs.getLong("user_id"));

        return bookings;

    }
}
