package com.bruno.tracker.models;

import java.sql.Timestamp;
import java.util.List;

public class Activity {
    public int Id;
    public Timestamp CreationDate;
    public Timestamp UpdateDate;
    public String Title;
    public String Content;
    public List<Comment> Comments;
}
