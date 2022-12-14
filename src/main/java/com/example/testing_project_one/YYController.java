package com.example.testing_project_one;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.chart.PieChart;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import javax.xml.transform.Result;
import java.io.IOException;
import java.sql.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Objects;

/**
 * @author Pavel
 */
public class YYController  extends ConnectionClass{
    @FXML Label allTheMoney;
    Stage stage_change = new Stage();
    //Connection connection = DriverManager.getConnection("jdbc:sqlite:the_yy.db");

    public YYController() throws SQLException {
    }

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
    //создание бд
    public void createDataBase() throws SQLException {
        Statement statement = connection.createStatement();
        String tableA = "CREATE TABLE IF NOT EXISTS GOODS" +
                "(id_goods INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "name text, " +
                "amount int, " +
                "cost_in int, " +
                "cost_out int, " +
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
    //метод завершения работы бд
    public void closeDataBase(){
        try{
            connection.close();
            System.out.println("Connection ended...");
        }catch (Exception e){
            e.printStackTrace();
        }

    }

    // все необходимые FXML поля
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
    @FXML private TableColumn<Goods, Integer> amountGoodsColumn;
    //изменение количества товара
    @FXML private TableView<Goods> amountView;
    @FXML private TableColumn<Goods, Integer> idAmountColumn;
    @FXML private TableColumn<Goods, String> nameAmountColumn;
    @FXML private TableColumn<Goods, Integer> amountAmountColumn;
    //удаление товара из бд
    @FXML private TableView<Goods> deleteView;
    @FXML private TableColumn<Goods, Integer> idDeleteColumn;
    @FXML private TableColumn<Goods, String> nameDeleteColumn;
    //таблица с изменениями
    @FXML private TableView<Changes> changeView;
    @FXML private TableColumn<Changes, Date> dateColumn;
    @FXML private TableColumn<Changes, String> changeColumn;

    //текстовые поля для окна изменения количества
    @FXML private TextField idAmount;
    @FXML private TextField amountAmount;
    //текстовые поля для окна удаления товара
    @FXML private TextField deleteField;
    //текстовые поля для продажи товара
    @FXML private TextField saleIdGoods;
    @FXML private TextField saleAmountGoods;
    //методы добавления значений в поля tableview
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
        }catch(NullPointerException e){}
        new_dataSmall();
        try {
            idGoodsColumn.setCellValueFactory(new PropertyValueFactory<Goods, Integer>("id_goods"));
            nameGoodsColumn.setCellValueFactory(new PropertyValueFactory<Goods, String>("name"));
            amountGoodsColumn.setCellValueFactory(new PropertyValueFactory<Goods, Integer>("amount"));
            tableView.setItems(saleData);
        }catch(NullPointerException e){}
        new_dataAmount();
        try {
            idAmountColumn.setCellValueFactory(new PropertyValueFactory<Goods, Integer>("id_goods"));
            nameAmountColumn.setCellValueFactory(new PropertyValueFactory<Goods, String>("name"));
            amountAmountColumn.setCellValueFactory(new PropertyValueFactory<Goods, Integer>("amount"));
            amountView.setItems(amountData);
        }catch(NullPointerException e){}
        new_dataDelete();
        try {
            idDeleteColumn.setCellValueFactory(new PropertyValueFactory<Goods, Integer>("id_goods"));
            nameDeleteColumn.setCellValueFactory(new PropertyValueFactory<Goods, String>("name"));
            deleteView.setItems(deleteData);
        }catch(NullPointerException e){}
        new_data();
        try {
            dateColumn.setCellValueFactory(new PropertyValueFactory<Changes, Date>("day"));
            changeColumn.setCellValueFactory(new PropertyValueFactory<Changes, String>("comment"));
            changeView.setItems(changesData);
        }catch(NullPointerException e){}
    }
    @FXML
    private void new_dataSmall() throws SQLException{
        saleData.clear();
        Statement statement = connection.createStatement();
        ResultSet rs = statement.executeQuery("select * from GOODS");
        while (rs.next()) {
            saleData.add(new Goods(rs.getInt(1), rs.getString(2), rs.getInt(3)));
        }
    }
    @FXML
    private void new_dataAmount() throws SQLException{
        amountData.clear();
        Statement statement = connection.createStatement();
        ResultSet rs = statement.executeQuery("select * from GOODS");
        while (rs.next()) {
            amountData.add(new Goods(rs.getInt(1), rs.getString(2), rs.getInt(3)));
        }
    }

    @FXML Button btnChangeAmount;
    @FXML
    private void changeAmount() throws SQLException {
        // Закрытие окна
        Stage stage_del = (Stage) btnChangeAmount.getScene().getWindow();
        stage_del.close();

        amountData.clear();
        int neededId = Integer.parseInt(idAmount.getText());
        PreparedStatement statement = connection.prepareStatement("SELECT amount FROM GOODS WHERE id_goods = ?");
        statement.setInt(1, neededId);
        ResultSet rs = statement.executeQuery();
        if ((Integer.parseInt(amountAmount.getText()) + rs.getInt(1)) > 0){
            int newAmount = rs.getInt(1) + Integer.parseInt(amountAmount.getText());

            PreparedStatement preparedStatement = connection.prepareStatement("UPDATE GOODS SET amount = ? WHERE id_goods = ?");
            preparedStatement.setInt(1, newAmount);
            preparedStatement.setInt(2, neededId);
            preparedStatement.executeUpdate();

            PreparedStatement preparedStatement1 = connection.prepareStatement("SELECT name FROM GOODS WHERE id_goods = ?");
            preparedStatement1.setInt(1, neededId);
            Statement statement1 = connection.createStatement();

            ResultSet rs_1 = statement1.executeQuery("select * from GOODS");
            while (rs.next()) {
                amountData.add(new Goods(rs_1.getInt(1), rs_1.getString(2), rs_1.getInt(3)));
            }

            Date date = new Date(System.currentTimeMillis());
            System.out.println(date);

            String new_change = "INSERT INTO changes (id_goods, day, comment) VALUES ('" +
                    neededId + "', '" + date + "', '" +
                    "Количество товара " + preparedStatement1.executeQuery().getString(1) + " было изменено на значение " + newAmount
                    + "')";
            statement1.executeUpdate(new_change);
        }else if ((Integer.parseInt(amountAmount.getText()) + rs.getInt(1)) == 0){
            //amountData.clear();
            String theSql = "DELETE FROM GOODS WHERE id_goods = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(theSql);
            preparedStatement.setInt(1, neededId);
            preparedStatement.executeUpdate();
            ResultSet rs_1 = statement.executeQuery("select * from GOODS");
            while (rs_1.next()) {
                amountData.add(new Goods(rs_1.getInt(1), rs_1.getString(2)));
            }
            Date date = new Date(System.currentTimeMillis());
            System.out.println(date);
            String new_change = "INSERT INTO changes (id_goods, day, comment) VALUES ('"+
                    neededId + "', '" + date + "', '" +
                    "Товар под номером " +  neededId + " был удален, так как его количество стало равно 0"
                    +  "')";
            statement.executeUpdate(new_change);
        }else {
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
        connection.close();
    }

    @FXML Button btnSale;
    @FXML
    private void saleTheGood() throws SQLException{
        // Закрытие окна
        Stage stage_del = (Stage) btnSale.getScene().getWindow();
        stage_del.close();

        saleData.clear();
        //Connection connection = DriverManager.getConnection("jdbc:sqlite:the_yy.db");
        Statement statement = connection.createStatement();
        int neededId = Integer.parseInt(saleIdGoods.getText());
        ResultSet rs = statement.executeQuery("SELECT amount FROM GOODS where id_goods = " + neededId);
        int newAmount = rs.getInt(1) - Integer.parseInt(saleAmountGoods.getText());
        int oldAmount = rs.getInt(1);

        if (newAmount >= 0) {
            PreparedStatement preparedStatement = connection.prepareStatement("UPDATE GOODS SET amount = ? WHERE id_goods = ?");
            preparedStatement.setInt(1, newAmount);
            preparedStatement.setInt(2, neededId);
            preparedStatement.executeUpdate();
            PreparedStatement preparedStatement1 = connection.prepareStatement("SELECT name FROM GOODS WHERE id_goods = ?");
            preparedStatement1.setInt(1, neededId);
            ResultSet rs_1 = statement.executeQuery("select * from GOODS");
            PreparedStatement preparedStatement3 = connection.prepareStatement("SELECT cost_out FROM GOODS WHERE id_goods = ?");
            preparedStatement3.setInt(1, neededId);
            ResultSet costSet = preparedStatement3.executeQuery();
            int theCost = costSet.getInt(1);
            String money_change = "UPDATE MONEY SET all_the_money = ?, all_the_lost = ?";
            ResultSet rs3 = statement.executeQuery("SELECT * FROM MONEY");

            int amountBought = Integer.parseInt(saleAmountGoods.getText());
            int currentMoney = rs3.getInt(1);
            int currentLost = rs3.getInt(2);
            PreparedStatement preparedStatement2 = connection.prepareStatement(money_change);
            preparedStatement2.setInt(1, (currentMoney + amountBought * theCost));
            preparedStatement2.setInt(2, (currentLost + amountBought * theCost));
            preparedStatement2.executeUpdate();
            int oldGoodProfit = statement.executeQuery("select profit from goods where id_goods = " + neededId).getInt(1);
            statement.executeUpdate("update goods set profit = " + (oldGoodProfit + amountBought * theCost)
                    + " where id_goods = " + neededId);
            String nameGood = preparedStatement1.executeQuery().getString(1);
            Date date = new Date(System.currentTimeMillis());
            String new_change = "INSERT INTO changes (id_goods, day, comment) VALUES ('" +
                    neededId + "', '" + date + "', '" +
                    "Товар «" + nameGood + "» продан в количестве " + (oldAmount - newAmount) +
                    ". Бюджет увеличен на " + (amountBought * theCost)
                    + "')";
            statement.executeUpdate(new_change);
            if (newAmount == 0){
                String theSql = "DELETE FROM GOODS WHERE id_goods = ?";
                PreparedStatement preparedStatement_del = connection.prepareStatement(theSql);
                preparedStatement_del.setInt(1, neededId);
                preparedStatement_del.executeUpdate();

                new_change = "INSERT INTO changes (id_goods, day, comment) VALUES ('"+
                        neededId + "', '" + date + "', '" +
                        "Товар «" +  nameGood + "» был удален, так как он бы распродан')";
                statement.executeUpdate(new_change);
            }
        }
        else{
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
        ResultSet goods = statement.executeQuery("select * from GOODS");
        while (goods.next()) {
            saleData.add(new Goods(goods.getInt(1), goods.getString(2), goods.getInt(3)));
        }
        connection.close();
    }

    @FXML
    private void new_dataDelete() throws SQLException{
        deleteData.clear();
        Statement statement = connection.createStatement();
        ResultSet rs = statement.executeQuery("select * from GOODS");
        while (rs.next()) {
            deleteData.add(new Goods(rs.getInt(1), rs.getString(2)));
        }
    }


    @FXML Button delTheGood;
    @FXML
    private void deleteTheField() throws SQLException{
        // Закрытие окна
        Stage stage_del = (Stage) delTheGood.getScene().getWindow();
        stage_del.close();

        Statement statement = connection.createStatement();

        String SqlDelGood = "DELETE FROM GOODS WHERE id_goods = ?";
        PreparedStatement preparedStatement = connection.prepareStatement(SqlDelGood);
        int idGood = Integer.parseInt(deleteField.getText());
        String nameGood = statement.executeQuery("select name from goods where id_goods = " +
                idGood).getString(1);
        // Если товар с таким id существует
        if (nameGood != null) {
            amountData.clear();
            preparedStatement.setInt(1, Integer.parseInt(deleteField.getText()));
            preparedStatement.executeUpdate();
            ResultSet rs_1 = statement.executeQuery("select * from GOODS");
            while (rs_1.next()) {
                amountData.add(new Goods(rs_1.getInt(1), rs_1.getString(2)));
            }
            // Добавляется новое изменение
            Date date = new Date(System.currentTimeMillis());
            String new_change = "INSERT INTO changes (id_goods, day, comment) VALUES ('" +
                    Integer.parseInt(deleteField.getText()) + "', '" + date + "', '" +
                    "Товар «" + nameGood + "» был удален"
                    + "')";
            statement.executeUpdate(new_change);
        }
        // Вывод ошибки если нет товара с таким id
        else {
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

    // Добавляет данные в таблицу
    @FXML
    private void new_data() throws SQLException, ParseException {
        goodsData.clear();
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
        }catch(NullPointerException e){}
        changesData.clear();
        ResultSet rs = statement.executeQuery("select * from CHANGES");
        while (rs.next()) {
            changesData.add(new Changes(rs.getString(3), rs.getString(4)));
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
