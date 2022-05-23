package com.example.javafx;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;

import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ResourceBundle;

public class DialogView implements Initializable {

    @FXML
    private BorderPane borderPane;

    @FXML
    private Button closeButton;

    @Override
    @FXML
    public void initialize(URL url, ResourceBundle resourceBundle) {
        System.out.println("Initializing DialogView!");
        closeButton.setOnAction(e -> {
            System.out.println("Closing About dialog!");
            ((Stage)borderPane.getScene().getWindow()).close();;
        });

    }
}
