package com.example.javafx.Controller;


import com.example.javafx.AboutDialog;
import com.example.javafx.TourApplication;
import com.example.javafx.ViewModle.TourLogVM;
import com.example.javafx.ViewModle.TourVM;
import com.example.javafx.business.MyTourLogManager;
import com.example.javafx.business.MyTourManager;
import com.example.javafx.business.TourManager;
import com.example.javafx.model.Tour;
import com.example.javafx.model.TourLog;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.Objects;
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
    private Button editTour;

    @FXML
    private Button addTourLog;

    @FXML
    private Button removeTourLog;

    @FXML
    private Button editTourLog;

    @FXML
    private ListView<Tour> tourList;
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

    @FXML
    private ImageView image;

    @FXML
    private TableView<TourLog> logsTable;

    @FXML
    private TableColumn<TourLog, String> date;

    @FXML
    private TableColumn<TourLog, String> time;

    @FXML
    private TableColumn<TourLog, String> difficulty;

    @FXML
    private TableColumn<TourLog, String> totalTime;

    @FXML
    private TableColumn<TourLog, String> rating;

    @FXML
    private TableColumn<TourLog, String> comment;

    //Hier müssen dann noch die Menü Items eingefügt werden

    private TourVM tourVM;
    private TourLogVM logVM;
    public TourController() {
        MyTourManager manager = MyTourManager.getInstance();
        MyTourLogManager logManager = MyTourLogManager.getInstance();
        this.tourVM = new TourVM(manager);
        this.logVM = new TourLogVM(logManager);
    }

    public void initialize(URL url, ResourceBundle resourceBundle) {
        tourList.setItems(tourVM.getTours());
        System.out.println(tourVM.getTours());

        /*List<String> items = tourVM.getTourNames();
        System.out.println("TOURCONT::75:: List:" + items);
        for(int i = 0; i < items.size(); i++){
            tourList.getItems().add(items.get(i));
        }

         */
        System.out.println(logVM.getTourLogs());
       // createTable();
    }


    //TODO das funktioniert noch nicht
    private void createTable(){
        ObservableList<TourLog> logs1 = FXCollections.observableArrayList();
                logs1 = logVM.getTourLogs();
        date.setCellValueFactory(new PropertyValueFactory<TourLog, String>("Date"));
        time.setCellValueFactory(new PropertyValueFactory<>("Time"));
        difficulty.setCellValueFactory(new PropertyValueFactory<>("Difficulty"));
        totalTime.setCellValueFactory(new PropertyValueFactory<>("TotalTime"));
        rating.setCellValueFactory(new PropertyValueFactory<>("Rating"));
        comment.setCellValueFactory(new PropertyValueFactory<>("Comment"));



        date.setCellValueFactory(new PropertyValueFactory<>("Date"));
        logsTable.setItems(logs1);
        logsTable.getColumns().setAll(date);
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
    protected void onSearchButtonClick() throws IOException {
        String text = textField.getText();
        //title.setText("Title: " + text);
        textField.setText("");

        System.out.println("Show About!");
        System.out.println("Add Tour LOG!");
        Stage stage = new Stage();
        stage.setTitle("Test");
        stage.initOwner(borderPane.getScene().getWindow());
        stage.initModality(Modality.WINDOW_MODAL);

        FXMLLoader fxmlLoader = new FXMLLoader(TourApplication.class.getResource("test.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 700, 500);
        stage.setScene(scene);
        stage.show();
    }
    @FXML
    protected void onAddButtonClick() throws IOException {
        System.out.println("Add Tour!");
        Stage stage = new Stage();
        stage.setTitle("Add Tour");
        stage.initOwner(borderPane.getScene().getWindow());
        stage.initModality(Modality.WINDOW_MODAL);

        FXMLLoader fxmlLoader = new FXMLLoader(TourApplication.class.getResource("AddTour.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 550, 500);
        stage.setScene(scene);
        stage.show();
    }
    @FXML
    protected void onDeleteButtonClick() throws IOException{
        String name = tourList.getSelectionModel().getSelectedItem().getName();
        tourVM.delete(name);
        textField.setText("");
    }

    @FXML
    protected void onEditTourButtonClick() throws IOException{
        String name = tourList.getSelectionModel().getSelectedItem().getName();
        System.out.println("Edit Tour!");
        Stage stage = new Stage();
        stage.setTitle("Edit Tour:" + name);
        stage.initOwner(borderPane.getScene().getWindow());
        stage.initModality(Modality.WINDOW_MODAL);

        FXMLLoader fxmlLoader = new FXMLLoader(TourApplication.class.getResource("EditTour.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 550, 500);
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    protected void onAddTourLogButtonClick() throws IOException {
        String name = tourList.getSelectionModel().getSelectedItem().getName();
        System.out.println("Add Tour LOG!");
        Stage stage = new Stage();
        stage.setTitle("Add Tour-Log to:" + name);
        stage.initOwner(borderPane.getScene().getWindow());
        stage.initModality(Modality.WINDOW_MODAL);

        FXMLLoader fxmlLoader = new FXMLLoader(TourApplication.class.getResource("AddTourLog.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 700, 500);
        stage.setScene(scene);
        stage.show();

    }

}