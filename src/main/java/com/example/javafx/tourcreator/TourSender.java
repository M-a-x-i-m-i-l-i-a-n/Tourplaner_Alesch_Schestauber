package com.example.javafx.tourcreator;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.Scanner;

public class TourSender {



    public static void main(String[] args) throws URISyntaxException, IOException, InterruptedException {
        Scanner start = new Scanner(System.in);
        Scanner dest = new Scanner(System.in);
        Scanner dist = new Scanner(System.in);
        RequestInfo requestInfo = new RequestInfo();

        System.out.println("Start: ");
        requestInfo.setStart(start.nextLine());
        System.out.println("Destination: ");
        requestInfo.setDestination(dest.nextLine());
        System.out.println("Distance: ");
        requestInfo.setDistance(dist.nextDouble());



        System.out.println("Your Start: " + requestInfo.getStart());
        System.out.println("Your Destination: " + requestInfo.getDestination());
        System.out.println("Your Distance: " + requestInfo.getDistance());


        SendRequest client = new SendRequest();
        client.sendRequestAsync(requestInfo);



    }


}
