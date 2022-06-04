package com.example.javafx.model;

import java.util.ArrayList;

public class Tour {
    String name;
    String description;
    String from;
    String to;
    String type;
    int distance;
    int time;
    int id;
    ArrayList<TourLog> logs;


    public Tour(){

    }
    public Tour(String name, String description, String from, String to, String type, int distance, int time, int id){
        this.name = name;
        this.description = description;
        this.from = from;
        this.to = to;
        this.type = type;
        this.distance = distance;
        this.time = time;
        this.id = id;
    }

    public int getDistance() {
        return distance;
    }

    public int getTime() {
        return time;
    }

    public String getDescription() {
        return description;
    }

    public String getFrom() {
        return from;
    }

    public String getName() {
        return name;
    }

    public String getTo() {
        return to;
    }

    public String getType() {
        return type;
    }

    public int getId() {
        return id;
    }

    public ArrayList<TourLog> getLogs() {
        return logs;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setDistance(int distance) {
        this.distance = distance;
    }

    public void setFrom(String from) {
        this.from = from;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setTime(int time) {
        this.time = time;
    }

    public void setTo(String to) {
        this.to = to;
    }

    public void setType(String type) {
        this.type = type;
    }

    //Fügt ein Tourlog zu der Tour hinzu
    public void addTourLog(TourLog log){
        logs.add(log);
    }
}