package com.example.javafx.DataAccessLayer;

import com.example.javafx.model.Tour;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class TourHandler {

    public static TourHandler instance;

    public static TourHandler getInstance() {
        if (TourHandler.instance == null) {
            TourHandler.instance = new TourHandler();
        }
        return TourHandler.instance;
    }
    //TODO hier vielleicht noch logger hinzufügen

    //Mit dieser Funktion wird eine neue Tour in der Datenbank gespeichert
    public void createTour(Tour tour){
        //Die id gibt an die wievielte Tour das ist damit die Tours leichter ausgelesen und dann als Liste angezeigt werden können
        //Das + 1 ist weil über getTourCount() die Anzahl der gespeicherten Tours ausgelesen wird und die neu gespeicherte Tour ist dann um 1 mehr
        int id = getTourCount() + 1;
        try{
            Connection conn = DatabaseHandler.getInstance().getConnection();
            PreparedStatement statement = conn.prepareStatement("INSERT INTO public.\"tour\"(name, description, from, to, type, distance, time, id) VALUES(?,?,?,?,?,?,?,?);");
            statement.setString(1, tour.getName());
            statement.setString(2, tour.getDescription());
            statement.setString(3, tour.getFrom());
            statement.setString(4, tour.getTo());
            statement.setString(5, tour.getType());
            statement.setInt(4, tour.getDistance());
            statement.setInt(5, tour.getTime());
            statement.setInt(6,id);
            ResultSet resultSet = statement.executeQuery();

            statement.close();
            conn.close();
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }
    //Hiermit wird eine Tour anhand des names der Tour aus der Datenbank ausgelesen
    public Tour getTourByName(String tourname){
        try{
            Connection conn = DatabaseHandler.getInstance().getConnection();
            PreparedStatement statement = conn.prepareStatement("SELECT  description, from, to, type, distance, time, id FROM public.\"tour\" WHERE name = ?;");
            statement.setString(1, tourname);
            ResultSet resultSet = statement.executeQuery();

            //User(String username, String password, String token, int coins, boolean admin)
            if(resultSet.next()){
                Tour tour = new Tour(tourname, resultSet.getString(1),resultSet.getString(2),
                        resultSet.getString(3),
                        resultSet.getString(4), resultSet.getInt(5), resultSet.getInt(6), resultSet.getInt(7));
                resultSet.close();
                statement.close();
                conn.close();
                return tour;
            }

        }catch (SQLException e){
            e.printStackTrace();
        }
        return null;
    }

    public List<String> getAllTourNames(){
        List<String> names = new ArrayList<>();
        try{
            Connection conn = DatabaseHandler.getInstance().getConnection();
            PreparedStatement statement = conn.prepareStatement("SELECT  name FROM public.\"tour\";");
            ResultSet resultSet = statement.executeQuery();

            //User(String username, String password, String token, int coins, boolean admin)
            int i = 0;
            while (resultSet.next()){
                names.add(resultSet.getString(i));
                i++;
            }

        }catch (SQLException e){
            e.printStackTrace();
        }
        return null;
    }

    public Tour getTourById(int id){
        try{
            Connection conn = DatabaseHandler.getInstance().getConnection();
            PreparedStatement statement = conn.prepareStatement("SELECT  name, description, from, to, type, distance, time FROM public.\"tour\" WHERE id = ?;");
            statement.setInt(1, id);
            ResultSet resultSet = statement.executeQuery();

            //User(String username, String password, String token, int coins, boolean admin)
            if(resultSet.next()){
                Tour tour = new Tour(resultSet.getString(1),resultSet.getString(2),
                        resultSet.getString(3),
                        resultSet.getString(4), resultSet.getString(5), resultSet.getInt(6), resultSet.getInt(7), id);
                resultSet.close();
                statement.close();
                conn.close();
                return tour;
            }

        }catch (SQLException e){
            e.printStackTrace();
        }
        return null;
    }

    //Mit dieser Funktion werden die Daten einer Tour in der Datenbank updated
    public void updateTour(Tour tour){
        try{
            Connection conn = DatabaseHandler.getInstance().getConnection();
            PreparedStatement statement = conn.prepareStatement("UPDATE public.\"tour\" SET description = ?, from = ?, " +
                    " to = ?, type = ?, distance = ?, time = ? WHERE name = ?;");
            statement.setString(1, tour.getDescription());
            statement.setString(1, tour.getFrom());
            statement.setString(1, tour.getTo());
            statement.setString(1, tour.getType());
            statement.setInt(1, tour.getDistance());
            statement.setInt(1, tour.getTime());

            statement.executeUpdate();
            statement.close();
            conn.close();

        }catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    //Löscht eine Tour wieder aus der Datenbank
    public void deleteTour(String tourname){
        try {
            Connection conn = DatabaseHandler.getInstance().getConnection();
            PreparedStatement statement = conn.prepareStatement("DELETE FROM public.\"tour\" WHERE name = ?;");
            statement.setString(1, tourname);

            statement.executeUpdate();

            statement.close();
            conn.close();


        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    //Gibt die Anzahl der gespeicherten Tours zurück
    public int getTourCount(){
        int rowcount = 0;
        try {
            Connection conn = DatabaseHandler.getInstance().getConnection();
            PreparedStatement statement = conn.prepareStatement("SELECT count(*) FROM public.\"tour\"");
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
