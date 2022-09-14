package com.example.testing_project_one;

import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * @author Pavel
 */
public class New_goodController {
    @FXML Button new_good;
    @FXML TextField name;
    @FXML TextField amount;
    @FXML TextField cost_out;
    @FXML TextField cost_in;
    Connection connection;
    public void insertGoods() throws SQLException {
        // Закрытие окна
        Stage stage_del = (Stage) new_good.getScene().getWindow();
        stage_del.close();

        connection = DriverManager.getConnection("jdbc:sqlite:the_yy.db");
        Statement statement;
        try {
            statement = connection.createStatement();
        } catch (Exception e) {
            throw new RuntimeException("unhandled", e);
        }
        Goods goods;
        try {
            goods = new Goods(name.getText(), Integer.parseInt(amount.getText()), Integer.parseInt(cost_out.getText()),
                    Integer.parseInt(cost_in.getText()),
                    Integer.parseInt("-" + cost_in.getText()));
            System.out.println("Adding goods to the table");
            String addingGoods = "INSERT INTO goods (name, amount, cost_out, cost_in, profit) VALUES ('" +
                    goods.getName() + "', '" + goods.getAmount() + "', '" + goods.getCost_out() +
                    "', '" + goods.getCost_in() + "', '" + goods.getProfit() + "');";
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
}
