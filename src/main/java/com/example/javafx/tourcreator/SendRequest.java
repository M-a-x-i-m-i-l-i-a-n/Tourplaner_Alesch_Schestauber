package com.example.javafx.tourcreator;


import com.example.javafx.config.Configuration;
import org.json.JSONObject;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;


public class SendRequest {


    Configuration apiKey = Configuration.getInstance();

    public void sendRequest(RequestInfo requestInfo) throws IOException, InterruptedException {
        //Create initial GET request for a route and to fill first few param of requestInfo object
        URI resourceUrl = URI.create("https://www.mapquestapi.com/directions/v2/route?"
                + "key=cTGzOGnX1es4yVyz5vR6wuA1SxaT5tZ2"
                + "&from="
                + requestInfo.getStart()
                + "&to="
                + requestInfo.getDestination()
                + "&unit=k"
                + "&routeType="
                + requestInfo.getTravelMethod());
        URI resourceMapUrl = URI.create("https://www.mapquestapi.com/staticmap/v5/map?");
        HttpClient client = HttpClient.newBuilder().build();
        HttpRequest request = HttpRequest.newBuilder(resourceUrl).build();
            //Start parsin the response from MapQuest
        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
        JSONObject json = new JSONObject(response.body());
        JSONObject route =(JSONObject) json.get("route");
        JSONObject boundingbox = (JSONObject)route.get("boundingBox");

        JSONObject start = (JSONObject) boundingbox.get("lr");
        requestInfo.setLrlng(start.get("lng").toString());
        requestInfo.setLrlat(start.get("lat").toString());

        JSONObject goal = (JSONObject) boundingbox.get("ul");
        requestInfo.setUllng(goal.get("lng").toString());
        requestInfo.setUllat(goal.get("lat").toString());

        requestInfo.setDistance(route.getDouble("distance"));

        requestInfo.setTravTime(route.get("formattedTime").toString());

        requestInfo.setSessionID(route.get("sessionId").toString());
        //Create Url used for Scenebuilder to display Map
        requestInfo.setMapUrl(resourceMapUrl + "key="
                + apiKey.get("ApiKey")
                + "&amp;size=" + requestInfo.getLength()
                + "," + requestInfo.getWidth()
                + "&amp;defaultMarker=none&amp;zoom=11&amp;rand=737758036&amp;session="
                + requestInfo.getSessionID()
                + "&amp;boundingBox="
                + requestInfo.getUllat()
                + "," + requestInfo.getUllng()
                + "," + requestInfo.getLrlat()
                + "," + requestInfo.getLrlng());


    }

    }




