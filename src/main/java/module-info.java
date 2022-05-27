module com.example.start_2 {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;
    requires java.desktop;

    opens com.example.start_2 to javafx.fxml;
    exports com.example.start_2;
}