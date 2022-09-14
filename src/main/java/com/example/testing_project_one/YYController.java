package com.example.testing_project_one;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Group;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.util.Callback;

import java.io.IOException;
import java.sql.*;
import java.util.Objects;

/**
 * @author Pavel
 */
public class YYController {
    Stage stage_change = new Stage();
    @FXML
    Button good;
    @FXML
    Button sale;
    @FXML
    Button up;
    @FXML
    Button down;
    @FXML
    Button del;
    @FXML
    Button new_good;
    @FXML
    TextField name;
    @FXML
    TextField amount;
    @FXML
    TextField cost_out;
    @FXML
    TextField cost_in;
    Connection connection;
    //for table with goods
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



    //Запуск бд
    public void startDataBase() {
        try {
            Class.forName("org.sqlite.JDBC");
            connection = DriverManager.getConnection("jdbc:sqlite:the_yy.db");
            System.out.println("Connection established...");
            createDataBase();
            Statement statement = connection.createStatement();
            String exists = "SELECT * FROM GOODS WHERE EXISTS(SELECT id_goods from GOODS WHERE id_goods = 1)";
            boolean result;
            ResultSet rs = statement.executeQuery(exists);
            result = rs.getBoolean(1);
            if (result) onStartup();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
    private ObservableList<Goods> data;

    @FXML
    public void onStartup() throws SQLException{
        data = FXCollections.observableArrayList();
        try{
            connection = DriverManager.getConnection("jdbc:sqlite:the_yy.db");
            String sql = "SELECT * from GOODS";
            ResultSet rs = connection.createStatement().executeQuery(sql);
//            for (int i = 0; i < rs.getMetaData().getColumnCount(); i++){
//                final int j = i;
//                TableColumn column = new TableColumn(rs.getMetaData().getColumnName(i + 1));
//                column.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<Goods, String>, ObservableValue<Goods>>() {
//                    public ObservableValue<Goods> call(TableColumn.CellDataFeatures<Goods, String> param) {
//                        return new SimpleStringProperty(param.getValue().getName());
//                    }
//                });
//                tableGoods.getColumns().addAll(column);
//            }
            while(rs.next()){
                ObservableList<Goods> row = FXCollections.observableArrayList();
                for (int i = 1; i <= rs.getMetaData().getColumnCount(); i++){
                    Statement statement = connection.createStatement();
                    ResultSet rs_1 = statement.executeQuery("SELECT * FROM GOODS");
                    row.add(new Goods(rs_1.getString(2), rs_1.getInt(3), rs_1.getInt(4),rs_1.getInt(5),rs_1.getInt(6)));
                }

                data.addAll(row);
            }
            tableGoods.setItems(data);

        }catch(Exception e){
            e.printStackTrace();
            System.out.println("Error on building data...");
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
                "comment text, " +
                "FOREIGN KEY (id_goods) REFERENCES goods (id_goods));";
        statement.executeUpdate(sql1);
        String sql2 = "CREATE TABLE IF NOT EXISTS MONEY" +
                "(all_the_money int, " +
                "all_the_lost int);";
        statement.executeUpdate(sql2);
        System.out.println("Created all the tables...");
    }
    public void insertGoods() throws SQLException {
        // Закрытие окна
        Stage stage_del = (Stage) new_good.getScene().getWindow();
        stage_del.close();

        connection = DriverManager.getConnection("jdbc:sqlite:the_yy.db");
        Statement statement;
        try {
            statement = connection.createStatement();
        } catch (Exception e) {
            throw new RuntimeException("unhandled", e);
        }
        Goods goods = null;
        try {
            int id = 0;
            id = id + 1;
            goods = new Goods(name.getText(), Integer.parseInt(amount.getText()), Integer.parseInt(cost_out.getText()),
                    Integer.parseInt(cost_in.getText()),
                    Integer.parseInt("-" + cost_in.getText()));
            System.out.println("Adding goods to the table");
            String addingGoods = "INSERT INTO GOODS (name, amount, cost_out, cost_in, profit)" +
                    "VALUES ('" + goods.getName() + "', '" + goods.getAmount() + "', '" + goods.getCost_out() +
                    "', '" + goods.getCost_in() + "', '" + goods.getProfit() + "');";
            statement.executeUpdate(addingGoods);
        } catch (NumberFormatException e) {
            Stage stage = new Stage();
            stage.setMinHeight(100);
            stage.setMinWidth(400);
            stage.setTitle("Ошибка");
            BorderPane borderPane = new BorderPane();
            Text text = new Text("Некорректные данные!!!");
            text.setStyle("--fxbackground-color: red");
            borderPane.setCenter(text);
            Scene scene = new Scene(borderPane);
            stage.setScene(scene);
            stage.show();
        }
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


    @FXML
    private void initialize() throws SQLException {
        initData();
        try {
            //idColumn.setCellValueFactory(cellData ->cellData.getValue().dateProperty());

            //idColumn.setCellValueFactory(new PropertyValueFactory<Goods, Integer>("id_goods"));
            nameColumn.setCellValueFactory(new PropertyValueFactory<Goods, String>("name"));
            amountColumn.setCellValueFactory(new PropertyValueFactory<Goods, Integer>("amount"));
            costOutColumn.setCellValueFactory(new PropertyValueFactory<Goods, Integer>("cost_out"));
            costInColumn.setCellValueFactory(new PropertyValueFactory<Goods, Integer>("cost_in"));
            profitColumn.setCellValueFactory(new PropertyValueFactory<Goods, Integer>("profit"));

            tableGoods.setItems(goodsData);
        }catch(NullPointerException e){

        }
    }

    private void initData() throws SQLException {
        Connection connection = DriverManager.getConnection("jdbc:sqlite:the_yy.db");

        String sql = "SELECT id_goods, name, amount, cost_out, cost_in, profit FROM GOODS WHERE id_goods = (SELECT MAX(id_goods)FROM GOODS)";

        Statement statement = connection.createStatement();
        ResultSet rs;
        rs = statement.executeQuery(sql);

        while (rs.next()) {
            int id_goods = rs.getInt(1);
            String name = rs.getString(2);
            int amount = rs.getInt(3);
            int cost_out = rs.getInt(4);
            int cost_in = rs.getInt(5);
            int profit = rs.getInt(6);
            goodsData.add(new Goods(name, amount, cost_out, cost_in, profit));
        }
    }


    //окна
    public void settings() {
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

    public void new_good() throws Exception {
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


}
