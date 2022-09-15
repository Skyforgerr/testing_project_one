package com.example.testing_project_one;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * @author Ivan 15.09.2022
 */
public class ConnectionClass {
    Connection connection = DriverManager.getConnection("jdbc:sqlite:the_yy.db");

    public ConnectionClass() throws SQLException {
    }
}
