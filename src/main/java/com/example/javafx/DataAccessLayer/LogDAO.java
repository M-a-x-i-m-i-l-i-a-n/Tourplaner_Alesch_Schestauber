package com.example.javafx.DataAccessLayer;

import com.example.javafx.Model.TourLog;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.*;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;



public class LogDAO {
    private static Logger logger = LogManager.getLogger();
    public static LogDAO instance;

    public static void checkIfTableExists(){
        try {
            Statement st = null;
            ResultSet rs = null;
            Connection conn = DatabaseHandler.getInstance().getConnection();
            DatabaseMetaData data = conn.getMetaData();
            ResultSet tables = data.getTables(null, null, "logs", null);
            if(!tables.next()){
                st = conn.createStatement();
                String qs ="CREATE TABLE IF NOT EXISTS public.logs\n" +
                        "(\n" +
                        "    id integer NOT NULL GENERATED ALWAYS AS IDENTITY ( INCREMENT 1 START 1 MINVALUE 1 MAXVALUE 10000 CACHE 1 ),\n" +
                        "    tourname text COLLATE pg_catalog.\"default\",\n" +
                        "    date text COLLATE pg_catalog.\"default\",\n" +
                        "    \"time\" text COLLATE pg_catalog.\"default\",\n" +
                        "    comment text COLLATE pg_catalog.\"default\",\n" +
                        "    difficulty integer,\n" +
                        "    totaltime double precision,\n" +
                        "    rating integer,\n" +
                        "    CONSTRAINT logs_pkey PRIMARY KEY (id)\n" +
                        ")\n" +
                        "\n" +
                        "TABLESPACE pg_default;\n" +
                        "\n" +
                        "ALTER TABLE IF EXISTS public.logs\n" +
                        "    OWNER to postgres;";
                rs = st.executeQuery(qs);
                conn.close();
            }
        }catch (Exception e){
            logger.debug(e);
        }
    }

    public static LogDAO getInstance() {
        if (LogDAO.instance == null) {
            LogDAO.instance = new LogDAO();
            checkIfTableExists();
        }
        return LogDAO.instance;
    }


    //Speichert ein Tourlog in der Datenbank
    public void createTourLog(String date, String time, String timeNeeded, String difficulty, String rating, String comment, String TourName) {
        try{

            Connection conn = DatabaseHandler.getInstance().getConnection();
            PreparedStatement statement = conn.prepareStatement("INSERT INTO public.\"logs\"(tourname, date, time, comment, difficulty, totaltime,rating) VALUES(?,?,?,?,?,?,?);");
            statement.setString(1, TourName);
            statement.setString(2, date);
            statement.setString(3, time);
            statement.setString(4, comment);
            statement.setInt(5, Integer.parseInt(difficulty));
            statement.setDouble(6, Double.parseDouble(timeNeeded));
            statement.setInt(7, Integer.parseInt(rating));

            statement.executeUpdate();

            statement.close();
            conn.close();
        } catch (Exception ex) {
            logger.debug(ex);
        }
    }

    public ObservableList<Integer> getLogIds(){
        ObservableList<Integer> ids = FXCollections.observableArrayList();
        try{
            Connection conn = DatabaseHandler.getInstance().getConnection();
            PreparedStatement statement = conn.prepareStatement("SELECT id FROM public.\"logs\";");
            ResultSet resultSet = statement.executeQuery();



            while (resultSet.next()){
                ids.add(resultSet.getInt(1));
            }
            return ids;
        }catch (SQLException e){
            e.printStackTrace();
            logger.debug(e);
        }
        return null;
    }

    //Updated ein Tourlog in der Datenabnk
    public void updateTourLog(TourLog log){
        try{
            Connection conn = DatabaseHandler.getInstance().getConnection();
            PreparedStatement statement = conn.prepareStatement("UPDATE public.\"logs\" SET tourname = ?, date = ?, " +
                    " time = ?, comment = ?, difficulty = ?, totaltime = ?, rating = ? WHERE id = ?;");
            statement.setString(1, log.getTourname());
            statement.setString(2, log.getDate());
            statement.setString(3, log.getTime());
            statement.setString(4, log.getComment());
            statement.setInt(5, log.getDifficulty());
            statement.setDouble(6, log.getTotalTime());
            statement.setInt(7, log.getRating());
            statement.setInt(8, log.getId());

            statement.executeUpdate();
            statement.close();
            conn.close();

        }catch (SQLException ex) {
            logger.debug(ex);
        }
    }

    //Diese Funktion gibt ein TourLog anhand der id zurück
    public TourLog getTourById(int id){
        try{
            Connection conn = DatabaseHandler.getInstance().getConnection();
            PreparedStatement statement = conn.prepareStatement("SELECT  tourname, date, time, comment, difficulty, totaltime,rating FROM public.\"logs\" WHERE id = ?;");
            statement.setInt(1, id);
            ResultSet resultSet = statement.executeQuery();


            if(resultSet.next()){
                TourLog log = new TourLog(id,resultSet.getString(1),
                        resultSet.getString(2),
                        resultSet.getString(3), resultSet.getString(4), resultSet.getInt(5), resultSet.getDouble(6), resultSet.getInt(7));
                resultSet.close();
                statement.close();
                conn.close();
                return log;
            }

        }catch (SQLException e){
            e.printStackTrace();
            logger.debug(e);
        }
        return null;
    }

    public ObservableList<TourLog> getTourLogsByTourname(String tourname){
        ObservableList<TourLog> logs = FXCollections.observableArrayList();
        TourLog log;
        try{
            Connection conn = DatabaseHandler.getInstance().getConnection();
            PreparedStatement statement = conn.prepareStatement("SELECT * FROM public.\"logs\" WHERE tourname = ?;");
            statement.setString(1, tourname);
            ResultSet resultSet = statement.executeQuery();



            while (resultSet.next()) {
                log = new TourLog(resultSet.getInt(1), resultSet.getString(2),
                        resultSet.getString(3),
                        resultSet.getString(4), resultSet.getString(5), resultSet.getInt(6), resultSet.getDouble(7), resultSet.getInt(8));
                logs.add(log);
            }
                resultSet.close();
                statement.close();
                conn.close();
                return logs;


        }catch (SQLException e){
            e.printStackTrace();
            logger.debug(e);
            return null;
        }
    }

    public void deleteTourLog(int id){
        try {
            Connection conn = DatabaseHandler.getInstance().getConnection();
            PreparedStatement statement = conn.prepareStatement("DELETE FROM public.\"logs\" WHERE id = ?;");
            statement.setInt(1, id);

            statement.executeUpdate();

            statement.close();
            conn.close();


        } catch (SQLException e) {
            e.printStackTrace();
            logger.debug(e);
        }
    }

    //Liefert die Anzahl der Tourlogs zurück
    public int getTourLogCount(){
        int rowcount = 0;
        try {
            Connection conn = DatabaseHandler.getInstance().getConnection();
            PreparedStatement statement = conn.prepareStatement("SELECT count(*) FROM public.\"logs\"");
            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {
                rowcount = resultSet.getInt(1);
            }
            statement.close();
            conn.close();

        } catch (SQLException e) {
            e.printStackTrace();
            logger.debug(e);
        }
        return rowcount;
    }
}
