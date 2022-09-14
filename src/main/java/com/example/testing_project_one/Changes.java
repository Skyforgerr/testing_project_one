package com.example.testing_project_one;

import java.util.Date;

/**
 * @author Ivan 15.09.2022
 */
public class Changes {
    private Date date;
    private String comment;
    Changes(Date date, String comment){
        this.date = date;
        this.comment = comment;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(java.util.Date date) {
        this.date = date;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }
}
