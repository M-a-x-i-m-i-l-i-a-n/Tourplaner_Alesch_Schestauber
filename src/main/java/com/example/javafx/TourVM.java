package com.example.javafx;

import com.example.javafx.business.TourListener;
import com.example.javafx.business.TourManager;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class TourVM implements TourListener {

    private final TourManager manager;
    private ObservableList<String> listItems;

    public TourVM(TourManager manager) {
        this.manager = manager;
        this.listItems = FXCollections.observableArrayList();
        this.manager.addTourListener(this);
    }

    public ObservableList<String> getListItems() {
        return listItems;
    }

    public void add(String name, String description, String from, String to, String type) {
        manager.addTour(name, description, from, to, type);
    }

    public void delete(String name){
        manager.deleteTour(name);
    }
    @Override
    public void listChanged() {
        //TODO wieder auskommentieren
        //listItems.setAll(manager.getTours());
    }
}
