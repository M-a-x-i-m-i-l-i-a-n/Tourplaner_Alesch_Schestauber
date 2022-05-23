package com.example.javafx.tourcreator;


import java.net.URI;
import java.net.URISyntaxException;
import java.net.http.*;


public class SendRequest {




    public static RequestInfo wrapper(RequestInfo requestInfo) throws URISyntaxException {

        HttpRequest.newBuilder()
                .uri(new URI("http://www.mapquestapi.com/directions/v2/route?"))
                .version(HttpClient.Version.HTTP_2)
                .headers("key", "LAe151WHQH1plIyp4JnV8P1kUrg0Ksid")
                .headers("from", requestInfo.getStart())
                .headers("to", requestInfo.getDestination())
                .headers("unit", "k")
                .GET();

        /*HttpResponse<String> response = HttpClient.newHttpClient()
                .send(request, HttpResponse.BodyHandlers.ofString());
        Http responseHeaders = response.headers();*/




        return requestInfo;
    }



}
