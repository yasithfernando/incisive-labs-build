package com.example.incisivelabsbuild;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class HelloApplication extends Application {

    public static Stage stage;
    public static Double numberofGelRuns_Text = 1.0;

//    new details object per session
    public static Details details;

    public static int numberOfGelRuns;
    public static boolean backNavigation = false;


    @Override
    public void start(Stage stage) throws IOException {
        details = new Details();
        HelloApplication.stage = stage;
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("details-page-one.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 1178, 720);
        stage.setTitle("Incisive Lab");
        stage.setScene(scene);
        stage.show();
        System.out.println("At Hello Application End");

    }

    public static void main(String[] args) {
        launch();
    }
}

//math operations
