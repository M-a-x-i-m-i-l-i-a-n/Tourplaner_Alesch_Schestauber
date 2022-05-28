package com.example.javafx.tourcreator;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.Scanner;

public class TourSender {



    public static void main(String[] args) throws URISyntaxException, IOException, InterruptedException {
        Scanner start = new Scanner(System.in);
        Scanner dest = new Scanner(System.in);
        Scanner width = new Scanner(System.in);
        Scanner length = new Scanner(System.in);
        Scanner method = new Scanner(System.in);
        RequestInfo requestInfo = new RequestInfo();

        System.out.println("Start: ");
        requestInfo.setStart(start.nextLine());
        System.out.println("Destination: ");
        requestInfo.setDestination(dest.nextLine());
        System.out.println("Picture Size: ");
        System.out.println("Length");
        requestInfo.setLength(length.nextInt());
        System.out.println("Width");
        requestInfo.setWidth(width.nextInt());
        System.out.println("Choose travel method: pedestrian; bicycle; car( use fastest): ");
        requestInfo.setTravelMethod(method.nextLine());



        System.out.println("Your Start: " + requestInfo.getStart());
        System.out.println("Your Destination: " + requestInfo.getDestination());
        SendRequest client = new SendRequest();
        //client.sendRequest();
        client.sendRequestAsync(requestInfo);
        System.out.println("Your MapURL: " + requestInfo.getMapUrl());






    }


}
