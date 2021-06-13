package com.holidaymakers.dao.rowmapper;

import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import com.holidaymakers.model.User;


public class UserRowMapper implements RowMapper<User> {

    @Override
    public User mapRow(ResultSet rs, int rowNum) throws SQLException {

        User user = new User();
        user.setId(rs.getLong("id"));
        user.setName(rs.getString("name"));
        user.setEmail(rs.getString("email"));
        user.setPhone(rs.getLong("phone"));
        user.setPassword(rs.getString("password"));
        user.setRegistered_date(rs.getDate("registered_date"));
        user.setRole(rs.getString("role"));

        return user;

    }
}
