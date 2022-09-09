package com.example.testing_project_one;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.util.Objects;


/**
 * @author Pavel
 */
public class YYApplication extends Application {

    @Override
    public void start(Stage stage) throws Exception {
        Parent content = FXMLLoader.load(Objects.requireNonNull(getClass().getClassLoader().getResource("scenes/YY.fxml")));
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
