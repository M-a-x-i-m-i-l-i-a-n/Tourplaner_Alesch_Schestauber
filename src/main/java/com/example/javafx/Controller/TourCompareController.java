package com.example.javafx.Controller;

import com.example.javafx.ViewModle.TourVM;
import com.example.javafx.business.MyTourManager;
import com.example.javafx.model.Tour;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.paint.Color;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.net.URL;
import java.sql.Time;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.ResourceBundle;




public class TourCompareController implements Initializable {

    @FXML
    private ChoiceBox tour1CB;

    @FXML
    private ChoiceBox tour2CB;

    @FXML
    private Label name1;
    @FXML
    private Label from1;
    @FXML
    private Label to1;
    @FXML
    private Label transportType1;
    @FXML
    private Label distance1;
    @FXML
    private Label estimatedTime1;
    @FXML
    private Label description1;
    @FXML
    private Label popularity1;
    @FXML
    private Label childFriendliness1;
    @FXML
    private ImageView routeImage1;

    @FXML
    private Label name2;
    @FXML
    private Label from2;
    @FXML
    private Label to2;
    @FXML
    private Label transportType2;
    @FXML
    private Label distance2;
    @FXML
    private Label estimatedTime2;
    @FXML
    private Label description2;
    @FXML
    private Label popularity2;
    @FXML
    private Label childFriendliness2;
    @FXML
    private ImageView routeImage2;
    private static Logger logger = LogManager.getLogger();

    private TourVM tourVM;

    private String check1, check2;
    Tour tour1;
    Tour tour2;
    public TourCompareController() {
        MyTourManager manager = MyTourManager.getInstance();
        this.tourVM = new TourVM(manager);
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        /*
        type.setItems(transportTypes);
        type.setValue(transportTypes.get(1));
        name.textProperty().bindBidirectional(tourVM.name);
        from.textProperty().bindBidirectional(tourVM.from);
        to.textProperty().bindBidirectional(tourVM.to);
        type.valueProperty().bindBidirectional(tourVM.type);
        description.textProperty().bindBidirectional(tourVM.description);
         */
        ObservableList<String> tournames = tourVM.getTourNames();
        tour1CB.setItems(tournames);
        tour2CB.setItems(tournames);
        if(tournames.size() > 1) {
            tour1CB.setValue(tournames.get(0));
            tour2CB.setValue(tournames.get(1));
        }else{
            if(tournames.size() > 0){
                tour1CB.setValue(tournames.get(0));
                tour2CB.setValue(tournames.get(0));
            }
        }
        check1 = "";
        check2 = "";
        onTour1Changed();
        onTour2Changed();
    }

    public void onTour1Changed(){
        String name = tour1CB.getSelectionModel().getSelectedItem().toString();
        if(!name.equals(check1)) {
            System.out.println("TOURCOMPARE:" + name);
            tour1 = tourVM.getOneTourByName(name);

            from1.setText(tour1.getStart());
            to1.setText(tour1.getDestin());
            transportType1.setText(tour1.getType());
            distance1.setText(tour1.getDistance().toString());
            estimatedTime1.setText(tour1.getTime());
            description1.setText(tour1.getDescription());
            popularity1.setText(Integer.toString(tour1.getPopularity()));
            childFriendliness1.setText(Integer.toString(tour1.getChildFriendliness()));
            //Hier dann noch das Bild reinladen

            Image image1 = new Image("file:./Files/images/" + tour1.getName() + ".jpg");
            routeImage1.setImage(image1);

            compareTours();
        }
    }

    public void onTour2Changed(){
        String name = tour2CB.getSelectionModel().getSelectedItem().toString();
        if(!name.equals(check2)) {
            System.out.println("TOURCOMPARE:" + name);
            tour2 = tourVM.getOneTourByName(name);

            from2.setText(tour2.getStart());
            to2.setText(tour2.getDestin());
            transportType2.setText(tour2.getType());
            distance2.setText(tour2.getDistance().toString());
            estimatedTime2.setText(tour2.getTime());
            description2.setText(tour2.getDescription());
            popularity2.setText(Integer.toString(tour2.getPopularity()));
            childFriendliness2.setText(Integer.toString(tour2.getChildFriendliness()));
            //Hier dann noch das Bild reinladen
            Image image2 = new Image("file:./Files/images/" + tour2.getName() + ".jpg");
            routeImage2.setImage(image2);
            compareTours();
        }
    }

    public void compareTours() {
        if (tour1 != null && tour2 != null) {
            if (tour1.getDistance() > tour2.getDistance()) {
                distance1.setTextFill(Color.web("#e30202"));
                distance2.setTextFill(Color.web("#02de18"));
            }else{
            if (tour1.getDistance() < tour2.getDistance()) {
                distance1.setTextFill(Color.web("#02de18"));
                distance2.setTextFill(Color.web("#e30202"));
            }else {
                distance1.setTextFill(Color.web("#0075a3"));
                distance2.setTextFill(Color.web("#0075a3"));
            }
            }
            if(tour1.getChildFriendliness() > tour2.getChildFriendliness()){
                childFriendliness1.setTextFill(Color.web("#02de18"));
                childFriendliness2.setTextFill(Color.web("#e30202"));
            }else{
            if(tour1.getChildFriendliness() < tour2.getChildFriendliness()){
                childFriendliness1.setTextFill(Color.web("#e30202"));
                childFriendliness2.setTextFill(Color.web("#02de18"));
            }else {
                childFriendliness1.setTextFill(Color.web("#0075a3"));
                childFriendliness2.setTextFill(Color.web("#0075a3"));
            }
            }
            if(tour1.getPopularity() > tour2.getPopularity()){
                popularity1.setTextFill(Color.web("#02de18"));
                popularity2.setTextFill(Color.web("#e30202"));
            }else {
            if(tour1.getPopularity() < tour2.getPopularity()){
                popularity1.setTextFill(Color.web("#e30202"));
                popularity2.setTextFill(Color.web("#02de18"));
            }else {
                popularity1.setTextFill(Color.web("#0075a3"));
                popularity2.setTextFill(Color.web("#0075a3"));
            }
            }

            try {
                SimpleDateFormat format = new SimpleDateFormat("hh:mm:ss");
                Date time1 = format.parse(tour1.getTime());
                Date time2 = format.parse(tour2.getTime());
                System.out.println("Time1::: " + time1.getTime());
                System.out.println("Time2::: " + time2.getTime());
                if(time1.getTime() > time2.getTime()){
                    estimatedTime1.setTextFill(Color.web("#e30202"));
                    estimatedTime2.setTextFill(Color.web("#02de18"));
                }else{
                if(time1.getTime() < time2.getTime()){
                    estimatedTime1.setTextFill(Color.web("#02de18"));
                    estimatedTime2.setTextFill(Color.web("#e30202"));
                }else {
                    estimatedTime1.setTextFill(Color.web("#0075a3"));
                    estimatedTime2.setTextFill(Color.web("#0075a3"));
                }
                }
            }catch (Exception e){
                logger.debug(e);
            }
        }
    }
}
