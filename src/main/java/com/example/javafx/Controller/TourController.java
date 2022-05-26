package com.example.javafx.Controller;


import com.example.javafx.AboutDialog;
import com.example.javafx.TourApplication;
import com.example.javafx.ViewModle.TourVM;
import com.example.javafx.business.MyTourManager;
import com.example.javafx.business.TourManager;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class TourController implements Initializable {
    @FXML
    private TextField textField;
    @FXML
    private ImageView searchButton;
    @FXML
    private Label title;

    @FXML
    private ImageView plusButton;

    @FXML
    private ImageView minusButton;

    @FXML
    private Button addButton;

    @FXML
    private Button removeButton;

    @FXML
    private ListView<String> tourList;
    @FXML
    private ImageView imageView;

    @FXML
    private MenuItem menuClose;

    @FXML
    private MenuItem menuPdf;

    @FXML
    private MenuItem menuFile;

    @FXML
    private MenuItem menuAbout;

    @FXML
    private BorderPane borderPane;
    //Hier müssen dann noch die Menü Items eingefügt werden

    private TourVM tourVM;

    public TourController() {
        TourManager manager = new MyTourManager();
        this.tourVM = new TourVM(manager);
    }

    public void initialize(URL url, ResourceBundle resourceBundle) {
        // textField.setText("Search");
        List<String> items = tourVM.getTourNames();
        System.out.println("TOURCONT::75:: List:" + items);
        for(int i = 0; i < items.size(); i++){
            tourList.getItems().add(items.get(i));
        }
    }

    @FXML
    public void onMenuClicked(ActionEvent e) throws IOException {

        if (e.getSource() == menuClose) {
            // ((Stage) borderPane.getScene().getWindow()).close();
        }

        if (e.getSource() == menuPdf) {
            System.out.println("Export to PDF File!");
        }

        if (e.getSource() == menuFile) {
            System.out.println("Export to File!");
        }

        if (e.getSource() == menuAbout) {
            System.out.println("Show About!");
            Stage stage = new Stage();
            stage.setTitle("About");
            stage.initOwner(borderPane.getScene().getWindow());
            stage.initModality(Modality.WINDOW_MODAL);

            stage.setScene(new Scene(new AboutDialog()));

            stage.show();
        }
    }


    @FXML
    protected void onSearchButtonClick() {
        String text = textField.getText();
        //title.setText("Title: " + text);
        textField.setText("");
    }
    @FXML
    protected void onAddButtonClick() throws IOException {
        System.out.println("Add Tour!");
        Stage stage = new Stage();
        stage.setTitle("Add Tour");
        stage.initOwner(borderPane.getScene().getWindow());
        stage.initModality(Modality.WINDOW_MODAL);

        FXMLLoader fxmlLoader = new FXMLLoader(TourApplication.class.getResource("AddTour.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 700, 500);
        stage.setScene(scene);
        stage.show();
        updateList();
    }
    @FXML
    protected void onDeleteButtonClick() throws IOException{
        String name = tourList.getSelectionModel().getSelectedItem();
        tourVM.delete(name);
        textField.setText("");
        updateList();
    }

    @FXML
    protected void updateList(){
        List<String> items = tourVM.getTourNames();
        tourList.getItems().clear();
        for(int i = 0; i < items.size(); i++){
            tourList.getItems().add(items.get(i));
        }

    }
}