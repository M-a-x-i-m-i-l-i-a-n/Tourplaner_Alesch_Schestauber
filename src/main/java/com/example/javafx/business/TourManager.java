package com.example.javafx.business;

import com.example.javafx.model.Tour;

import java.util.List;

public interface TourManager {

    void addTourListener(TourListener listener);
    void addTour(String name, String description, String from, String to, String type);
    Tour getTour(String name);
    List<String> getTours();

}
