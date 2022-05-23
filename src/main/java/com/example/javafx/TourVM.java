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

    public void add(String name, String description, String from, String to, String type, int distance, int time, int id) {
        manager.addTour(name, description, from, to, type, distance, time, id);
    }

    @Override
    public void listChanged() {
        listItems.setAll(manager.getTours());
    }
}
