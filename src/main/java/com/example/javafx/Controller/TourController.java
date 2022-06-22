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
import javafx.stage.FileChooser;
import javafx.stage.Modality;
import javafx.stage.Stage;


import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
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
    private MenuItem exportFile;

    @FXML
    private MenuItem menuPdf;

    @FXML
    private MenuItem menuFile;

    @FXML
    private MenuItem importFile;

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

    @FXML
    private Label name;
    @FXML
    private Label from;
    @FXML
    private Label to;
    @FXML
    private Label transportType;
    @FXML
    private Label distance;
    @FXML
    private Label estimatedTime;
    @FXML
    private Label description;
    @FXML
    private Label popularity;
    @FXML
    private Label childFriendliness;

    @FXML
    private ImageView routeImage;

    @FXML
    private MenuBar menuBar;

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

        date.setCellValueFactory(new PropertyValueFactory<TourLog, String>("Date"));
        time.setCellValueFactory(new PropertyValueFactory<>("Time"));
        difficulty.setCellValueFactory(new PropertyValueFactory<>("Difficulty"));
        totalTime.setCellValueFactory(new PropertyValueFactory<>("TotalTime"));
        rating.setCellValueFactory(new PropertyValueFactory<>("Rating"));
        comment.setCellValueFactory(new PropertyValueFactory<>("Comment"));


        logsTable.setItems(logVM.getTourLogs());
    }


    @FXML
    public void onMenuClicked(ActionEvent e) throws IOException {



        if (e.getSource() == menuPdf) {
            System.out.println("Export to PDF File!");
            Tour tour = tourList.getSelectionModel().getSelectedItem();
            ObservableList<TourLog> logs = logVM.getLogsByTourname(tour.getName());

        }

        if (e.getSource() == exportFile) {
            System.out.println("Export to File!");
            tourVM.exportTour(tourList.getSelectionModel().getSelectedItem());
        }

        if (e.getSource() == importFile) {
            Stage stage = new Stage();
            FileChooser fileChooser = new FileChooser();
            fileChooser.setTitle("Open File");
            File file =  fileChooser.showSaveDialog(stage);

        }
    }


    @FXML
    protected void onSearchButtonClick() throws IOException {
        String text = textField.getText();
        //title.setText("Title: " + text);
        textField.setText("");
        tourVM.getTourbyName(text);
        ObservableList<TourLog> logs = logVM.getLogsFromList(tourList.getItems());
        logsTable.setItems(logs);

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
    protected void onDeleteTourLog(){
        TourLog log = logsTable.getSelectionModel().getSelectedItem();
        logVM.deleteLog(log);
    }
    //Todo es muss noch der Edit-button für die Tourlogs gemacht werden
    @FXML
    protected void onTourSelected(){
        Tour tour = tourList.getSelectionModel().getSelectedItem();
        ObservableList<TourLog> logs = logVM.getLogsByTourname(tour.getName());
        logsTable.setItems(logs);
        System.out.println("AAAAAAAAAA::: " + tour.getUrl());
        Image image = new Image(tour.getUrl());
        System.out.println(image.getUrl());
        routeImage.setImage(image);
        //TODO das Bild vielleicht asynchron laden und während dessen einen Ladescreen/Ladesymbol reingeben
        name.setText("Name of the Tour: " + tour.getName());
        from.setText(tour.getStart());
        to.setText(tour.getDestin());
        transportType.setText(tour.getType());
        distance.setText(tour.getDistance().toString());
        estimatedTime.setText(tour.getTime());
        description.setText(tour.getDescription());

        popularity.setText(Integer.toString(tour.getPopularity()));
        childFriendliness.setText(Integer.toString(tour.getChildFriendliness()));
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

    @FXML
    protected void onEditTourLogButtonClick() throws IOException {
        int id = logsTable.getSelectionModel().getSelectedItem().getId();
        Stage stage = new Stage();
        stage.setTitle("Edit Tour-Log");
        stage.initOwner(borderPane.getScene().getWindow());
        stage.initModality(Modality.WINDOW_MODAL);

        FXMLLoader fxmlLoader = new FXMLLoader(TourApplication.class.getResource("EditTourLog.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 700, 500);
        EditTourLogController controller = fxmlLoader.getController();
        controller.setID(id);
        stage.setScene(scene);
        stage.show();
    }
}