package com.example.javafx.ViewModle;

import com.example.javafx.business.TourListener;
import com.example.javafx.business.TourLogListener;
import com.example.javafx.business.TourLogManager;
import com.example.javafx.business.TourManager;
import com.example.javafx.model.Tour;
import com.example.javafx.model.TourLog;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.util.List;

public class TourLogVM implements TourLogListener {
    private final TourLogManager manager;
    private ObservableList<String> listItems;

    public final StringProperty date = new SimpleStringProperty();
    public final StringProperty time = new SimpleStringProperty();
    public final StringProperty timeNeeded = new SimpleStringProperty();
    public final StringProperty difficulty = new SimpleStringProperty();
    public final StringProperty rating = new SimpleStringProperty();
    public final StringProperty comment = new SimpleStringProperty();
    public final StringProperty Tourname = new SimpleStringProperty();

    ObservableList<TourLog> logs = FXCollections.observableArrayList();
    public TourLogVM(TourLogManager manager){
        this.manager = manager;
        this.listItems = FXCollections.observableArrayList();
        this.manager.addTourLogListener(this);
    }

    public ObservableList<String> getListItems() {
        return listItems;
    }

    public void addTourLog(String Tourname){
        manager.addTourLog(date.getValue(), time.getValue(), timeNeeded.getValue(), difficulty.getValue(), rating.getValue(), comment.getValue(), Tourname);
    }

    public ObservableList<TourLog> getTourLogs(){
        List<Integer> ids = manager.getTourLogIds();
        TourLog log;
        logs.clear();
        for (int id: ids) {
            log = manager.getTourLog(id);
            this.logs.add(log);
        }
        return logs;
    }

    public ObservableList<TourLog> getLogsByTourname(String tourname){
        return manager.getTourLogsByName(tourname);
    }

    @Override
    public void logsChanged() {

    }
}
