package com.example.javafx.business;

import com.example.javafx.model.Tour;

import java.io.File;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class TourImportExport {

    private static Logger logger = LogManager.getLogger();
    public TourImportExport(){

    }

    public void exportTour(Tour tour){
        try {

            String[] data = new String[]{tour.getName(), tour.getDescription(), tour.getStart(), tour.getDestin(), tour.getType(), tour.getTime(),
                    tour.getLrlng(), tour.getDistance().toString(), tour.getLrlat(), tour.getUllat(), tour.getUllng(),  tour.getSessionID(), tour.getUrl()+ "'"};

            File csvOutputFile = new File("./Files/Exports/" + tour.getName() + ".csv");
            try (PrintWriter pw = new PrintWriter(csvOutputFile)) {
                String tourData = convertToCSV(data);
                pw.println(tourData);
            }
            csvOutputFile.exists();

        }catch (Exception e){
            logger.info("Could not export Tour in business/TourImportExport/exportTour");
            logger.debug(e);
        }
    }

    public String convertToCSV(String[] data) {
        return Stream.of(data)
                .collect(Collectors.joining("'"));
    }


    public Tour importTour(File file){
        try {
            Scanner scanner = new Scanner(file);
            scanner.useDelimiter("'");
            List<String> input = new ArrayList<>();
            while(scanner.hasNext()){
                input.add(scanner.next());
            }

            Tour tour = new Tour(input.get(0), input.get(1),input.get(2),input.get(3),input.get(4),input.get(5), input.get(6), Double.parseDouble(input.get(7)),input.get(8),input.get(9),input.get(10),input.get(11),input.get(12));
            return tour;
        }catch (Exception e){
            logger.debug(e);
        }
        return null;
    }
}
