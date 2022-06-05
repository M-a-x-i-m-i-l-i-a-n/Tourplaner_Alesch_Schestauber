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
    ObservableList<String> transportTypes = FXCollections.observableArrayList("shortest", "fastest", "bicycle", "pedestrian");
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
        MyTourManager manager = MyTourManager.getInstance();
        this.tourVM = new TourVM(manager);
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        type.setItems(transportTypes);
        type.setValue(transportTypes.get(1));
        name.textProperty().bindBidirectional(tourVM.name);
        from.textProperty().bindBidirectional(tourVM.from);
        to.textProperty().bindBidirectional(tourVM.to);
        type.valueProperty().bindBidirectional(tourVM.type);
        description.textProperty().bindBidirectional(tourVM.description);

    }

    @FXML
    protected void onSaveButtonClicked() {
        tourVM.add();
        Stage stage = (Stage) saveTour.getScene().getWindow();
        stage.close();
    }
}
