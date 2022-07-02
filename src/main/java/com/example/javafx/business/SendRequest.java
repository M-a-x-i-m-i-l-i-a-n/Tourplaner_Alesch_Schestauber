package com.example.javafx.business;


import com.example.javafx.config.Configuration;
import com.example.javafx.model.Tour;
import org.json.JSONObject;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;


public class SendRequest {


    Configuration apiKey = Configuration.getInstance();

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
        //Create Url used for Scenebuilder to display Map
        tour.setUrl(resourceMapUrl + "key="
                + apiKey.get("ApiKey")

                + "&size=1920,1080"
                + "&defaultMarker=none&zoom=9&rand=737758036&session="
                + tour.getSessionID()
                + "&;boundingBox="

                + tour.getUllat()
                + "," + tour.getUllng()
                + "," + tour.getLrlat()
                + "," + tour.getLrlng());

    }

    }




