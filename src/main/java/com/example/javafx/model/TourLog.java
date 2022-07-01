package com.example.javafx.model;

public class TourLog {
   /*
    SimpleDateFormat dateFormat = new SimpleDateFormat("dd.MM.yyyy");
    SimpleDateFormat timeFormat = new SimpleDateFormat("dd.MM.yyyy");
    Date date = dateFormat.parse("16.04.2022");
*/

    int id;
    String tourname;
    String date;
    String time;
    String comment;
    //1 - sehr leicht -> 5 - sehr schwer
    int difficulty;
    double totalTime;
    //1 - schlecht -> 5 - sehr gut
    int rating;

    public TourLog(int id, String tourname, String date, String time, String comment, int difficulty, double totalTime, int rating){
        this.id = id;
        this.tourname = tourname;
        this.date = date;
        this.time = time;
        this.comment = comment;
        this.difficulty = difficulty;
        this.totalTime = totalTime;
        this.rating = rating;
    }


    public int getId() {
        return id;
    }

    public String getTourname() {
        return tourname;
    }

    public int getDifficulty() {
        return difficulty;
    }

    public int getRating() {
        return rating;
    }

    public double getTotalTime() {
        return totalTime;
    }

    public String getComment() {
        return comment;
    }

    public String getDate() {
        return date;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public void setDifficulty(int difficulty) {
        this.difficulty = difficulty;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }

    public void setTotalTime(int totalTime) {
        this.totalTime = totalTime;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setTourname(String tourname) {
        this.tourname = tourname;
    }
}
