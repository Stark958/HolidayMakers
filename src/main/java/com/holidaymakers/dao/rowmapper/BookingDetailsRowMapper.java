package com.holidaymakers.dao.rowmapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.holidaymakers.model.BookingDetails;

public class BookingDetailsRowMapper implements RowMapper<BookingDetails> {
	@Override
	public BookingDetails mapRow(ResultSet rs, int rowNum) throws SQLException {
		BookingDetails bt = new BookingDetails();
		bt.setId(rs.getLong("id"));
		bt.setName(rs.getString("name"));
		bt.setAge(rs.getInt("age"));
		bt.setGender(rs.getString("gender"));
		bt.setId_proof_number(rs.getLong("id_proof_number"));
		bt.setBooking_id(rs.getLong("booking_id"));
		return bt;
	}
}
