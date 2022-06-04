package com.example.javafx.model;

import java.util.ArrayList;

public class Tour {
    String name;
    String description;
    String start;
    String destin;
    String type;
    Double distance;
    String time;
    String sessionID;
    String url;
    String lrlng;
    String lrlat;
    String ullng;
    String ullat;

    ArrayList<TourLog> logs;


    public Tour(String name, String description, String start, String destin, String type, String time, Double distance, String lrlng, String lrlat, String ullng, String ullat, String url, String sessionID){
        this.name = name;
        this.description = description;
        this.start = start;
        this.destin = destin;
        this.type = type;
        this.distance = distance;
        this.time = time;
        this.lrlat = lrlat;
        this.lrlng = lrlng;
        this.ullat = ullat;
        this.ullng = ullng;
        this.url = url;
        this.sessionID = sessionID;
    }
    public Tour(String name, String description, String start, String destin, String type){
        this.name = name;
        this.description = description;
        this.start = start;
        this.destin = destin;
        this.type = type;


    }

    @Override
    public String toString(){
        return getName();
    }

    public Double getDistance() {
        return distance;
    }

    public String getTime() {
        return time;
    }

    public String getSessionID() {return sessionID;}

    public void setSessionID(String sessionID) {this.sessionID = sessionID;}

    public String getUrl() {return url;}

    public void setUrl(String mapUrl) {this.url = mapUrl;}

    public String getLrlng() {return lrlng;}

    public void setLrlng(String lrlng) {this.lrlng = lrlng;}

    public String getLrlat() {return lrlat;}

    public void setLrlat(String lrlat) {this.lrlat = lrlat;}

    public String getUllng() {return ullng;}

    public void setUllng(String ullng) {this.ullng = ullng;}

    public String getUllat() {return ullat;}

    public void setUllat(String ullat) {this.ullat = ullat;}

    public String getDescription() {
        return description;
    }

    public String getStart() {
        return start;
    }

    public String getName() {
        return name;
    }

    public String getDestin() {
        return destin;
    }

    public String getType() {
        return type;
    }

    public ArrayList<TourLog> getLogs() {
        return logs;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setDistance(Double distance) {
        this.distance = distance;
    }

    public void setStart(String start) {
        this.start = start;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public void setDestin(String destin) {
        this.destin = destin;
    }

    public void setType(String type) {
        this.type = type;
    }

    //Fügt ein Tourlog zu der Tour hinzu
    public void addTourLog(TourLog log){
        logs.add(log);
    }
}