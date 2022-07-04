package com.example.javafx.Business;

import com.example.javafx.Model.Tour;
import com.example.javafx.Model.TourLog;
import javafx.collections.ObservableList;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

public interface TourManager {

    void addTourListener(TourListener listener);

    static MyTourManager getInstance() {
        return null;
    }

    void addTour(String name, String description, String from, String to, String type) throws IOException, InterruptedException;
    Tour getTour(String name);
    void deleteTour(String name);

    void exportTour(Tour tour);
    void importTour(File file);

    void callPDFGenerator(Tour tour, ObservableList<TourLog> logs) throws IOException;
    void callPDFStatGenerator(ObservableList<Tour> tours) throws FileNotFoundException;

    ObservableList<String> getTours();

    void editTour(Tour tour);
}
