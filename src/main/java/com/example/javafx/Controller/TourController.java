package com.example.javafx.Controller;


import com.example.javafx.TourApplication;
import com.example.javafx.ViewModle.TourLogVM;
import com.example.javafx.ViewModle.TourVM;
import com.example.javafx.Business.MyTourLogManager;
import com.example.javafx.Business.MyTourManager;
import com.example.javafx.Model.Tour;
import com.example.javafx.Model.TourLog;
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
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;


import java.io.File;
import java.io.IOException;
import java.net.URL;
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
    public ListView<Tour> tourList;
    @FXML
    private ImageView imageView;

    @FXML
    private MenuItem exportFile;

    @FXML
    private MenuItem menuPdf;

    @FXML
    private MenuItem statsPdf;

    @FXML
    private MenuItem menuFile;

    @FXML
    private MenuItem TourCompare;

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

    private static Logger logger = LogManager.getLogger();

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
            if(tour != null) {
                ObservableList<TourLog> logs = logVM.getLogsByTourname(tour.getName());

                System.out.println("Calling createTourReport");
                tourVM.createTourReport(tour, logs);
            }else{
                logger.info("You have to select a tour to create a report.");
            }

        }

        if(e.getSource() == statsPdf){
            System.out.println("Create stat PDF!");
            ObservableList<Tour> tours = tourList.getItems();
            if(tours != null) {
                System.out.println("Calling createStatReport");
                tourVM.createStatReport(tours);
            }else{
                logger.info("You have currently no tours saved, so you can not create a report.");
            }
        }

        if (e.getSource() == exportFile) {
            System.out.println("Export to File!");
            Tour tour = tourList.getSelectionModel().getSelectedItem();
            if(tour != null) {
                tourVM.exportTour(tour);
            }else{
                logger.info("You have to select a tour to export it.");
            }
        }

        if (e.getSource() == importFile) {
            Stage stage = new Stage();
            FileChooser fileChooser = new FileChooser();
            fileChooser.setTitle("Open File");
            File file =  fileChooser.showOpenDialog(stage);
            String fileName = file.toString();

            int index = fileName.lastIndexOf('.');
            if(index > 0) {
                String extension = fileName.substring(index + 1);
                if(extension.equals("csv")){
                    tourVM.importTour(file);
                }
            }
        }

        if (e.getSource() == TourCompare) {

            ObservableList<Tour> tours = tourList.getItems();
            if(tours.size() > 0) {
                System.out.println("Compare Tour");
                Stage stage = new Stage();
                stage.setTitle("Tour-Compare");
                stage.initOwner(borderPane.getScene().getWindow());
                stage.initModality(Modality.WINDOW_MODAL);

                FXMLLoader fxmlLoader = new FXMLLoader(TourApplication.class.getResource("TourCompare.fxml"));
                Scene scene = new Scene(fxmlLoader.load(), 1200, 800);
                stage.setScene(scene);
                stage.show();
            }
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
        Tour tour = tourList.getSelectionModel().getSelectedItem();
        if(tour != null) {
            String name = tour.getName();
            System.out.println("Edit Tour!");
            Stage stage = new Stage();
            stage.setTitle("Edit Tour:" + name);
            stage.initOwner(borderPane.getScene().getWindow());
            stage.initModality(Modality.WINDOW_MODAL);

            FXMLLoader fxmlLoader = new FXMLLoader(TourApplication.class.getResource("EditTour.fxml"));
            Scene scene = new Scene(fxmlLoader.load(), 550, 500);
            stage.setScene(scene);
            stage.show();
        }else{
            logger.info("You have to select a tour to edit tour data.");
        }
    }

    @FXML
    protected void onDeleteTourLog(){
        TourLog log = logsTable.getSelectionModel().getSelectedItem();
        logVM.deleteLog(log);
    }

    @FXML
    protected void onTourSelected(){
        Tour tour = tourList.getSelectionModel().getSelectedItem();
        if(tour != null) {
            ObservableList<TourLog> logs = logVM.getLogsByTourname(tour.getName());
            logsTable.setItems(logs);

            //Image image = new Image(tour.getUrl());

            Image image2 = new Image("file:./Files/images/" + tour.getName() + ".jpg");
            routeImage.setImage(image2);

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
    }


    @FXML
    protected void onAddTourLogButtonClick() throws IOException {
        Tour tour = tourList.getSelectionModel().getSelectedItem();
        if(tour != null) {
            String name = tour.getName();
            System.out.println("Add Tour LOG!");
            Stage stage = new Stage();
            stage.setTitle("Add Tour-Log to:" + name);
            stage.initOwner(borderPane.getScene().getWindow());
            stage.initModality(Modality.WINDOW_MODAL);

            FXMLLoader fxmlLoader = new FXMLLoader(TourApplication.class.getResource("AddTourLog.fxml"));
            Scene scene = new Scene(fxmlLoader.load(), 700, 500);
            stage.setScene(scene);
            stage.show();
        }else{
            logger.info("You have to select a tour to create a tourlog.");
        }
    }

    @FXML
    protected void onEditTourLogButtonClick() throws IOException {
        TourLog log = logsTable.getSelectionModel().getSelectedItem();
        if(log != null) {
            int id = log.getId();
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
        }else{
            logger.info("You have to select a tourlog to edit it.");
        }
    }
}