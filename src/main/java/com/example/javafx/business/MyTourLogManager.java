package com.example.javafx.business;

import com.example.javafx.DataAccessLayer.LogDAO;
import com.example.javafx.model.TourLog;

import java.util.List;

public class MyTourLogManager implements TourLogManager{
    private LogDAO logDAO;

    public MyTourLogManager(){
        this.logDAO = LogDAO.getInstance();
    }
    @Override
    public void addTourListener(TourListener listener) {

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

    @Override
    public List<String> getTours() {
        return null;
    }
}
