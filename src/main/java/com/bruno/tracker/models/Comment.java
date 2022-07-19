package com.bruno.tracker.models;

import java.sql.Timestamp;

public class Comment {
    public int Id;
    public int ActivityId;
    public Timestamp CreationDate;
    public Timestamp UpdateDate;
    public String Author;
    public String Content;

    public Comment() {
    }

    public Comment(String author, String content, String ActivityId) {
        this.Author = author;
        this.Content = content;
        this.ActivityId = Integer.parseInt(ActivityId.trim());
    }

    public String getContent() {
        return Content;
    }

    public void setContent(String content) {
        this.Content = content;
    }

    public String getAuthor() {
        return Author;
    }

    public void setAuthor(String author) {
        this.Author = author;
    }

    public int getActivityId() {
        return ActivityId;
    }

    public void setActivityId(String activityId) {
        this.ActivityId = Integer.parseInt(activityId.trim());
    }
}
