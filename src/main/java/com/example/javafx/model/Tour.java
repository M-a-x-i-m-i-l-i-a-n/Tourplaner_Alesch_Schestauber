package com.example.javafx.model;

import java.util.ArrayList;

public class Tour {
    private String name;
    private String description;
    private String start;
    private String destin;
    private String type;
    private Double distance;
    private String time;
    private String sessionID;
    private String url;
    private String lrlng;
    private String lrlat;
    private String ullng;
    private String ullat;
    //1-5 -> 1 - not popular ; 5 - very popular
    private int popularity;
    //1-5 -> 1 - not child friendly ; 5 - very child friendly
    private int childFriendliness;




    public Tour(String name, String description, String start, String destin, String type, String time, String lrlng, Double distance,  String lrlat, String ullng, String ullat, String sessionID, String url){

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


    public void setPopularity(int logsCount){
        if(logsCount > 5){
            popularity = 5;
        }else{
            if(logsCount < 0){
                popularity = 1;
            }
            else {
                popularity = logsCount;
            }
        }
    }
    public void setChildFriendliness(double difficulty, double totalTimes){
        childFriendliness = (int) ((totalTimes / difficulty * 25)/distance);
    }

    public int getChildFriendliness() {
        return childFriendliness;
    }

    public int getPopularity() {
        return popularity;
    }
}