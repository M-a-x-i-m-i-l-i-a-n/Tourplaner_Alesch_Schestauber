package com.example.javafx;

import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public class AboutDialog extends BorderPane {

    private TextArea textArea;
    private Button closeButton;

    public AboutDialog() {
        initialize();
    }

    private void initialize() {
        textArea = new TextArea();
        textArea.setText("Programmed by:\nSome Name!");
        textArea.setEditable(false);
        closeButton = new Button("Close");

        this.setCenter(textArea);
        this.setBottom(closeButton);

        closeButton.setOnMouseClicked(e -> {
            ((Stage)this.getScene().getWindow()).close();
        });
    }
}
