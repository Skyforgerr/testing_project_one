package com.example.testing_project_one;

import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.sql.*;

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
            Date date = new Date(System.currentTimeMillis());
            System.out.println(date);
            String new_change = "INSERT INTO changes (id_goods, day, comment) VALUES ('"+
                    statement.executeQuery("SELECT id_goods FROM GOODS WHERE id_goods = " +
                            "(SELECT MAX(id_goods) FROM GOODS)").getInt(1) + "', '" + date + "', 'Добавлен товар " +
                    goods.getName() + " в количестве " + goods.getAmount() + " с затратами " + goods.getCost_out() + "')";
            statement.executeUpdate(new_change);
            String out_money = "INSERT INTO money (all_the_money, all_the_lost) VALUES ('"+
                    (statement.executeQuery("SELECT all_the_money FROM money").getInt(1) -
                    goods.getCost_out()) + "', '" +
                    (statement.executeQuery("SELECT all_the_lost FROM money").getInt(1) -
                            goods.getCost_out()) + "')";
            statement.executeUpdate("DELETE FROM money");
            statement.executeUpdate(out_money);
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
