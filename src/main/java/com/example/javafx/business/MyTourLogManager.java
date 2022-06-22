package com.example.javafx.business;

import com.example.javafx.DataAccessLayer.LogDAO;
import com.example.javafx.model.TourLog;
import javafx.collections.ObservableList;

import java.util.ArrayList;
import java.util.List;

public class MyTourLogManager implements TourLogManager{
    private LogDAO logDAO;
    private List<TourLogListener> listeners;


    public static MyTourLogManager instance;
    public static MyTourLogManager getInstance() {
        if (MyTourLogManager.instance == null) {
            MyTourLogManager.instance = new MyTourLogManager();
            instance.init();
        }
        return MyTourLogManager.instance;
    }

    private void init(){
        this.logDAO = LogDAO.getInstance();
        this.listeners = new ArrayList<>();
    }


    @Override
    public void addTourLogListener(TourLogListener listener) {
        this.listeners.add(listener);
    }

    @Override
    public void addTourLog(String date, String time, String timeNeeded, String difficulty, String rating, String comment, String TourName) {
        logDAO.createTourLog(date, time, timeNeeded, difficulty, rating, comment, TourName);
    }

    public ObservableList<Integer> getTourLogIds(){
        return logDAO.getLogIds();
    }
    @Override
    public TourLog getTourLog(int id) {
        return logDAO.getTourById(id);
    }

    @Override
    public void deleteTourLog(String name) {

    }

    public void fireGetTourLogs(){
        for(TourLogListener listener : listeners){

        }
    }

    public void onGetTours(){

    }

    public ObservableList<TourLog> getTourLogsByName(String tourname){
        return logDAO.getToursByTourname(tourname);
    }

    @Override
    public List<String> getTours() {
        return null;
    }
}
