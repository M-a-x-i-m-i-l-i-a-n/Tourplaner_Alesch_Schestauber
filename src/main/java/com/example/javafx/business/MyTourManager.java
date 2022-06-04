package com.example.javafx.business;

import com.example.javafx.DataAccessLayer.TourDAO;
import com.example.javafx.model.Tour;

import java.util.ArrayList;
import java.util.List;

public class MyTourManager implements TourManager {

    private List<TourListener> listeners;
    private TourDAO tourHandler;
    public MyTourManager() {
        this.listeners = new ArrayList<>();
        this.tourHandler = TourDAO.getInstance();
    }

    public void addTour(String name, String description, String from, String to, String type) {
        //TODO hier müsste dann mittels mapquest die Map sowie die Distanz und Zeit abgefragt werden und in den Funktionsaufruf reingegeben werden
        tourHandler.createTour(new Tour(name, description, from, to, type));
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
    public void deleteTour(String name){
        tourHandler.deleteTour(name);
    }
    public void addTourListener(TourListener listener) {
        listeners.add(listener);
    }

    private void fireToursChanged() {
        listeners.forEach(l->l.listChanged());
    }
}
