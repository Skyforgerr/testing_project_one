package com.example.testing_project_one;

import java.util.Date;

/**
 * @author Ivan 15.09.2022
 */
public class Changes {
    private Date day;
    private String comment;
    Changes(Date day, String comment){
        this.day = day;
        this.comment = comment;
    }

    public Date getDay() {
        return day;
    }

    public void setDay(java.util.Date day) {
        this.day = day;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }
}
