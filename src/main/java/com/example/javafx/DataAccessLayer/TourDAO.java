package com.example.javafx.DataAccessLayer;

import com.example.javafx.model.Tour;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

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



        try{
            Connection conn = DatabaseHandler.getInstance().getConnection();
            PreparedStatement statement = conn.prepareStatement("INSERT INTO public.\"tours\"(name, description, start, destin, type, distance, \"time\", lrlng, lrlat, ullat, ullng, \"sessionID\", url) VALUES(?,?,?,?,?,?,?,?,?,?,?,?,?);");
            statement.setString(1, tour.getName());
            statement.setString(2, tour.getDescription());
            statement.setString(3, tour.getStart());
            statement.setString(4, tour.getDestin());
            statement.setString(5, tour.getType());
            statement.setDouble(6, tour.getDistance());
            statement.setString(7, tour.getTime());
            statement.setString(8, tour.getLrlat());
            statement.setString(9, tour.getLrlng());
            statement.setString(10, tour.getUllat());
            statement.setString(11, tour.getUllng());
            statement.setString(12, tour.getSessionID());
            statement.setString(13, tour.getUrl());


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
            PreparedStatement statement = conn.prepareStatement("SELECT  * FROM public.\"tours\" WHERE name = ?;");
            statement.setString(1, tourname);
            ResultSet resultSet = statement.executeQuery();

            if(resultSet.next()){
                Tour tour = new Tour(tourname,
                        resultSet.getString(2),
                        resultSet.getString(3),
                        resultSet.getString(4),
                        resultSet.getString(5),
                        resultSet.getString(6),
                        resultSet.getString(7),
                        resultSet.getDouble(8),
                        resultSet.getString(9),
                        resultSet.getString(10),
                        resultSet.getString(11),
                        resultSet.getString(12),
                        resultSet.getString(13));
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



            while (resultSet.next()){
                names.add(resultSet.getString(1));
            }
            return names;
        }catch (SQLException e){
            e.printStackTrace();
        }
        return null;
    }



    //Mit dieser Funktion werden die Daten einer Tour in der Datenbank updated
    public void updateTour(Tour tour){
        try{
            Connection conn = DatabaseHandler.getInstance().getConnection();
            PreparedStatement statement = conn.prepareStatement("UPDATE public.\"tours\" SET description = ?, start = ?, " +
                    " destin = ?, type = ?, distance = ?, time = ? WHERE name = ?;");
            statement.setString(1, tour.getDescription());

            statement.setString(2, tour.getStart());
            statement.setString(3, tour.getDestin());
            statement.setString(4, tour.getType());
            statement.setDouble(5, tour.getDistance());
            statement.setString(6, tour.getTime());
            statement.setString(7, tour.getName());

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
