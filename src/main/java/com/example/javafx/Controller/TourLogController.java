package com.example.javafx.Controller;

import com.example.javafx.ViewModle.TourLogVM;
import com.example.javafx.ViewModle.TourVM;
import com.example.javafx.business.MyTourLogManager;
import com.example.javafx.business.MyTourManager;
import com.example.javafx.business.TourLogManager;
import com.example.javafx.business.TourManager;
import com.example.javafx.model.Tour;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ResourceBundle;

import com.example.javafx.ViewModle.TourVM;

public class TourLogController implements Initializable {
    ObservableList<String> ratings = FXCollections.observableArrayList("1", "2", "3", "4", "5");
    ObservableList<String> difficulties = FXCollections.observableArrayList("1", "2", "3", "4", "5");
    TourLogVM tourLogVM;

    @FXML
    private ChoiceBox rating;

    @FXML
    private ChoiceBox difficulty;

    @FXML
    private TextField date;

    @FXML
    private TextField time;

    @FXML
    private TextField timeNeeded;

    @FXML
    private TextArea comment;

    @FXML
    private Button save;

    public TourLogController(){
        TourLogManager manager = TourLogManager.getInstance();
        this.tourLogVM = new TourLogVM(manager);
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        rating.setValue("");
        rating.setItems(ratings);

        difficulty.setValue("");
        difficulty.setItems(difficulties);
        date.textProperty().bindBidirectional(tourLogVM.date);
        time.textProperty().bindBidirectional(tourLogVM.time);
        timeNeeded.textProperty().bindBidirectional(tourLogVM.timeNeeded);
        difficulty.valueProperty().bindBidirectional(tourLogVM.difficulty);
        rating.valueProperty().bindBidirectional(tourLogVM.rating);
        comment.textProperty().bindBidirectional(tourLogVM.comment);

    }

    @FXML
    protected void onSaveButtonClicked() {
        Stage stage = (Stage) save.getScene().getWindow();
        String[] stageName = stage.getTitle().split(":");
        String TourName = stageName[1];
        System.out.println(TourName);
        tourLogVM.addTourLog(TourName);

        stage.close();
    }
}

