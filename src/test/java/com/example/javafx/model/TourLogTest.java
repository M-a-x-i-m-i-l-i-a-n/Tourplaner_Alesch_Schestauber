package com.example.javafx.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class TourLogTest {
    private TourLog log;
    @BeforeEach
    void setUp() {
        log = new TourLog(654694,"TestTour","3.7.2022","12:11","This is a test comment", 4, 12.42,3);
    }

    @Test
    void testSetDifficulty(){
        System.out.println(log.getDifficulty());
        log.setDifficulty(5);
        if(log.getDifficulty() == 5){
            System.out.println("Properly set new Difficulty");
        }else{
            fail();
        }

    }

    @Test
    void testSetComment(){
        System.out.println(log.getComment());
        log.setComment("This is another test comment");
        assertEquals("This is another test comment", log.getComment());

    }

    @Test
    void testGetDate(){
        assertNotEquals("Tree", log.getDate());
    }

    @Test
    void testSetTotalTime(){
        System.out.println(log.getTotalTime());
        try{
            log.setTotalTime(16.13);
        }catch (Exception e){
            e.printStackTrace();
            fail();
        }
    }

    @Test
    void testSetRating(){
        System.out.println(log.getRating());
        log.setRating(4);
        assertEquals(4, log.getRating());
    }

    @Test
    void testGetTourname(){
        assertNotEquals("Bruh dis da wrong name!", log.getTourname());
    }

    @Test
    void testGetId(){
        assertEquals(654694, log.getId());
    }

}