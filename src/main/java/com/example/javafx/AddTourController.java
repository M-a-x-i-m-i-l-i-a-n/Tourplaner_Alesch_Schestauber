package com.example.javafx;

import com.example.javafx.business.MyTourManager;
import com.example.javafx.business.TourManager;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class AddTourController implements Initializable {
    ObservableList<String> transportTypes = FXCollections.observableArrayList("Bike", "Hike", "Running", "Vacation");
    @FXML
    private TextField name;

    @FXML
    private TextField from;

    @FXML
    private TextField to;

    @FXML
    private ChoiceBox type;

    @FXML
    private TextArea description;

    @FXML
    private Button saveTour;

    private TourVM tourVM;

    public AddTourController() {
        TourManager manager = new MyTourManager();
        this.tourVM = new TourVM(manager);
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        type.setValue("Type");
        type.setItems(transportTypes);
    }

    @FXML
    protected void onSaveButtonClicked() {
        String tourName = name.getText();
        String tourFrom = from.getText();
        String tourTo   = to.getText();
        String tourType = type.getValue().toString();
        String tourDescription = description.getText();
        tourVM.add(tourName, tourDescription, tourFrom, tourTo, tourType);
    }
}
