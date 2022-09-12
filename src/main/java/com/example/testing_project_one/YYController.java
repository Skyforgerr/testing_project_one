package com.example.testing_project_one;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Group;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Objects;

/**
 * @author Pavel
 */
public class YYController {
    Stage stage_change = new Stage();
    @FXML Button good;
    @FXML Button sale;
    @FXML Button up;
    @FXML Button down;
    @FXML Button del;

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
        stage_change.setMinHeight(400);
        stage_change.setMinWidth(620);
        stage_change.setMaxHeight(400);
        stage_change.setMaxWidth(620);
                Parent main_content = FXMLLoader.load(
                    Objects.requireNonNull(getClass().getClassLoader().getResource("new_change.fxml")));
                Scene change = new Scene(main_content);
        stage_change.setScene(change);
        stage_change.setTitle("Выбор изменения");
        stage_change.show();
    }

    public void good_window() throws IOException {
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

    public void sale_window() throws IOException {
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
        Stage stage = (Stage) del.getScene().getWindow();
        stage.close();
        Parent sale_content = FXMLLoader.load(
                Objects.requireNonNull(getClass().getClassLoader().getResource("del.fxml")));
        Scene sale_scene = new Scene(sale_content);
        stage_change.setScene(sale_scene);
        stage_change.setTitle("Удаление товара");
        stage_change.show();
    }
    // работа с выводом в верхнюю таблицу
    private ObservableList<Goods> goodsData = FXCollections.observableArrayList();

    @FXML
    private TableView<Goods> tableGoods;

    @FXML
    private TableColumn<Goods, Integer> idColumn;

    @FXML
    private TableColumn<Goods, String> nameColumn;

    @FXML
    private TableColumn<Goods, Integer> amountColumn;

    @FXML
    private TableColumn<Goods, Integer> costOutColumn;

    @FXML
    private TableColumn<Goods, Integer> costInColumn;

    @FXML
    private TableColumn<Goods, Integer> profitColumn;


    @FXML
    private void initialize(){
        initData();

        idColumn.setCellValueFactory(new PropertyValueFactory<Goods, Integer>("id"));
        nameColumn.setCellValueFactory(new PropertyValueFactory<Goods, String>("name"));
        amountColumn.setCellValueFactory(new PropertyValueFactory<Goods, Integer>("amount"));
        costOutColumn.setCellValueFactory(new PropertyValueFactory<Goods, Integer>("costOut"));
        costInColumn.setCellValueFactory(new PropertyValueFactory<Goods, Integer>("costIn"));
        profitColumn.setCellValueFactory(new PropertyValueFactory<Goods, Integer>("profit"));

        tableGoods.setItems(goodsData);
    }

    private void initData(){
        goodsData.add(new Goods("name1", 2, 4, 3, 3));
    }

}
