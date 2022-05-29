package com.example.javafx.tourcreator;


import netscape.javascript.JSObject;
import org.json.JSONObject;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Collections;
import java.util.concurrent.CompletableFuture;

public class SendRequest {

    public String key = "cTGzOGnX1es4yVyz5vR6wuA1SxaT5tZ2";

    public void sendRequest(RequestInfo requestInfo) throws IOException, InterruptedException {
        URI resourceUrl = URI.create("http://www.mapquestapi.com/directions/v2/route?" + "key=cTGzOGnX1es4yVyz5vR6wuA1SxaT5tZ2" + "&from=" + requestInfo.getStart() + "&to=" + requestInfo.getDestination() + "&unit=k" + "&routeType=" + requestInfo.getTravelMethod());
        URI resourceMapUrl = URI.create("https://www.mapquestapi.com/staticmap/v5/map?");
        HttpClient client = HttpClient.newBuilder().build();
        HttpRequest request = HttpRequest.newBuilder(resourceUrl).build();
        System.out.println(request.toString());
        requestInfo.setMapUrl(resourceMapUrl + "start=" + requestInfo.getStart() + "&amp;end=" + requestInfo.getDestination() + "&amp;size=" + requestInfo.getLength() + "," + requestInfo.getWidth() + "&amp;key=" + key + "&amp;unit=k");
        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
        JSONObject json = new JSONObject(response.body());
        JSONObject route =(JSONObject) json.get("route");
        JSONObject boundingbox = (JSONObject)route.get("boundingBox");
        JSONObject start = (JSONObject) boundingbox.get("lr");
        String lng = start.get("lng").toString();
        String lat = start.get("lat").toString();


    }

    public void sendRequestAsync(RequestInfo requestInfo) throws IOException, InterruptedException {

        URI resourceUrl = URI.create("https://www.mapquestapi.com/directions/v2/route?");
        HttpClient client = HttpClient.newBuilder().build();
        HttpRequest request = HttpRequest.newBuilder(resourceUrl)
                .version(HttpClient.Version.HTTP_2)
                .headers("key", key)
                .headers("from", requestInfo.getStart())
                .headers("to", requestInfo.getDestination())
                .headers("unit", "k")
                .headers("routeType", requestInfo.getTravelMethod())
                .GET()
                .build();
//TODO: Actually read Response Body, parse for distance and time(formatted/non formatted), save to requestInfo, also put routeType into map url
        requestInfo.setMapUrl(resourceUrl + "start=" + requestInfo.getStart() + "&amp;end=" + requestInfo.getDestination() + "&amp;size=" + requestInfo.getLength() + "," + requestInfo.getWidth() + "&amp;key=" + key + "&amp;unit=k");

        CompletableFuture<HttpResponse<String>> future =
                client.sendAsync(request, HttpResponse.BodyHandlers.ofString());
        future.thenApply(response -> response.body()).thenAccept(imagedata ->{
            try {
                Files.write(Paths.get("image1.jpg"), Collections.singleton(imagedata));
                System.out.println("File has been written");
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
        future.join();
    }



/*

    public static RequestInfo wrapper(RequestInfo requestInfo) throws URISyntaxException {
        ObjectMapper mapper = new ObjectMapper();
        try {
        HttpRequest request = (HttpRequest) HttpRequest.newBuilder()
                .uri(new URI("http://www.mapquestapi.com/directions/v2/route?"))
                .version(HttpClient.Version.HTTP_2)
                .headers("key", "LAe151WHQH1plIyp4JnV8P1kUrg0Ksid")
                .headers("from", requestInfo.getStart())
                .headers("to", requestInfo.getDestination())
                .headers("unit", "k")
                .GET();
        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString())




    } catch(Exception e) {
        e.printStackTrace();
        }

        /*HttpResponse<String> response = HttpClient.newHttpClient()
                .send(request, HttpResponse.BodyHandlers.ofString());
        Http responseHeaders = response.headers();*/




       // return requestInfo;
    }




