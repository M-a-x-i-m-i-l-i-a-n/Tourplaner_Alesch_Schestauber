module com.example.javafx {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;
    requires java.net.http;
    requires com.fasterxml.jackson.databind;
    requires jdk.jsobject;
    requires org.json;
    requires layout;
    requires kernel;
    requires io;

    opens com.example.javafx to javafx.fxml;
    exports com.example.javafx;
    exports com.example.javafx.business;
    opens com.example.javafx.business to javafx.fxml;
    exports com.example.javafx.Controller;
    opens com.example.javafx.Controller to javafx.fxml;
    exports com.example.javafx.ViewModle;
    opens com.example.javafx.ViewModle to javafx.fxml;
}