package com.example.testing_project_one;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * @author Pavel
 */
public class SettingsController {
    Connection connection;
    @FXML Button del_all_good;
    @FXML Button del_all_changes;
    @FXML Button del_all;
    public void del_goods() throws SQLException {
        // Закрытие окна
        Stage stage = (Stage) del_all_good.getScene().getWindow();
        stage.close();

        connection = DriverManager.getConnection("jdbc:sqlite:the_yy.db");
        try {
            Statement statement = connection.createStatement();
            statement.executeUpdate("delete from goods");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public void del_changes() throws SQLException {
        // Закрытие окна
        Stage stage = (Stage) del_all_changes.getScene().getWindow();
        stage.close();

        connection = DriverManager.getConnection("jdbc:sqlite:the_yy.db");
        try {
            Statement statement = connection.createStatement();
            statement.executeUpdate("delete from changes");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public void del_all_bd() throws SQLException {
        Stage stage = (Stage) del_all.getScene().getWindow();
        stage.close();

        connection = DriverManager.getConnection("jdbc:sqlite:the_yy.db");
        try {
            Statement statement = connection.createStatement();
            statement.executeUpdate("delete from changes");
            statement.executeUpdate("delete from goods");
            statement.executeUpdate("DELETE FROM money");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
