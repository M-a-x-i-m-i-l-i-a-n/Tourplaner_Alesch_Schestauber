package com.example.javafx.Controller;

import com.example.javafx.ViewModle.TourVM;
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
import java.util.ResourceBundle;
import javafx.beans.property.*;
import javafx.stage.Stage;

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

    private StringProperty tourName = new SimpleStringProperty();
    private StringProperty tourFrom = new SimpleStringProperty();
    private StringProperty tourTo = new SimpleStringProperty();
    private StringProperty tourType = new SimpleStringProperty();
    private StringProperty tourDescription = new SimpleStringProperty();

    public AddTourController() {
        TourManager manager = new MyTourManager();
        this.tourVM = new TourVM(manager);
        //TODO das Binding muss hier noch gemacht werden
        //name.textProperty().bindBidirectional(tourVM.);
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
        Stage stage = (Stage) saveTour.getScene().getWindow();
        stage.close();
    }
}
