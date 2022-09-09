package com.example.testing_project_one;

import javafx.fxml.FXMLLoader;
import javafx.scene.Group;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

/**
 * @author Pavel
 */
public class YYController {
    public void settings(){
        Stage stage = new Stage();
        Group group = new Group();
        Scene scene = new Scene(group);
        Button button = new Button("Удалить всё");
        button.setStyle("-fx-background-color: Red; -fx-border-style: 0px,0px,5px,5px; -fx-border-color: darkred; -fx-border-radius: 50px; -fx-padding: 10px");
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
        Parent content = FXMLLoader.load(getClass().getClassLoader().getResource("new_good.fxml"));
        Scene scene = new Scene(anchorPane);
        anchorPane.getChildren().add(content);
        stage.setTitle("Добавление товара");
        stage.setScene(scene);
        stage.show();

    }
}
