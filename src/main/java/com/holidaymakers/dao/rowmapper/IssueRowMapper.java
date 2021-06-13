package com.holidaymakers.dao.rowmapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.holidaymakers.model.Issue;

public class IssueRowMapper implements RowMapper<Issue> {
	
	public Issue mapRow(ResultSet rs, int rowNum) throws SQLException{
		Issue issue = new Issue();
		issue.setId(rs.getLong("id"));
		issue.setSubject(rs.getString("subject"));
		issue.setReply(rs.getString("reply"));
		issue.setMessage(rs.getString("message"));
		issue.setUser_id(rs.getLong("user_id"));

		return issue;
	}

}
