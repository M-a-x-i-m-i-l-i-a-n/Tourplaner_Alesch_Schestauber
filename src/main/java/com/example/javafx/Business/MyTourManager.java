package com.example.javafx.Business;

import com.example.javafx.DataAccessLayer.TourDAO;
import com.example.javafx.Business.ReportGenerator.PDFReport;
import com.example.javafx.Model.Tour;
import com.example.javafx.Model.TourLog;
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
        double d = 1;
        if (toCheck == null) {
            return false;
        }
        try {
            d = Double.parseDouble(toCheck);
            System.out.println("MyTourManager:: " + d);
        } catch (NumberFormatException nfe) {
            logger.debug("Invalid User-Input :: " + d);
            return true;
        }
        return false;
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
            logger.debug("Do to an invalid user-input the tour could not be saved::: From:" + from + " " + checkFrom + " TO: " + to + " " + checkTo);
        }
    }

    public void editTour(Tour tour){
       try {
           SendRequest client = new SendRequest();
           client.sendRequest(tour);
           tourDAO.updateTour(tour);
           fireToursChanged();
       }catch (Exception e){
           logger.debug(e);
       }
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
        File file = new File("./Files/images/" + name + ".jpg");
        if(file != null) {
            if (file.delete()) {
                logger.debug("Image of " + name + " deleted");
            }else {
                logger.debug("The Image of " + name + "could not be deleted");
            }
        }else {
            logger.debug("There is no file with that name.");
        }
        fireToursChanged();
    }

    public void exportTour(Tour tour){
        export.exportTour(tour);
    }

    public void importTour(File file){
        Tour tour = export.importTour(file);
        Tour controll = tourDAO.getTourByName(tour.getName());
        if(controll == null) {
            try {
                SendRequest client = new SendRequest();
                client.sendRequest(tour);
                tourDAO.createTour(tour);
                fireToursChanged();
            }catch (Exception e){
                logger.debug(e);
            }
        }else{
            logger.info("A tour with this name already exists.");
        }
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
