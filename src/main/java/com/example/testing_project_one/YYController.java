package com.example.testing_project_one;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Objects;

/**
 * @author Pavel
 */
public class YYController  extends MoneyController{
    @FXML Label allTheMoney;
    Stage stage_change = new Stage();
    Connection connection;

    //Запуск бд
    public void startDataBase() {
        try {
            Class.forName("org.sqlite.JDBC");
            connection = DriverManager.getConnection("jdbc:sqlite:the_yy.db");
            System.out.println("Connection established...");
            createDataBase();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public void createDataBase() throws SQLException {
        Statement statement = connection.createStatement();
        String tableA = "CREATE TABLE IF NOT EXISTS GOODS" +
                "(id_goods INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "name text, " +
                "amount int, " +
                "cost_out int, " +
                "cost_in int, " +
                "profit int); ";
        statement.executeUpdate(tableA);
        String sql1 = "CREATE TABLE IF NOT EXISTS CHANGES" +
                "(id_changes INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "id_goods int, " +
                "day datе," +
                "comment text, " +
                "FOREIGN KEY (id_goods) REFERENCES goods (id_goods));";
        statement.executeUpdate(sql1);
        String sql2 = "CREATE TABLE IF NOT EXISTS MONEY" +
                "(all_the_money int, " +
                "all_the_lost int);";
        statement.executeUpdate(sql2);
        System.out.println("Created all the tables...");
    }

    public void closeDataBase(){
        try{
            connection.close();
            System.out.println("Connection ended...");
        }catch (Exception e){
            e.printStackTrace();
        }

    }

    // работа с выводом в верхнюю таблицу
    private ObservableList<Goods> goodsData = FXCollections.observableArrayList();
    private ObservableList<Goods> saleData = FXCollections.observableArrayList();
    private ObservableList<Goods> amountData = FXCollections.observableArrayList();
    private ObservableList<Goods> deleteData = FXCollections.observableArrayList();
    private ObservableList<Changes> changesData = FXCollections.observableArrayList();
    @FXML private TableView<Goods> tableGoods;
    @FXML private TableColumn<Goods, Integer> idColumn;
    @FXML private TableColumn<Goods, String> nameColumn;
    @FXML private TableColumn<Goods, Integer> amountColumn;
    @FXML private TableColumn<Goods, Integer> costOutColumn;
    @FXML private TableColumn<Goods, Integer> costInColumn;
    @FXML private TableColumn<Goods, Integer> profitColumn;
    //вывод в таблицу с продажами
    @FXML private TableView<Goods> tableView;
    @FXML private TableColumn<Goods, Integer> idGoodsColumn;
    @FXML private TableColumn<Goods, String> nameGoodsColumn;
    //изменение количества товара
    @FXML private TableView<Goods> amountView;
    @FXML private TableColumn<Goods, Integer> idAmountColumn;
    @FXML private TableColumn<Goods, String> nameAmountColumn;
    //удаление товара из бд
    @FXML private TableView<Goods> deleteView;
    @FXML private TableColumn<Goods, Integer> idDeleteColumn;
    @FXML private TableColumn<Goods, String> nameDeleteColumn;
    //таблица с изменениями
    @FXML private TableView<Changes> changeView;
    @FXML private TableColumn<Changes, Date> dateColumn;
    @FXML private TableColumn<Changes, String> changeColumn;

    @FXML
    private void initialize() throws SQLException, ParseException {
        new_data();
        try {
            idColumn.setCellValueFactory(new PropertyValueFactory<Goods, Integer>("id_goods"));
            nameColumn.setCellValueFactory(new PropertyValueFactory<Goods, String>("name"));
            amountColumn.setCellValueFactory(new PropertyValueFactory<Goods, Integer>("amount"));
            costOutColumn.setCellValueFactory(new PropertyValueFactory<Goods, Integer>("cost_out"));
            costInColumn.setCellValueFactory(new PropertyValueFactory<Goods, Integer>("cost_in"));
            profitColumn.setCellValueFactory(new PropertyValueFactory<Goods, Integer>("profit"));

            tableGoods.setItems(goodsData);
        }catch(NullPointerException e){
            //e.printStackTrace();
        }

        new_dataSmall();
        try {
            idGoodsColumn.setCellValueFactory(new PropertyValueFactory<Goods, Integer>("id_goods"));
            nameGoodsColumn.setCellValueFactory(new PropertyValueFactory<Goods, String>("name"));
            tableView.setItems(saleData);
        }catch(NullPointerException e){
            //e.printStackTrace();
        }
        new_dataAmount();
        try {
            idAmountColumn.setCellValueFactory(new PropertyValueFactory<Goods, Integer>("id_goods"));
            nameAmountColumn.setCellValueFactory(new PropertyValueFactory<Goods, String>("name"));
            amountView.setItems(amountData);
        }catch(NullPointerException e){
            //e.printStackTrace();
        }
        new_dataDelete();
        try {
            idDeleteColumn.setCellValueFactory(new PropertyValueFactory<Goods, Integer>("id_goods"));
            nameDeleteColumn.setCellValueFactory(new PropertyValueFactory<Goods, String>("name"));
            deleteView.setItems(deleteData);
        }catch(NullPointerException e){
            //e.printStackTrace();
        }
        new_data();
        try {
            dateColumn.setCellValueFactory(new PropertyValueFactory<Changes, Date>("day"));
            changeColumn.setCellValueFactory(new PropertyValueFactory<Changes, String>("comment"));
            changeView.setItems(changesData);
        }catch(NullPointerException e){
            //e.printStackTrace();
        }


    }
    @FXML
    private void new_dataSmall() throws SQLException{
        saleData.clear();
        Connection connection = DriverManager.getConnection("jdbc:sqlite:the_yy.db");
        Statement statement = connection.createStatement();
        ResultSet rs = statement.executeQuery("select * from GOODS");
        while (rs.next()) {
            saleData.add(new Goods(rs.getInt(1), rs.getString(2)));
        }
    }
    @FXML
    private void new_dataAmount() throws SQLException{
        deleteData.clear();
        Connection connection = DriverManager.getConnection("jdbc:sqlite:the_yy.db");
        Statement statement = connection.createStatement();
        ResultSet rs = statement.executeQuery("select * from GOODS");
        while (rs.next()) {
            amountData.add(new Goods(rs.getInt(1), rs.getString(2)));
        }
    }
    @FXML
    private void new_dataDelete() throws SQLException{
        deleteData.clear();
        Connection connection = DriverManager.getConnection("jdbc:sqlite:the_yy.db");
        Statement statement = connection.createStatement();
        ResultSet rs = statement.executeQuery("select * from GOODS");
        while (rs.next()) {
            deleteData.add(new Goods(rs.getInt(1), rs.getString(2)));
        }
    }

//    @FXML
//    private void new_dataChange() throws SQLException, ParseException {
//        changesData.clear();
//        Connection connection = DriverManager.getConnection("jdbc:sqlite:the_yy.db");
//        Statement statement = connection.createStatement();
//        ResultSet rs = statement.executeQuery("select * from CHANGES");
//        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
//        while (rs.next()) {
//            java.util.Date date1;
//                changesData.add(new Changes(date1 = simpleDateFormat.parse(rs.getString(3)), rs.getString(4)));
//        }
//    }
    // Добавляет данные в таблицу
    @FXML
    private void new_data() throws SQLException, ParseException {

        goodsData.clear();
        connection = DriverManager.getConnection("jdbc:sqlite:the_yy.db");
        Statement statement = connection.createStatement();
        try {
            allTheMoney.setText("Бюджет: "
                    + statement.executeQuery("SELECT all_the_money FROM MONEY").getInt(1)
                    + " Прибыль: "
                    + statement.executeQuery("SELECT all_the_lost FROM MONEY").getInt(1));
            ResultSet rs = statement.executeQuery("select * from GOODS");

            int n = 1;
            while (rs.next()) {
                goodsData.add(new Goods(n, rs.getString(2), rs.getInt(3), rs.getInt(4),
                        rs.getInt(5), rs.getInt(6)));
                n++;
            }
        }catch(NullPointerException e){
            //e.printStackTrace();
        }

        changesData.clear();
        //Connection connection = DriverManager.getConnection("jdbc:sqlite:the_yy.db");
        //Statement statement = connection.createStatement();
        ResultSet rs = statement.executeQuery("select * from CHANGES");
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd-MM-yyyy");
        while (rs.next()) {
            java.util.Date date1;
            changesData.add(new Changes(date1 = simpleDateFormat.parse(rs.getString(3)), rs.getString(4)));
        }
    }


    //окна
    public void settings() throws IOException {
        AnchorPane anchorPane = new AnchorPane();
        Stage stage = new Stage();
        stage.setMinHeight(160);
        stage.setMinWidth(240);
        stage.setMaxHeight(160);
        stage.setMaxWidth(240);
        Parent content = FXMLLoader.load(Objects.requireNonNull(getClass().getClassLoader().getResource(
                "settings.fxml")));
        Scene scene = new Scene(anchorPane);
        anchorPane.getChildren().add(content);
        stage.setTitle("Настройки");
        stage.setScene(scene);
        stage.show();
    }

    public void new_good() throws Exception {
        AnchorPane anchorPane = new AnchorPane();
        Stage stage = new Stage();
        stage.setMinHeight(400);
        stage.setMinWidth(600);
        Parent content = FXMLLoader.load(Objects.requireNonNull(getClass().getClassLoader().getResource(
                "new_good.fxml")));
        Scene scene = new Scene(anchorPane);
        anchorPane.getChildren().add(content);
        stage.setTitle("Добавление товара");
        stage.setScene(scene);
        stage.show();
    }

    public void new_change() throws Exception {
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
}
