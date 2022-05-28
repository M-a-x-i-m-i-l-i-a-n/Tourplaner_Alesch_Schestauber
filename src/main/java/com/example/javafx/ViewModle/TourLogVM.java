package com.example.javafx.ViewModle;

import com.example.javafx.business.TourListener;
import com.example.javafx.business.TourLogManager;
import com.example.javafx.business.TourManager;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class TourLogVM implements TourListener {
    private final TourLogManager manager;
    private ObservableList<String> listItems;

    public TourLogVM(TourLogManager manager){
        this.manager = manager;
        this.listItems = FXCollections.observableArrayList();
        this.manager.addTourListener(this);
    }
    public ObservableList<String> getListItems() {
        return listItems;
    }

    public void addTourLog(String date, String time, String timeNeeded, String difficulty, String rating, String comment, String Tourname){
        manager.addTourLog(date, time, timeNeeded, difficulty, rating, comment, Tourname);
    }



    @Override
    public void listChanged() {

    }
}
