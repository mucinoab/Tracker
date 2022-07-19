package com.bruno.tracker.models;

import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class CommentMapper implements RowMapper<Comment> {

    @Override
    public Comment mapRow(ResultSet rs, int rowNum) throws SQLException {
        Comment comment = new Comment();

        comment.Id = rs.getInt("id");
        comment.ActivityId = rs.getInt("activity_id");
        comment.CreationDate = rs.getTimestamp("creation_date");
        comment.UpdateDate = rs.getTimestamp("update_date");
        comment.Author = rs.getString("author");
        comment.Content = rs.getString("content");

        return comment;
    }
}
