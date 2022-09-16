package com.example.testing_project_one;

import java.util.Date;

/**
 * @author Ivan 15.09.2022
 */
public class Changes {
    private String day;
    private String comment;
    Changes(String day, String comment){
        this.day = day;
        this.comment = comment;
    }

    public String getDay() {
        return day;
    }

    public void setDay(String day) {
        this.day = day;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }
}
