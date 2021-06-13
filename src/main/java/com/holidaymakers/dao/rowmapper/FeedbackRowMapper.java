package com.holidaymakers.dao.rowmapper;

import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import com.holidaymakers.model.Feedback;


public class FeedbackRowMapper implements RowMapper<Feedback> {

    @Override
    public Feedback mapRow(ResultSet rs, int rowNum) throws SQLException {

        Feedback feedback = new Feedback();
        feedback.setId(rs.getLong("id"));
        feedback.setMessage(rs.getString("name"));

        return feedback;

    }
}

