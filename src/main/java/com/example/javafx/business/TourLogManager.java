package com.example.javafx.business;

import com.example.javafx.model.Tour;
import com.example.javafx.model.TourLog;

import java.util.List;

public interface TourLogManager {
    void addTourListener(TourListener listener);
    void addTourLog(String date, String time, String timeNeeded, String difficulty, String rating, String comment, String Tourname);
    TourLog getTourLog(String name);
    void deleteTourLog(String name);
    List<String> getTours();
}
