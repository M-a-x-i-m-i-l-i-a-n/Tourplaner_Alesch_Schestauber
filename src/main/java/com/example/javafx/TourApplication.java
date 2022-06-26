package com.example.javafx;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;

public class TourApplication extends Application {

    @Override
    public void start(Stage stage) throws IOException {
        Logger logger = LogManager.getLogger(TourApplication.class);
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("test.fxml"));
        Parent parent = fxmlLoader.load();
        Scene scene = new Scene(parent);
        stage.setTitle("Tour App");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}