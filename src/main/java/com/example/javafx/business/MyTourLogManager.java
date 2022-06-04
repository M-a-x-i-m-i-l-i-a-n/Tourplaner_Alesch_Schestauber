package com.example.javafx.business;

import com.example.javafx.DataAccessLayer.LogDAO;
import com.example.javafx.DataAccessLayer.TourDAO;
import com.example.javafx.model.TourLog;

import java.util.ArrayList;
import java.util.List;

public class MyTourLogManager implements TourLogManager{
    private LogDAO logDAO;
    private List<TourLogListener> tourLogListenerList;


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
    }


    @Override
    public void addTourLogListener(TourLogListener listener) {
        this.tourLogListenerList.add(listener);
    }

    @Override
    public void addTourLog(String date, String time, String timeNeeded, String difficulty, String rating, String comment, String TourName) {
        logDAO.createTourLog(date, time, timeNeeded, difficulty, rating, comment, TourName);
    }

    @Override
    public TourLog getTourLog(String name) {
        return null;
    }

    @Override
    public void deleteTourLog(String name) {

    }

    public void fireGetTourLogs(){
        for(TourLogListener listener : tourLogListenerList){

        }
    }

    public void onGetTours(){

    }

    @Override
    public List<String> getTours() {
        return null;
    }
}
