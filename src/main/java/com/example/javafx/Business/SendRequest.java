package com.example.javafx.Business;


import com.example.javafx.Config.Configuration;
import com.example.javafx.Model.Tour;
import org.json.JSONObject;


import java.io.*;

import java.net.URI;
import java.net.URL;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;


public class SendRequest {


    Configuration apiKey = Configuration.getInstance();
    private static Logger logger = LogManager.getLogger();

    public void sendRequest(Tour tour) throws IOException, InterruptedException {
        //Create initial GET request for a route and to fill first few param of requestInfo object
        URI resourceUrl = URI.create("https://www.mapquestapi.com/directions/v2/route?"
                + "key=" + apiKey.get("ApiKey")
                + "&from="
                + tour.getStart()
                + "&to="
                + tour.getDestin()
                + "&unit=k"
                + "&routeType="
                + tour.getType());
        URI resourceMapUrl = URI.create("https://www.mapquestapi.com/staticmap/v5/map?");
        HttpClient client = HttpClient.newBuilder().build();
        HttpRequest request = HttpRequest.newBuilder(resourceUrl).build();
            //Start parsin the response from MapQuest
        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
        JSONObject json = new JSONObject(response.body());
        JSONObject route =(JSONObject) json.get("route");
        JSONObject boundingbox = (JSONObject)route.get("boundingBox");

        JSONObject start = (JSONObject) boundingbox.get("lr");
        tour.setLrlng(start.get("lng").toString());
        tour.setLrlat(start.get("lat").toString());

        JSONObject goal = (JSONObject) boundingbox.get("ul");
        tour.setUllng(goal.get("lng").toString());
        tour.setUllat(goal.get("lat").toString());

        tour.setDistance(route.getDouble("distance"));

        tour.setTime(route.get("formattedTime").toString());

        tour.setSessionID(route.get("sessionId").toString());

        int imageZoom = 0;

        if(tour.getDistance() < 100){
            imageZoom = 11;
        }else {
            if (tour.getDistance() < 200) {
                imageZoom = 9;
            } else {
                if (tour.getDistance() < 600) {
                    imageZoom = 8;
                } else {
                    if (tour.getDistance() < 800) {
                        imageZoom = 7;
                    } else {
                        if (tour.getDistance() < 1300) {
                            imageZoom = 6;
                        } else {
                            if (tour.getDistance() < 2500) {
                                imageZoom = 4;
                            } else {
                                imageZoom = 3;
                            }
                        }
                    }
                }
            }
        }
        //Create Url used for Scenebuilder to display Map
        tour.setUrl(resourceMapUrl + "key="
                + apiKey.get("ApiKey")

                + "&size=1152,864"
                + "&defaultMarker=none&zoom=" + imageZoom + "&rand=737758036&session="
                + tour.getSessionID()
                + "&;boundingBox="

                + tour.getUllat()
                + "," + tour.getUllng()
                + "," + tour.getLrlat()
                + "," + tour.getLrlng());


        String destinationFile = "./Files/images/" + tour.getName() + ".jpg";

        URL url = new URL(tour.getUrl());
        InputStream is = url.openStream();
        OutputStream os = new FileOutputStream(destinationFile);

        byte[] r = new byte[2048];
        int length;

        while ((length = is.read(r)) != -1) {
            os.write(r, 0, length);
        }

        is.close();
        os.close();

        logger.info("Picture successfully saved");

    }

    }




