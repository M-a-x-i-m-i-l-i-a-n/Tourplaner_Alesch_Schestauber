package com.example.javafx.business;

import com.example.javafx.model.Tour;
import com.example.javafx.model.TourLog;
import javafx.collections.ObservableList;

import java.util.List;

public interface TourLogManager {
    static TourLogManager getInstance() {
        return null;
    }

    void addTourLogListener(TourLogListener listener);

    void addTourLog(String date, String time, String timeNeeded, String difficulty, String rating, String comment, String Tourname);
    TourLog getTourLog(int id);
    void deleteTourLog(String name);
    List<String> getTours();
    ObservableList<Integer> getTourLogIds();
}
