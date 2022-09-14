package com.example.testing_project_one;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Objects;

/**
 * @author Pavel
 */
public class New_changeController extends SaleController{
    @FXML Button good;
    @FXML Button sale;
    @FXML Button up;
    @FXML Button down;
    @FXML Button del;
    Stage stage_change = new Stage();

    public void good_window() throws IOException, SQLException {
        // Закрытие предыдущего окна
        initializeSmall();
        Stage stage = (Stage) good.getScene().getWindow();
        stage.close();

        stage_change.setMinHeight(400);
        stage_change.setMinWidth(650);
        Parent sale_content = FXMLLoader.load(
                Objects.requireNonNull(getClass().getClassLoader().getResource("good.fxml")));
        Scene sale_scene = new Scene(sale_content);
        stage_change.setScene(sale_scene);
        stage_change.setTitle("Изменение количества товара");
        stage_change.show();
    }

    public void sale_window() throws IOException, SQLException {
        // Закрытие предыдущего окна
        initializeSmall();
        Stage stage = (Stage) sale.getScene().getWindow();
        stage.close();

        stage_change.setMinHeight(400);
        stage_change.setMinWidth(650);
        Parent sale_content = FXMLLoader.load(
                Objects.requireNonNull(getClass().getClassLoader().getResource("sale.fxml")));
        AnchorPane anchorPane = new AnchorPane();
        anchorPane.getChildren().add(sale_content);
        Scene sale_scene = new Scene(anchorPane);
        stage_change.setScene(sale_scene);
        stage_change.setTitle("Продажа товара");
        stage_change.show();
    }

    public void up_window() throws IOException {
        // Закрытие предыдущего окна
        Stage stage = (Stage) up.getScene().getWindow();
        stage.close();

        stage_change.setMinHeight(400);
        stage_change.setMinWidth(297);
        Parent sale_content = FXMLLoader.load(
                Objects.requireNonNull(getClass().getClassLoader().getResource("up.fxml")));
        Scene sale_scene = new Scene(sale_content);
        stage_change.setScene(sale_scene);
        stage_change.setTitle("Увеличение бюджета");
        stage_change.show();
    }

    public void down_window() throws IOException {
        // Закрытие предыдущего окна
        Stage stage = (Stage) down.getScene().getWindow();
        stage.close();

        Parent sale_content = FXMLLoader.load(
                Objects.requireNonNull(getClass().getClassLoader().getResource("down.fxml")));
        Scene sale_scene = new Scene(sale_content);
        stage_change.setScene(sale_scene);
        stage_change.setTitle("Уменьшение бюджета");
        stage_change.show();
    }

    public void del_window() throws IOException {
        // Закрытие предыдущего окна
        Stage stage = (Stage) del.getScene().getWindow();
        stage.close();

        Parent sale_content = FXMLLoader.load(
                Objects.requireNonNull(getClass().getClassLoader().getResource("del.fxml")));
        Scene sale_scene = new Scene(sale_content);
        stage_change.setScene(sale_scene);
        stage_change.setTitle("Удаление товара");
        stage_change.show();
    }
}
