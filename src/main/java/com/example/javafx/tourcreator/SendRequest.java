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
import java.util.concurrent.CompletableFuture;

public class SendRequest {






    private void sendRequest() throws IOException, InterruptedException {
        URI resourceUrl = URI.create("https://www.postman-echo.com/get");
        HttpClient client = HttpClient.newBuilder().build();
        HttpRequest request = HttpRequest.newBuilder(resourceUrl).build();
        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
        JSONObject json = new JSONObject(response.body());
        String pretty = json.getJSONObject("headers").toString(4);
        System.out.println(pretty);
    }

    public void sendRequestAsync(RequestInfo requestInfo) throws IOException, InterruptedException {

        URI resourceUrl = URI.create("www.mapquestapi.com/directions/v2/route?");
        HttpClient client = HttpClient.newBuilder().build();
        HttpRequest request = HttpRequest.newBuilder(resourceUrl)
                .version(HttpClient.Version.HTTP_2)
                .headers("key", "LAe151WHQH1plIyp4JnV8P1kUrg0Ksid")
                .headers("from", requestInfo.getStart())
                .headers("to", requestInfo.getDestination())
                .headers("unit", "k")
                .GET()
                .build();
        CompletableFuture<HttpResponse<byte[]>> future =
                client.sendAsync(request, HttpResponse.BodyHandlers.ofByteArray());
        future.thenApply(response -> response.body()).thenAccept(imagedata ->{
            try {
                Files.write(Paths.get("image.jpg"), imagedata);
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




