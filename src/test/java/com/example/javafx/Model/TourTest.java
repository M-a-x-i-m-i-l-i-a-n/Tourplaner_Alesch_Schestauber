package com.example.javafx.Model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


import static org.junit.jupiter.api.Assertions.*;

class TourTest {
    private Tour tour;



    @BeforeEach
    void setUp() {
        tour = new Tour("TestTour","Tour for testing","Teststart","Testdestination","fastest","123.32","52.21314",11284.12,"32.23424","32.234224","43.234","q342978fhq34g7hq34g7h347g798herg","www.yourtest.com");

    }

    @Test
    void testsetSessionID() {
        tour.setSessionID("q342978fhq34g7hq34g7h347g798herg");
            assertEquals("q342978fhq34g7hq34g7h347g798herg", tour.getSessionID());

    }

    @Test
    void testSetTime(){
        try {
                tour.setTime("121.1");
            System.out.println(tour.getTime());
        }
        catch(IllegalArgumentException e){
            System.out.println("Properly caught invalid input in setDifficulty(): " + e.getMessage());
        }
    }

    @Test
    void testGetDescription(){
        assertEquals("Tour for testing", tour.getDescription());
    }

    @Test
    void testsetDescription() {
        tour.setDescription("Testing for Tour");
        assertEquals("Testing for Tour", tour.getDescription());

    }

    @Test
    void testgetStartandDestin(){
        assertEquals("Teststart", tour.getStart());
        assertEquals("Testdestination",tour.getDestin());
    }

    @Test
    void testsetStart() {
        tour.setStart("TestStartLocation");
        assertEquals("TestStartLocation", tour.getStart());

    }

    @Test
    void testsetDestin() {
        tour.setDestin("DestinationTest");
        assertEquals("DestinationTest", tour.getDestin());

    }

    @Test
    void testSetDistance(){
        tour.setDistance(2342.197);
        assertEquals(2342.197, tour.getDistance());
    }







}