package com.example.testing_project_one;

import javafx.fxml.FXMLLoader;
import javafx.scene.Group;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.util.Objects;

/**
 * @author Pavel
 */
public class YYController {
    public void settings(){
        Stage stage = new Stage();
        Group group = new Group();
        Scene scene = new Scene(group);
        Button button = new Button("Удалить всё");
        button.setStyle("-fx-background-color: Red; -fx-border-color: darkred; -fx-border-radius: 50px; -fx-padding: 10px");
        button.minHeight(100);
        button.minWidth(100);

        group.getChildren().add(button);
        stage.setTitle("Настройки");
        stage.setMinWidth(300);
        stage.setMinHeight(200);
        stage.setScene(scene);
        stage.show();
    }

    public void new_good() throws Exception{
        AnchorPane anchorPane = new AnchorPane();
        Stage stage = new Stage();
        stage.setMinHeight(400);
        stage.setMinWidth(600);
        Parent content = FXMLLoader.load(Objects.requireNonNull(getClass().getClassLoader().getResource("new_good.fxml")));
        Scene scene = new Scene(anchorPane);
        anchorPane.getChildren().add(content);
        stage.setTitle("Добавление товара");
        stage.setScene(scene);
        stage.show();

    }

    public void new_change() throws Exception{
        Stage stage = new Stage();
        stage.setMinHeight(400);
        stage.setMinWidth(600);
        Parent content = FXMLLoader.load(
                Objects.requireNonNull(getClass().getClassLoader().getResource("new_change.fxml")));
        Scene scene = new Scene(content);
        stage.setTitle("Выбор добавляемого изменения");
        stage.setScene(scene);
        stage.show();
    }
}
