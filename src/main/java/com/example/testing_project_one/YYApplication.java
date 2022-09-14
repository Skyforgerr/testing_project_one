package com.example.testing_project_one;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TableView;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

import java.util.Objects;


/**
 * @author Pavel
 */
public class YYApplication extends Application {
    @Override
    public void start(Stage stage) throws Exception {
        YYController yyController = new YYController();
        yyController.startDataBase();
        Parent content = FXMLLoader.load(Objects.requireNonNull(getClass().getClassLoader().getResource("YY.fxml")));
        // Устанавливаем минимальный размер окна
        stage.setMinHeight(550);
        stage.setMinWidth(600);
        AnchorPane anchorPane = new AnchorPane();
        Scene scene = new Scene(anchorPane);
        anchorPane.getChildren().add(content);

        stage.setScene(scene);

        stage.setTitle("УУ Учёт, Управление");
        stage.show();
        stage.setOnCloseRequest(new EventHandler<WindowEvent>() {
            @Override
            public void handle(WindowEvent t) {
                Platform.exit();
                System.exit(0);
            }
        });
    }

    public static void main(String[] args) {
        launch();
    }
}
