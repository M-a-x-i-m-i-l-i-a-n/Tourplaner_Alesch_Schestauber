module com.example.javafx {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;

    opens com.example.javafx to javafx.fxml;
    exports com.example.javafx;
    exports com.example.javafx.business;
    opens com.example.javafx.business to javafx.fxml;
    exports com.example.javafx.Controller;
    opens com.example.javafx.Controller to javafx.fxml;
    exports com.example.javafx.ViewModle;
    opens com.example.javafx.ViewModle to javafx.fxml;
}