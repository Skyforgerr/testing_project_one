package com.example.testing_project_one;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Group;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.lang.reflect.InvocationTargetException;
import java.util.Objects;


/**
 * @author Pavel
 */
public class YYApplication extends Application {

    @Override
    public void start(Stage stage) throws Exception {
        Parent content = FXMLLoader.load(getClass().getClassLoader().getResource("YY.fxml"));
        // Устанавливаем минимальный размер окна
        stage.setMinHeight(550);
        stage.setMinWidth(700);
        AnchorPane anchorPane = new AnchorPane();
        Scene scene = new Scene(anchorPane);
        anchorPane.getChildren().add(content);

        stage.setScene(scene);

        stage.setTitle("УУ Учёт, Управление");
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}
