package com.example.javafx.business;

import com.example.javafx.DataAccessLayer.TourDAO;
import com.example.javafx.model.Tour;
import javafx.collections.ObservableList;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class MyTourManager implements TourManager {

    private List<TourListener> listeners;
    private TourDAO tourDAO;


    public static MyTourManager instance;

    public static MyTourManager getInstance() {
        if (MyTourManager.instance == null) {
            MyTourManager.instance = new MyTourManager();
            instance.init();
        }
        return MyTourManager.instance;
    }

    private void init(){
        this.listeners = new ArrayList<>();
        this.tourDAO = TourDAO.getInstance();
    }

    public void addTour(String name, String description, String from, String to, String type) throws IOException, InterruptedException {
        //TODO hier müsste dann mittels mapquest die Map sowie die Distanz und Zeit abgefragt werden und in den Funktionsaufruf reingegeben werden
        Tour tour = new Tour(name, description, from, to, type);
        SendRequest client = new SendRequest();
        client.sendRequest(tour);
        tourDAO.createTour(tour);
        fireToursChanged();
    }

    public void editTour(Tour tour){
        //TODO hier müsste dann mittels mapquest die Map sowie die Distanz und Zeit abgefragt werden und in den Funktionsaufruf reingegeben werden
        tourDAO.updateTour(tour);
        fireToursChanged();
    }

    public Tour getTour(String name){
        return tourDAO.getTourByName(name);
    }

    public ObservableList<String> getTours(){
        return tourDAO.getAllTourNames();
    }
    /*
    public List<String> getTours() {
        //TODO das hier mit .stream und .map nochmal anschauen (kann sehr nützlich sein)
        return tourDao.findAll().stream().map(t -> t.getName()).collect(Collectors.toList());
    }
*/
    public void deleteTour(String name){
        tourDAO.deleteTour(name);
        fireToursChanged();
    }
    public void addTourListener(TourListener listener) {
        listeners.add(listener);
    }

    private void fireToursChanged() {
        //listeners.forEach(TourListener::listChanged);
        for (TourListener listener : listeners) {
            listener.listChanged();
        }
    }
}
