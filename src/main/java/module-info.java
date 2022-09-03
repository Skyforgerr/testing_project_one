module com.example.testing_project_one {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.testing_project_one to javafx.fxml;
    exports com.example.testing_project_one;
}