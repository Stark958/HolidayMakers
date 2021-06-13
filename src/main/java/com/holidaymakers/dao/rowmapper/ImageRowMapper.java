package com.holidaymakers.dao.rowmapper;

import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import com.holidaymakers.model.Image;


public class ImageRowMapper implements RowMapper<Image> {

    @Override
    public Image mapRow(ResultSet rs, int rowNum) throws SQLException {

        Image image = new Image();
        image.setId(rs.getLong("ID"));
        image.setName(rs.getString("name"));
        image.setContent_type(rs.getString("content_type"));
        image.setSize(rs.getLong("size"));
        image.setData(rs.getBytes("data"));
        return image;

    }
}
