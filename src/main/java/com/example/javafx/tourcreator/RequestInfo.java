package com.example.javafx.tourcreator;

public class RequestInfo {
    String start;
    String destination;
    Double distance;
    String mapUrl;
    Integer length;
    Integer width;
    String sessionID;
    String travTime;
    String travelMethod;
    String lrlng;
    String lrlat;
    String ullng;
    String ullat;


    public RequestInfo(){

    }

    public RequestInfo(String start, String destination, Double distance, String mapUrl, Integer width, Integer length, String travTime, String travelMethod, String lrlng, String lrlat, String ullat, String ullng){
        this.start = start;
        this.destination = destination;
        this.distance = distance;
        this.mapUrl = mapUrl;
        this.width = width;
        this.length = length;
        this.travTime = travTime;
        this.travelMethod = travelMethod;
        this.lrlng = lrlng;
        this.lrlat = lrlat;
        this.ullng = ullng;
        this.ullat = ullat;

    }

    public String getTravelMethod() {
        return travelMethod;
    }

    public void setTravelMethod(String travelMethod) {
        this.travelMethod = travelMethod;
    }

    public String getTravTime() {
        return travTime;
    }

    public void setTravTime(String travTime) {
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

    public String getLrlng() {return lrlng;}

    public void setLrlng(String lrlng) {this.lrlng = lrlng;}

    public String getLrlat() {return lrlat;}

    public void setLrlat(String lrlat) {this.lrlat = lrlat;}

    public String getUllng() {return ullng;}

    public void setUllng(String ullng) {this.ullng = ullng;}

    public String getUllat() {return ullat;}

    public void setUllat(String ullat) {this.ullat = ullat;}
}
