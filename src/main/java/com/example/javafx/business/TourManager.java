package com.example.javafx.business;

import com.example.javafx.model.Tour;
import javafx.collections.ObservableList;

import java.io.File;
import java.io.IOException;
import java.util.List;

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
    ObservableList<String> getTours();

    void editTour(Tour tour);
}
