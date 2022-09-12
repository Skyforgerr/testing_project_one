package com.example.testing_project_one;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.sql.*;

/**
 * @author Ivan 12.09.2022
 */
public class DataBase {
    @FXML
    TextField name;
    @FXML
    TextField amount;
    @FXML
    TextField cost_out;
    @FXML
    TextField cost_in;
    Connection connection;
    public void startDataBase() {
        try {
            Class.forName("org.sqlite.JDBC");
            connection = DriverManager.getConnection("jdbc:sqlite:the_yy.db");
            System.out.println("Connection established...");
            createDataBase();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
    public void createDataBase() throws SQLException {
        Statement statement = connection.createStatement();
        String tableA = "CREATE TABLE IF NOT EXISTS GOODS" +
                "(id_goods int AUTO_INCREMENT, " +
                "name text, " +
                "amount int, " +
                "cost_out int, " +
                "cost_in int, " +
                "profit int, " +
                "PRIMARY KEY (id_goods));";
        statement.executeUpdate(tableA);
        System.out.println("Created the table for goods...");
        System.out.println("Creating the table for changes...");
        String sql1 = "CREATE TABLE IF NOT EXISTS CHANGES" +
                "(id_changes int AUTO_INCREMENT, " +
                "id_goods int, " +
                "comment text, " +
                "PRIMARY KEY (id_changes), " +
                "FOREIGN KEY (id_goods) REFERENCES goods (id_goods));";
        statement.executeUpdate(sql1);
        System.out.println("Created table for changes...");
        System.out.println("Creating the table for money...");
        String sql2 = "CREATE TABLE IF NOT EXISTS MONEY" +
                "(all_the_money int, " +
                "all_the_lost int);";
        statement.executeUpdate(sql2);
        System.out.println("Created table for money...");
    }
    public void insertGoods() throws SQLException {
        connection = DriverManager.getConnection("jdbc:sqlite:the_yy.db");
        Statement statement;
        try {
            statement = connection.createStatement();
        } catch (Exception e) {
            throw new RuntimeException("unhandled", e);
        }
        Goods goods = null;
        try {
            goods = new Goods(name.getText(), Integer.parseInt(amount.getText()), Integer.parseInt(cost_out.getText()), Integer.parseInt(cost_in.getText()), (Integer.parseInt(cost_in.getText()) - Integer.parseInt(cost_out.getText())));
            System.out.println("Adding goods to the table");
            String addingGoods = "INSERT INTO GOODS (name, amount, cost_out, cost_in, profit)" +
                    "VALUES ('" + goods.getName() + "', '" + goods.getAmount() + "', '" + goods.getCost_out() +
                    "', '" + goods.getCost_in() + "', '" + (goods.getCost_in() - goods.getCost_out()) + "');";
            statement.executeUpdate(addingGoods);
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
    }
    public void closeDataBase(){
        try{
            connection.close();
            System.out.println("Connection ended...");
        }catch (Exception e){
            e.printStackTrace();
        }

    }


}
