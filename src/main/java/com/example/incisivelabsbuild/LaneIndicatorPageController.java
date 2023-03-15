package com.example.incisivelabsbuild;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.FileChooser;

import javax.swing.*;
import java.io.File;
import java.io.IOException;

import static com.example.incisivelabsbuild.HelloApplication.details;
import static com.example.incisivelabsbuild.HelloApplication.stage;

public class LaneIndicatorPageController {
    public Button resetBtn;
    public Button nextBtn;
    public Button backBtn;
    public ImageView gelImageViewer;
    public Button addGelImageButton;
    JPanel buttonPanel = new JPanel();


    public void onAddGelImageButtonClick(ActionEvent actionEvent) {
        FileChooser fileChooser = new FileChooser();
        FileChooser.ExtensionFilter imageFilter = new FileChooser.ExtensionFilter("Image files (*.jpg, *.png)", "*.jpg", "*.png");
        fileChooser.getExtensionFilters().add(imageFilter);
        File selectedFile = fileChooser.showOpenDialog(stage);
        if (selectedFile != null) {
            System.out.println("Selected file: " + selectedFile.getAbsolutePath());
            Image image = new Image(selectedFile.toURI().toString());
            gelImageViewer.setImage(image);
        }
    }
    public void onResetButtonClick(ActionEvent actionEvent) {

    }

    public void onNextButtonClick(ActionEvent actionEvent) throws IOException {
        HelloApplication.backNavigation = false;
        if (gelImageViewer.getImage() == null){
            JOptionPane.showMessageDialog(buttonPanel, "Please Upload an image of the gel run");
        }
        else {
            details.getGelRunArrayList().get(LaneContentsPageController.currentIterativeRunTime).setGelImage(gelImageViewer.getImage());
            FXMLLoader fxmlLoader = new FXMLLoader(RawDataPageController.class.getResource("raw-data-page.fxml"));

            //Set the stage with the new scene
            Scene scene = new Scene(fxmlLoader.load(), 1178, 700);
            stage.setTitle("Incisive Lab");
            stage.setScene(scene);
            stage.show();
        }

    }

    public void onBackButtonClick(ActionEvent actionEvent) throws IOException {
        HelloApplication.backNavigation = true;
        FXMLLoader fxmlLoader = new FXMLLoader(LaneContentsPageController.class.getResource("lane-contents-page.fxml"));

        //Set the stage with the new scene
        Scene scene = new Scene(fxmlLoader.load(), 1178, 700);
        stage.setTitle("Incisive Lab");
        stage.setScene(scene);
        stage.show();

    }


}
