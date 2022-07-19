package com.bruno.tracker.models;

import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class ActivityMapper implements RowMapper<Activity> {

    @Override
    public Activity mapRow(ResultSet rs, int rowNum) throws SQLException {
        Activity activity = new Activity();

        activity.Id = rs.getInt("id");
        activity.CreationDate = rs.getTimestamp("creation_date");
        activity.UpdateDate = rs.getTimestamp("update_date");
        activity.Title = rs.getString("title");
        activity.Content = rs.getString("content");

        return activity;
    }
}