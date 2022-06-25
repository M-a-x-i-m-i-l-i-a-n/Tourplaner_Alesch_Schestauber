package com.example.javafx.business;

import com.example.javafx.model.Tour;

import java.io.File;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class TourImportExport {

    public TourImportExport(){

    }

    public void exportTour(Tour tour){
        try {

            String[] data = new String[]{tour.getName(), tour.getDescription(), tour.getStart(), tour.getDestin(), tour.getType(), tour.getTime(),
                    tour.getDistance().toString(), tour.getLrlng(), tour.getLrlat(), tour.getUllng(), tour.getUllat(),  tour.getSessionID(), tour.getUrl()+ "'"};


            File csvOutputFile = new File(tour.getName() + ".csv");
            try (PrintWriter pw = new PrintWriter(csvOutputFile)) {
                String tourdata = convertToCSV(data);
                pw.println(tourdata);
            }
            csvOutputFile.exists();

        }catch (Exception e){
            //TODO hier muss die Exception noch gelogged werden
        }
    }

    public String convertToCSV(String[] data) {
        return Stream.of(data)
                .collect(Collectors.joining("'"));
    }

    public String escapeSpecialCharacters(String data) {
        String escapedData = data.replaceAll("\\R", " ");
        if (data.contains(",") || data.contains("\"") || data.contains("'")) {
            data = data.replace("\"", "\"\"");
            escapedData = "\"" + data + "\"";
        }
        return escapedData;
    }

    public Tour importFile(File file){
        try {
            Scanner scanner = new Scanner(file);
            scanner.useDelimiter("'");
            List<String> input = new ArrayList<>();
            while(scanner.hasNext()){
                input.add(scanner.next());
            }

            Tour tour = new Tour(input.get(0), input.get(1),input.get(2),input.get(3),input.get(4),input.get(5), Double.parseDouble(input.get(6)),input.get(7),input.get(8),input.get(9),input.get(10),input.get(11),input.get(12));
            return tour;
        }catch (Exception e){
            //TODO loggen
        }
        return null;
    }
}
