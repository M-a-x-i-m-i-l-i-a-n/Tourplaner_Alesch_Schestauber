package com.example.javafx.business;

import com.example.javafx.DataAccessLayer.LogDAO;
import com.example.javafx.model.TourLog;
import javafx.collections.ObservableList;

import java.util.ArrayList;
import java.util.List;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class MyTourLogManager implements TourLogManager{
    private LogDAO logDAO;
    private List<TourLogListener> listeners;
    private static Logger logger = LogManager.getLogger();

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
    private boolean checkInput(String toCheck){
        if (toCheck == null) {
            return false;
        }
        try {
            double d = Double.parseDouble(toCheck);
        } catch (NumberFormatException nfe) {
            logger.debug("Invalid User-Input");
            return false;
        }
        return true;
    }

    @Override
    public void addTourLogListener(TourLogListener listener) {
        this.listeners.add(listener);
    }

    @Override
    public void addTourLog(String date, String time, String timeNeeded, String difficulty, String rating, String comment, String TourName) {
        if(timeNeeded.contains(",")){
            timeNeeded = timeNeeded.replace(",", ".");
        }
        if(checkInput(timeNeeded)) {
            logDAO.createTourLog(date, time, timeNeeded, difficulty, rating, comment, TourName);
            fireGetTourLogs();
        }else{
            logger.debug("Do to an invalid user-input the tour-log could not be saved");
        }
    }

    public ObservableList<Integer> getTourLogIds(){
        return logDAO.getLogIds();
    }
    @Override
    public TourLog getTourLog(int id) {
        return logDAO.getTourById(id);
    }

    @Override
    public void deleteTourLog(TourLog log) {
        logDAO.deleteTourLog(log.getId());
        fireGetTourLogs();
    }

    public void editTourLog(TourLog log){
        logDAO.updateTourLog(log);
        fireGetTourLogs();
    }
    public void fireGetTourLogs(){
        for(TourLogListener listener : listeners){
            listener.logsChanged();
        }

    }


    public void onGetTours(){

    }

    public ObservableList<TourLog> getTourLogsByName(String tourname){
        return logDAO.getTourLogsByTourname(tourname);
    }

    @Override
    public List<String> getTours() {
        return null;
    }
}
