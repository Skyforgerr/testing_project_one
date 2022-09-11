module com.example.testing_project_one {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;
    requires org.xerial.sqlitejdbc;


    opens com.example.testing_project_one to javafx.fxml;
    exports com.example.testing_project_one;
}