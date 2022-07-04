package com.example.javafx.Model;

import com.example.javafx.Controller.TourController;
import com.example.javafx.TourApplication;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import javafx.stage.Stage;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.testfx.api.FxAssert;
import org.testfx.api.FxRobot;
import org.testfx.framework.junit5.ApplicationExtension;
import org.testfx.framework.junit5.Start;
import org.testfx.matcher.base.WindowMatchers;


import java.io.IOException;

import static org.junit.jupiter.api.Assertions.fail;

@ExtendWith(ApplicationExtension.class)
public class MainUITest {
    final static String BTN_TEXT = "+";
    final static String LABEL_TEXT_AFTER_CLICK = "Button was clicked!";
    final static String BTN_ID = "myButton";
    final static String BTN_CLASS = "button";

    private TourController controller;
    private static FxRobot robot;

    @BeforeAll
    public static void setup() {
        robot = new FxRobot();
        System.out.println("AAAAAAAAAAA");
    }

    @Start
    public void start(Stage stage) throws IOException {
        FXMLLoader loader = new FXMLLoader(TourApplication.class.getResource("main.fxml"));
        Parent root = loader.load();
        //This must happen AFTER loader.load()
        this.controller = loader.getController();
        stage.setScene(new Scene(root, 1000, 800));
        stage.show();
    }

    @Test
    void test_SearchFunction() throws InterruptedException {
        robot.clickOn("#textField");
        robot.sleep(1000);
        robot.write("T", 1000);
        robot.clickOn("#searchButton");
        robot.sleep(1000);
        ObservableList<Tour> tours2 = controller.tourList.getItems();

        for(int i = 0; i < tours2.size(); i++){
            if(!tours2.get(i).getName().contains("T")){
                fail();
            }
        }
    }

    @Test
    void test_AddTourButton(){
        robot.sleep(1000);
        robot.clickOn("#addButton");
        robot.sleep(1000);
        FxAssert.verifyThat(robot.window("Add Tour"), WindowMatchers.isShowing());
        robot.closeCurrentWindow();
    }

    @Test
    void test_EditTourButton(){
        robot.sleep(1000);
        robot.clickOn("#tourList");
        robot.type(KeyCode.UP);
        robot.type(KeyCode.UP);
        robot.type(KeyCode.UP);
        robot.type(KeyCode.UP);
        robot.type(KeyCode.UP);
        robot.type(KeyCode.UP);
        robot.type(KeyCode.UP);
        robot.type(KeyCode.UP);
        robot.type(KeyCode.UP);
        robot.type(KeyCode.UP);
        robot.type(KeyCode.UP);
        robot.type(KeyCode.UP);
        robot.sleep(100);

        String name = controller.tourList.getSelectionModel().getSelectedItem().getName();
        robot.clickOn("#editTour");

        robot.sleep(1000);
        FxAssert.verifyThat(robot.window("Edit Tour:" + name), WindowMatchers.isShowing());
        robot.sleep(1000);
        robot.closeCurrentWindow();
    }


    @Test
    void test_DeleteTourButton(){
        int count1 = controller.tourList.getItems().size();
        robot.sleep(1000);
        robot.clickOn("#tourList");
        robot.type(KeyCode.DOWN);
        robot.type(KeyCode.DOWN);
        robot.type(KeyCode.DOWN);
        robot.type(KeyCode.DOWN);
        robot.type(KeyCode.DOWN);
        robot.type(KeyCode.DOWN);
        robot.type(KeyCode.DOWN);
        robot.type(KeyCode.DOWN);
        robot.type(KeyCode.DOWN);
        robot.type(KeyCode.DOWN);
        robot.type(KeyCode.DOWN);
        robot.type(KeyCode.DOWN);
        robot.sleep(100);
        robot.clickOn("#removeButton");
        robot.sleep(3000);
        int count2 = controller.tourList.getItems().size();
        if(!(count2 < count1)){
            fail();
        }
    }

    @Test
    void test_AddTour(){
        robot.sleep(1000);
        robot.clickOn("#addButton");
        robot.sleep(1000);
        String name = "UnitTestTour";
        robot.clickOn("#name").write(name);
        robot.clickOn("#type");
        robot.type(KeyCode.DOWN);
        robot.type(KeyCode.ENTER);
        robot.sleep(1000);
        robot.type(KeyCode.TAB);
        robot.type(KeyCode.TAB);
        robot.type(KeyCode.TAB);
        robot.write("Wien");
        robot.type(KeyCode.TAB);
        robot.write("Graz");
        robot.type(KeyCode.TAB);
        robot.write("test");
        robot.sleep(1000);
        robot.clickOn("#saveTour");
        //robot.closeCurrentWindow();
        ObservableList<Tour> tours = controller.tourList.getItems();
        Boolean failed = true;
        for(int i = 0; i < tours.size(); i++){
            if(tours.get(i).getName().equals(name)){
                failed = false;
            }
        }
        if(failed){
            fail();
        }
    }


}
