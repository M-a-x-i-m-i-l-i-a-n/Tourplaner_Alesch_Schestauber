package com.example.javafx.Business;

import com.example.javafx.Model.TourLog;
import javafx.collections.ObservableList;

import java.util.List;

public interface TourLogManager {
    static TourLogManager getInstance() {
        return null;
    }

    void addTourLogListener(TourLogListener listener);

    void addTourLog(String date, String time, String timeNeeded, String difficulty, String rating, String comment, String Tourname);
    TourLog getTourLog(int id);
    void deleteTourLog(TourLog log);
    ObservableList<TourLog> getTourLogsByName(String tourname);
    List<String> getTours();

    void editTourLog(TourLog log);

    ObservableList<Integer> getTourLogIds();
}
