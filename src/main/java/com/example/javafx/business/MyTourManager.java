package com.example.javafx.business;

import com.example.javafx.DataAccessLayer.TourDAO;
import com.example.javafx.business.ReportGenerator.PDFReport;
import com.example.javafx.model.Tour;
import com.example.javafx.model.TourLog;
import javafx.collections.ObservableList;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class MyTourManager implements TourManager {

    private List<TourListener> listeners;
    private TourDAO tourDAO;
    private TourImportExport export;
    ComputedTourAtt calc;
    private static Logger logger = LogManager.getLogger();
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
        this.calc = new ComputedTourAtt();
        this.export = new TourImportExport();
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

    public void addTour(String name, String description, String from, String to, String type) throws IOException, InterruptedException {
        boolean checkFrom = false, checkTo = false;
        checkFrom = checkInput(from);
        checkTo = checkInput(to);
        if(checkFrom && checkTo) {
            Tour tour = new Tour(name, description, from, to, type);
            SendRequest client = new SendRequest();
            client.sendRequest(tour);
            tourDAO.createTour(tour);
            fireToursChanged();
        }else {
            logger.debug("Do to an invalid user-input the tour could not be saved");
        }
    }

    public void editTour(Tour tour){
        //TODO hier müsste dann mittels mapquest die Map sowie die Distanz und Zeit abgefragt werden und in den Funktionsaufruf reingegeben werden
        tourDAO.updateTour(tour);
        fireToursChanged();
    }

    public Tour getTour(String name){
        Tour tour = tourDAO.getTourByName(name);
        tour = calc.calcularePopularity(tour);

        return tour;
    }

    public ObservableList<String> getTours(){
        return tourDAO.getAllTourNames();
    }

    public void deleteTour(String name){
        tourDAO.deleteTour(name);
        fireToursChanged();
    }

    public void exportTour(Tour tour){
        export.exportTour(tour);
    }

    public void importTour(File file){
        Tour tour = export.importTour(file);
        tourDAO.createTour(tour);
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

    public void callPDFGenerator(Tour tour, ObservableList<TourLog> logs) throws IOException {
        PDFReport pdfReport =  new PDFReport();
        pdfReport.pdfTourGenerator(tour, logs);
    }

    public void callPDFStatGenerator(ObservableList<Tour> tours) throws FileNotFoundException {
        PDFReport pdfReport = new PDFReport();
        pdfReport.pdfStatGenerator(tours);
    }
}
