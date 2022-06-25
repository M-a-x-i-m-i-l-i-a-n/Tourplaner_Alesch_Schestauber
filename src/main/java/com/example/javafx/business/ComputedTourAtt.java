package com.example.javafx.business;

import com.example.javafx.model.Tour;
import com.example.javafx.model.TourLog;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class ComputedTourAtt {
    private final MyTourManager tourManager = MyTourManager.getInstance();
    MyTourLogManager logManager = MyTourLogManager.getInstance();
    ObservableList<TourLog> logs = FXCollections.observableArrayList();

    public Tour calcularePopularity(Tour tour){
        int count = logManager.getTourLogsByName(tour.getName()).size();
        tour.setPopularity(count);
        tour = calculateChildFriendliness(tour);
        return tour;
    }

    public Tour calculateChildFriendliness(Tour tour){
        logs = logManager.getTourLogsByName(tour.getName());
        int difficulty = 0, totalTimes = 0;
        for(TourLog log : logs){
            difficulty += log.getDifficulty();
            totalTimes += log.getTotalTime();
        }
        if(logs.size() > 0){
            tour.setChildFriendliness(difficulty/ logs.size(), totalTimes/ logs.size());
        }
        else{
            tour.setChildFriendliness(0, 0);
        }
        return tour;
    }
}
