package com.example.incisivelabsbuild;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;

import javax.swing.*;
import java.io.IOException;

import static com.example.incisivelabsbuild.HelloApplication.details;
import static com.example.incisivelabsbuild.HelloApplication.stage;

public class DetailsPageOneController {
    String title_text;
    String batch_number_text;
    String checkPoint_text;
    String analyst_text;
    String checked_text;
    String notes_text;
    static String bovine_or_globin;




    public TextField txtTitle;
    public TextField batchNumberText;
    public TextField checkpointTxt;
    public TextField analystTxt;
    public TextField checkerTxt;
    public TextArea notesTxt;
    public ComboBox bovineBlueCombo;
    public Button resetBtn;
    public Button nextBtn;

//    todo rename
    JPanel buttonPanel = new JPanel();

    @FXML
    public void onNextButtonClick() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(DetailsPageTwoController.class.getResource("details-page-two.fxml"));
        details.setTitle_text(txtTitle.getText());
        details.setBatch_number_text(batchNumberText.getText());
        details.setCheckPoint_text(checkpointTxt.getText());
        details.setAnalyst_text(analystTxt.getText());
        details.setChecked_text(checkerTxt.getText());
        details.setNotes_text(notesTxt.getText());
        details.setBovine_or_globin((String) bovineBlueCombo.getValue());

        if (inputValidater(details.title_text, "[a-zA-Z0-9 ]+")){
            JOptionPane.showMessageDialog(buttonPanel,"Invalid input for Title Text \n" + title_text);
        } else if (inputValidater(details.batch_number_text, "[a-zA-Z0-9 ]+")) {
            JOptionPane.showMessageDialog(buttonPanel,"Invalid input for Batch Number \n" + batch_number_text);
        } else if (inputValidater(details.checkPoint_text, "[a-zA-Z0-9 ]+")) {
            JOptionPane.showMessageDialog(buttonPanel, "Invalid input for checkpoint text \n" + checkPoint_text);
        } else if (inputValidater(details.analyst_text, "[a-zA-Z0-9 ]+")) {
            JOptionPane.showMessageDialog(buttonPanel,"Invalid input for analyst text \n" + analyst_text);
        } else if (inputValidater(details.checked_text, "[a-zA-Z0-9 ]+")) {
        JOptionPane.showMessageDialog(buttonPanel,"Invalid input for checked text \n" + checked_text);
        } else if (details.bovine_or_globin == null) {
            JOptionPane.showMessageDialog(buttonPanel, "Select either for Bovine haemoglobin or BlueCheck ");
        }else {
            Scene scene = new Scene(fxmlLoader.load(), 1178, 700);
            stage.setTitle("Incisive Lab");
            stage.setScene(scene);
            stage.show();
        }
    }
    public boolean inputValidater(String text,String regex) {
        return !text.matches(regex);
    }
}