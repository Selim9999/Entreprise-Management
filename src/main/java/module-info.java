module com.example.employees_management {
    requires javafx.controls;
    requires javafx.fxml;
    requires MaterialFX;
    requires java.sql;
    requires com.jfoenix;
    requires scenebuilder;

    exports Controllers;
    exports Models;

    opens Controllers to javafx.fxml;
    opens com.example.employees_management to javafx.fxml;

    exports com.example.employees_management;
    opens Models to javafx.fxml;

}