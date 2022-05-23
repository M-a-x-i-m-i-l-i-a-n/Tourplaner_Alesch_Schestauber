package com.example.javafx.DataAccessLayer;

import com.example.javafx.model.TourLog;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class LogHandler {
    public static LogHandler instance;
    public static LogHandler getInstance() {
        if (LogHandler.instance == null) {
            LogHandler.instance = new LogHandler();
        }
        return LogHandler.instance;
    }
    //Speichert ein Tourlog in der Datenbank
    public void createTour(TourLog log){
        //Die id gibt an die wievielte Tour das ist damit die Tours leichter ausgelesen und dann als Liste angezeigt werden können
        //Das + 1 ist weil über getTourCount() die Anzahl der gespeicherten Tours ausgelesen wird und die neu gespeicherte Tour ist dann um 1 mehr
        int id = getTourLogCount() + 1;
        try{
            Connection conn = DatabaseHandler.getInstance().getConnection();
            PreparedStatement statement = conn.prepareStatement("INSERT INTO public.\"tourlogs\"(id, tourname, date, time, comment, difficulty, total-time,rating) VALUES(?,?,?,?,?,?,?,?);");
            statement.setInt(1,id);
            statement.setString(2, log.getTourname());
            statement.setString(3, log.getDate());
            statement.setString(4, log.getTime());
            statement.setString(5, log.getComment());
            statement.setInt(6, log.getDifficulty());
            statement.setInt(7, log.getTotalTime());
            statement.setInt(8, log.getRating());

            ResultSet resultSet = statement.executeQuery();

            statement.close();
            conn.close();
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    //Updated ein Tourlog in der Datenabnk
    public void updateTourLog(TourLog log){
        try{
            Connection conn = DatabaseHandler.getInstance().getConnection();
            PreparedStatement statement = conn.prepareStatement("UPDATE public.\"tourlogs\" SET tourname = ?, date = ?, " +
                    " time = ?, comment = ?, difficulty = ?, total-time = ?, rating = ? WHERE id = ?;");
            statement.setString(1, log.getTourname());
            statement.setString(2, log.getDate());
            statement.setString(3, log.getTime());
            statement.setString(4, log.getComment());
            statement.setInt(5, log.getDifficulty());
            statement.setInt(6, log.getTotalTime());
            statement.setInt(7, log.getRating());
            statement.setInt(8, log.getId());

            statement.executeUpdate();
            statement.close();
            conn.close();

        }catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    //Diese Funktion gibt ein TourLog anhand der id zurück
    public TourLog getTourById(int id){
        try{
            Connection conn = DatabaseHandler.getInstance().getConnection();
            PreparedStatement statement = conn.prepareStatement("SELECT  tourname, date, time, comment, difficulty, total-time,rating FROM public.\"tourlogs\" WHERE id = ?;");
            statement.setInt(1, id);
            ResultSet resultSet = statement.executeQuery();

            //User(String username, String password, String token, int coins, boolean admin)
            if(resultSet.next()){
                TourLog log = new TourLog(id,resultSet.getString(1),
                        resultSet.getString(2),
                        resultSet.getString(3), resultSet.getString(4), resultSet.getInt(5), resultSet.getInt(6), resultSet.getInt(7));
                resultSet.close();
                statement.close();
                conn.close();
                return log;
            }

        }catch (SQLException e){
            e.printStackTrace();
        }
        return null;
    }

    public ArrayList<TourLog> getToursByTourname(String tourname){
        ArrayList<TourLog> logs = new ArrayList<>();
        TourLog log;
        try{
            Connection conn = DatabaseHandler.getInstance().getConnection();
            PreparedStatement statement = conn.prepareStatement("SELECT * FROM public.\"tourlogs\" WHERE name = ?;");
            statement.setString(1, tourname);
            ResultSet resultSet = statement.executeQuery();

            //User(String username, String password, String token, int coins, boolean admin)

            while (resultSet.next()) {
                log = new TourLog(resultSet.getInt(1), resultSet.getString(2),
                        resultSet.getString(3),
                        resultSet.getString(4), resultSet.getString(5), resultSet.getInt(6), resultSet.getInt(7), resultSet.getInt(8));
                logs.add(log);
            }
                resultSet.close();
                statement.close();
                conn.close();
                return logs;


        }catch (SQLException e){
            e.printStackTrace();
            return null;
        }
    }

    public void deleteTourLog(int id){
        try {
            Connection conn = DatabaseHandler.getInstance().getConnection();
            PreparedStatement statement = conn.prepareStatement("DELETE FROM public.\"tourlogs\" WHERE id = ?;");
            statement.setInt(1, id);

            statement.executeUpdate();

            statement.close();
            conn.close();


        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    //Liefert die Anzahl der Tourlogs zurück
    public int getTourLogCount(){
        int rowcount = 0;
        try {
            Connection conn = DatabaseHandler.getInstance().getConnection();
            PreparedStatement statement = conn.prepareStatement("SELECT count(*) FROM public.\"tourlogs\"");
            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {
                rowcount = resultSet.getInt(1);
            }
            statement.close();
            conn.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return rowcount;
    }
}
