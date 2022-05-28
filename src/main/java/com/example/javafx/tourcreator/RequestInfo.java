package com.example.javafx.tourcreator;

public class RequestInfo {
    String start;
    String destination;
    Double distance;
    String mapUrl;
    Integer length;
    Integer width;
    String sessionID;
    Integer travTime;
    String travelMethod;


    public RequestInfo(){

    }




    public RequestInfo(String start, String destination, Double distance, String mapUrl, Integer width, Integer length, Integer travTime, String travelMethod){
        this.start = start;
        this.destination = destination;
        this.distance = distance;
        this.mapUrl = mapUrl;
        this.width = width;
        this.length = length;
        this.travTime = travTime;
        this.travelMethod = travelMethod;


    }

    public String getTravelMethod() {
        return travelMethod;
    }

    public void setTravelMethod(String travelMethod) {
        this.travelMethod = travelMethod;
    }

    public Integer getTravTime() {
        return travTime;
    }

    public void setTravTime(Integer travTime) {
        this.travTime = travTime;
    }

    public String getMapUrl() {
        return mapUrl;
    }

    public void setMapUrl(String mapUrl) {
        this.mapUrl = mapUrl;
    }

    public Integer getLength() {
        return length;
    }

    public void setLength(Integer length) {
        this.length = length;
    }
    public void setWidth(Integer width) {
        this.width = width;
    }

    public Integer getWidth() {
        return width;
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

    public String getSessionID() {
        return sessionID;
    }

    public void setSessionID(String sessionID) {
        this.sessionID = sessionID;
    }
}
