package com.example.testing_project_one;

import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
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
}
