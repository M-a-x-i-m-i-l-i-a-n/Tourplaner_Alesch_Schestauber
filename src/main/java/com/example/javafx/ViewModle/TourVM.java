package com.example.javafx.ViewModle;

import com.example.javafx.business.TourListener;
import com.example.javafx.business.TourManager;
import com.example.javafx.model.Tour;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.io.IOException;
import java.util.List;

public class TourVM implements TourListener {

    private final TourManager manager;
    public ObservableList<String> listItems;
    public final StringProperty name = new SimpleStringProperty();
    public final StringProperty description = new SimpleStringProperty();
    public final StringProperty from = new SimpleStringProperty();
    public final StringProperty to = new SimpleStringProperty();
    public final StringProperty type = new SimpleStringProperty();

    final ObservableList<Tour> tours = FXCollections.observableArrayList();

    public TourVM(TourManager manager) {
        this.manager = manager;
        this.listItems = FXCollections.observableArrayList();
        this.manager.addTourListener(this);

    }

    public ObservableList<String> getListItems() {
        return listItems;
    }

    public void add() throws IOException, InterruptedException {
        manager.addTour(name.getValue(), description.getValue(), from.getValue(), to.getValue(), type.getValue());
    }

    public void edit(){
        Tour tour = manager.getTour(name.getValue());
        tour.setDescription(description.getValue());
        tour.setStart(from.getValue());
        tour.setDestin(to.getValue());
        tour.setType(type.getValue());
        manager.editTour(tour);
    }

    public void delete(String name){
        manager.deleteTour(name);
    }

    public ObservableList<String> getTourNames(){
        return manager.getTours();
    }

    public ObservableList<Tour> getTours(){
        listChanged();
        return tours;
    }

    public void getTourList(){
        List<String> tournames = manager.getTours();
        Tour newTour;
        for (String name: tournames) {
            newTour = manager.getTour(name);
            this.tours.add(newTour);
        }
    }

    @Override
    public void listChanged() {
        //TODO wieder auskommentieren
        listItems.setAll(manager.getTours());
        tours.clear();
        getTourList();
        //tours.setAll(tours.stream().filter(tour -> tour.getName().contains("")).toList());
    }

    public void setListItems(ObservableList<String> listItems) {
        this.listItems = listItems;
    }
}
