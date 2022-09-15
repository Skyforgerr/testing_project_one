package com.example.testing_project_one;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

import java.net.URL;
import java.sql.*;
import java.util.ResourceBundle;

/**
 * @author Ivan 14.09.2022
 */
public class SaleController extends ConnectionClass{
    private ObservableList<Goods> goodsData = FXCollections.observableArrayList();
    @FXML private TableView<Goods> tableView;
    @FXML private TableColumn<Goods, Integer> idGoodsColumn;
    @FXML private TableColumn<Goods, String> nameGoodsColumn;

    public SaleController() throws SQLException {
    }

    @FXML
    private void initialize() throws SQLException {
        new_dataSmall();
        try {
            idGoodsColumn.setCellValueFactory(new PropertyValueFactory<Goods, Integer>("id_goods"));
            nameGoodsColumn.setCellValueFactory(new PropertyValueFactory<Goods, String>("name"));
            tableView.setItems(goodsData);
        }catch(NullPointerException e){
            e.printStackTrace();
        }
    }
    @FXML
    private void new_dataSmall() throws SQLException{
        //Connection connection = DriverManager.getConnection("jdbc:sqlite:the_yy.db");
        Statement statement = connection.createStatement();
        ResultSet rs = statement.executeQuery("select * from GOODS");
        while (rs.next()) {
            goodsData.add(new Goods(rs.getInt(1), rs.getString(2)));
        }
    }
}
