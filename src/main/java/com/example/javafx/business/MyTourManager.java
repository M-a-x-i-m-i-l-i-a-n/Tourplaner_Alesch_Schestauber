package com.example.javafx.business;

import com.example.javafx.DataAccessLayer.TourHandler;
import com.example.javafx.model.Tour;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class MyTourManager implements TourManager {

    private List<TourListener> listeners;
    private TourHandler tourHandler;
    public MyTourManager() {
        this.listeners = new ArrayList<>();
        this.tourHandler = TourHandler.getInstance();
    }

    public void addTour(String name, String description, String from, String to, String type, int distance, int time, int id) {
        tourHandler.createTour(new Tour(name, description, from, to, type, distance, time, id));
        fireToursChanged();
    }

    public Tour getTour(String name){
        return tourHandler.getTourByName(name);
    }

    public List<String> getTours(){
        return tourHandler.getAllTourNames();
    }
    /*
    public List<String> getTours() {
        //TODO das hier mit .stream und .map nochmal anschauen (kann sehr nützlich sein)
        return tourDao.findAll().stream().map(t -> t.getName()).collect(Collectors.toList());
    }
*/
    public void addTourListener(TourListener listener) {
        listeners.add(listener);
    }

    private void fireToursChanged() {
        listeners.forEach(l->l.listChanged());
    }
}
