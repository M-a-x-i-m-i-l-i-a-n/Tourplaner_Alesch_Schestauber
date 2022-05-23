package com.example.javafx.tourcreator;

public class RequestInfo {
    String start;
    String destination;
    Double distance;
    String mapUrl;


    public RequestInfo(){

    }



    public RequestInfo(String start, String destination, Double distance, String mapUrl){
        this.start = start;
        this.destination = destination;
        this.distance = distance;
        this.mapUrl = mapUrl;

    }

    public String getStart() {
        return start;
    }

    public void setStart(String start) {
        this.start = start;
    }

    public String getDestination() {
        return destination;
    }

    public void setDestination(String destination) {
        this.destination = destination;
    }

    public Double getDistance() {
        return distance;
    }

    public void setDistance(Double distance) {
        this.distance = distance;
    }

    public String getMapUrl() {
        return mapUrl;
    }

    public void setMapUrl(String mapUrl) {
        this.mapUrl = mapUrl;
    }
}
