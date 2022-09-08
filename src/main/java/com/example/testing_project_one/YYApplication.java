package com.example.testing_project_one;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Group;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.lang.reflect.InvocationTargetException;
import java.util.Objects;


/**
 * @author Pavel
 */
public class YYApplication extends Application {
    @FXML
    public MenuItem settingsSettings;
    @Override
    public void start(Stage stage) throws Exception {


        Group group = new Group();
        Parent content = FXMLLoader.load(Objects.requireNonNull(getClass().getClassLoader().getResource("my.fxml")));
        Scene scene = new Scene(group, 750, 500);
        // Подключаем bootstrap
        scene.getStylesheets().add("org/kordamp/bootstrapfx/bootstrapfx.css");
        BorderPane borderPane = new BorderPane();
        borderPane.setMinHeight(500);
        borderPane.setMinWidth(750);

        stage.setScene(scene);
        // Создание меню бара
        MenuBar menuBar = new MenuBar();
        Menu menu_one = new Menu("Главное");
        //MenuItem item_one = new MenuItem("Настройки");
//        settingsSettings.setOnAction(new EventHandler<ActionEvent>() {
//            @Override
//            public void handle(ActionEvent actionEvent) {
//                try {
//                    settings(new Stage());
//                } catch (Exception e) {
//                    e.printStackTrace();
//                }
//            }
//        });
        //menu_one.getItems().add(item_one);
        menuBar.getMenus().add(menu_one);
        VBox vBox = new VBox();
        vBox.getChildren().add(menuBar);
        //borderPane.setTop(vBox);


        Button button = new Button("Нажми");
        button.getStyleClass().add("btn");
        button.setStyle("");
        //borderPane.setBottom(button);

        group.getChildren().add(content);
        stage.setTitle("УУ Учёт, Управление");
        stage.show();
    }
//    public void settings(Stage stage) throws Exception {
//        Group group_settings = new Group();
//        Scene settings = new Scene(group_settings, 300, 200);
//        stage.setScene(settings);
//        stage.setTitle("Настройки");
//
//        stage.show();
//    }
//    @FXML
//    public void settingsWindow(ActionEvent actionEvent) throws Exception {
//        settings(new Stage());
//    }

    public static void main(String[] args) {
        launch();
    }
}
