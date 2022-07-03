package com.example.javafx.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class TourTest {

    private Tour tour;
    private TourLog log;

    @BeforeEach
    void setUp() {
        tour = new Tour("TestTour","Tour for testing","Teststart","Testdestination","fastest","123.32","52.21314",11284.12,"32.23424","32.234224","43.234","q342978fhq34g7hq34g7h347g798herg","www.yourtest.com");
        log = new TourLog(654694,"TestTour","3.7.2022","12:11","This is a test comment", 4, 14.124,3);
    }

    @Test
    void getSessionID() {
    }

    @Test
    void getLogs() {
    }
}