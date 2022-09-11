package com.example.testing_project_one;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

/**
 * @author Ivan 12.09.2022
 */
public class TableController {
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
        goodsData.add(new Goods("name1", 2, 3, 4));
    }
}
