package com.example.javafx.ViewModle;

import com.example.javafx.business.TourListener;
import com.example.javafx.business.TourManager;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.util.List;

public class TourVM implements TourListener {

    private final TourManager manager;
    private ObservableList<String> listItems;
    private String name;
    private String description;
    private String from;
    private String to;
    private String type;

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

    public List<String> getTourNames(){
        return manager.getTours();
    }

    @Override
    public void listChanged() {
        //TODO wieder auskommentieren
        listItems.setAll(manager.getTours());
    }

    public String getType() {
        return type;
    }

    public String getTo() {
        return to;
    }

    public String getName() {
        return name;
    }

    public String getFrom() {
        return from;
    }

    public String getDescription() {
        return description;
    }

    public void setType(String type) {
        this.type = type;
    }

    public void setTo(String to) {
        this.to = to;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setFrom(String from) {
        this.from = from;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setListItems(ObservableList<String> listItems) {
        this.listItems = listItems;
    }
}
