package com.example.testing_project_one;

import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.sql.*;

/**
 * @author Pavel
 */
public class MoneyController{
    @FXML TextField money_up;
    @FXML TextArea comment_up;
    @FXML TextField money_down;
    @FXML TextArea comment_down;
    @FXML Button up;
    @FXML Button down;

    public void new_up() throws SQLException {
        // Закрытие окна
        Stage stage_del = (Stage) up.getScene().getWindow();
        stage_del.close();

        Connection connection = DriverManager.getConnection("jdbc:sqlite:the_yy.db");
        Statement statement = connection.createStatement();
        try {
            String sql_up_money = "INSERT INTO money (all_the_money) VALUES ('" +
                    (statement.executeQuery("SELECT all_the_money FROM money").getInt(1) +
                            Integer.parseInt(money_up.getText())) + "')";
            statement.executeUpdate("DELETE FROM money");
            statement.executeUpdate(sql_up_money);
            Date date = new Date(System.currentTimeMillis());
            statement.executeUpdate("INSERT INTO changes (day, comment) VALUES ('" + date + "', 'Увеличен бюджет на "
                            + money_up.getText() + ". По причине: " + comment_up.getText() + "')");
        }catch (NumberFormatException e) {
            Stage stage = new Stage();
            stage.setMinHeight(100);
            stage.setMinWidth(400);
            stage.setTitle("Ошибка");
            BorderPane borderPane = new BorderPane();
            Text text = new Text("Некорректные данные!!!");
            text.setStyle("--fxbackground-color: red");
            borderPane.setCenter(text);
            Scene scene = new Scene(borderPane);
            stage.setScene(scene);
            stage.show();
        }
        connection.close();
    }

    public void new_down() throws SQLException {
        // Закрытие окна
        Stage stage_del = (Stage) down.getScene().getWindow();
        stage_del.close();

        Connection connection = DriverManager.getConnection("jdbc:sqlite:the_yy.db");
        Statement statement = connection.createStatement();
        try {
            String sql_up_money = "INSERT INTO money (all_the_money) VALUES ('" +
                    (statement.executeQuery("SELECT all_the_money FROM money").getInt(1) -
                            Integer.parseInt(money_down.getText())) + "')";
            statement.executeUpdate("DELETE FROM money");
            statement.executeUpdate(sql_up_money);
            Date date = new Date(System.currentTimeMillis());
            statement.executeUpdate("INSERT INTO changes (day, comment) VALUES ('" + date + "', 'Ушло из бюджета "
                   + money_down.getText() + ". По причине: "+ comment_down.getText() + "')");
        } catch (NumberFormatException e) {
            Stage stage = new Stage();
            stage.setMinHeight(100);
            stage.setMinWidth(400);
            stage.setTitle("Ошибка");
            BorderPane borderPane = new BorderPane();
            Text text = new Text("Некорректные данные!!!");
            text.setStyle("--fxbackground-color: red");
            borderPane.setCenter(text);
            Scene scene = new Scene(borderPane);
            stage.setScene(scene);
            stage.show();
        }
        connection.close();
    }

}
