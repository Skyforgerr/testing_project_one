package com.example.testing_project_one;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Group;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.MenuItem;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.Objects;
import java.util.ResourceBundle;

/**
 * @author Pavel
 */


public class YYController implements Initializable {
    @FXML
    public MenuItem settingsSettings;
    @FXML
    private void settingsMaking(Stage stage) throws IOException {
        Parent content = FXMLLoader.load(Objects.requireNonNull(getClass().getClassLoader().getResource("my.fxml")));
        Group group_settings = new Group();
        Scene settings = new Scene(group_settings, 300, 200);
        stage.setScene(settings);
        stage.setTitle("Настройки");
        stage.show();
    }
    @FXML
    private void settingsWindow(ActionEvent actionEvent)throws Exception{
        settingsMaking(new Stage());

    }



    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }
}
