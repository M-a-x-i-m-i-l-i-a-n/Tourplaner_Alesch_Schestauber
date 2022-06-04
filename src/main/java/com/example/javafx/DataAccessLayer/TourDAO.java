package com.example.javafx.DataAccessLayer;

import com.example.javafx.model.Tour;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class TourDAO {

    public static TourDAO instance;

    public static TourDAO getInstance() {
        if (TourDAO.instance == null) {
            TourDAO.instance = new TourDAO();
        }
        return TourDAO.instance;
    }
    //TODO hier vielleicht noch logger hinzufügen

    //Mit dieser Funktion wird eine neue Tour in der Datenbank gespeichert
    public void createTour(Tour tour){
        //Die id gibt an die wievielte Tour das ist damit die Tours leichter ausgelesen und dann als Liste angezeigt werden können
        //Das + 1 ist weil über getTourCount() die Anzahl der gespeicherten Tours ausgelesen wird und die neu gespeicherte Tour ist dann um 1 mehr
        System.out.println("TOURHANDLER:44:: AAAAAAAAAAAAAAAAAAA");
        int id = getTourCount() + 1;
        System.out.println("TOURHANDLER:44:: AAAAAAAAAAAAAAAAAAA + id:" + id);
        try{
            Connection conn = DatabaseHandler.getInstance().getConnection();
            PreparedStatement statement = conn.prepareStatement("INSERT INTO public.\"tours\"(name, description, start, destin, type, distance, time, id) VALUES(?,?,?,?,?,?,?,?);");
            statement.setString(1, tour.getName());
            statement.setString(2, tour.getDescription());
            statement.setString(3, tour.getFrom());
            statement.setString(4, tour.getTo());
            statement.setString(5, tour.getType());
            statement.setInt(6, tour.getDistance());
            statement.setInt(7, tour.getTime());
            statement.setInt(8,id);

            statement.executeUpdate();
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
            PreparedStatement statement = conn.prepareStatement("SELECT  description, start, destin, type, distance, time, id FROM public.\"tours\" WHERE name = ?;");
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

    public ObservableList<String> getAllTourNames(){
        ObservableList<String> names = FXCollections.observableArrayList();
        try{
            Connection conn = DatabaseHandler.getInstance().getConnection();
            PreparedStatement statement = conn.prepareStatement("SELECT name FROM public.\"tours\";");
            ResultSet resultSet = statement.executeQuery();

            //User(String username, String password, String token, int coins, boolean admin)

            while (resultSet.next()){
                names.add(resultSet.getString(1));
            }
            return names;
        }catch (SQLException e){
            e.printStackTrace();
        }
        return null;
    }

    public Tour getTourById(int id){
        try{
            Connection conn = DatabaseHandler.getInstance().getConnection();
            PreparedStatement statement = conn.prepareStatement("SELECT  name, description, start, destin, type, distance, time FROM public.\"tours\" WHERE id = ?;");
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
            PreparedStatement statement = conn.prepareStatement("UPDATE public.\"tours\" SET description = ?, from = ?, " +
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
            PreparedStatement statement = conn.prepareStatement("DELETE FROM public.\"tours\" WHERE name = ?;");
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
            PreparedStatement statement = conn.prepareStatement("SELECT count(*) FROM public.\"tours\"");
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
