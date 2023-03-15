package com.example.incisivelabsbuild;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

import javax.swing.*;
import java.io.IOException;

import static com.example.incisivelabsbuild.HelloApplication.*;

public class DetailsPageTwoController {
    public  TextField dilutionTableTestSampleVolumeOfSampleTxt;
    public  TextField dilutionTableTestSampleTotalVolumeTxt;
    public  TextField dilutionTableReferenceStandardVolumeOfSampleTxt;
    public  TextField dilutionTableReferenceStandardTotalVolumeTxt;
    public TextField numberOfGelRunsTxtnumberOfGelRunsTxt;

    //todo after ui changes take place
    public Button resetBtn;
    public Button nextBtn;
    public Button backBtn;
    public TextField tssfTotalVolumeTxt;
    public TextField tssfFinalConcentrationTxt;
    public TextField tssfEstimatedConcentrationOfTestSampleTxt;
    public Label tssfVolumeOfSampleLabel;
    public Label tssfVolumeOf2XRSOBLabel;
    public TextField rssfTotalVolumeTxt;
    public TextField rssfFinalConcentrationTxt;
    public TextField rssfConcentrationOfRefernceStndardTxt;
    public Label rssfVolumeOfSampleLabel;
    public Label rssfVolumeOf2XRSOBLabel;
    JPanel buttonPanel = new JPanel();

    public void tssfCalculations() {

        details.setTssfTotalVolume_text(Double.valueOf(tssfTotalVolumeTxt.getText()));
        details.setTssfFinalConcentration_text(Double.parseDouble(tssfFinalConcentrationTxt.getText()));
        details.setTssfEstimatedConcentrationOfTestSample_text(Double.valueOf(tssfEstimatedConcentrationOfTestSampleTxt.getText()));

        details.tssfVolumeOfSample_lbl = (details.tssfTotalVolume_text * details.tssfEstimatedConcentrationOfTestSample_text) / details.tssfFinalConcentration_text;
        details.tssfVolumeOf2XRSOB_lbl = 1000 - details.tssfVolumeOfSample_lbl;
        tssfVolumeOfSampleLabel.setText(String.valueOf(details.tssfVolumeOfSample_lbl));
        tssfVolumeOf2XRSOBLabel.setText(String.valueOf(details.tssfVolumeOf2XRSOB_lbl));
    }

    public void rssfCalculations() {

        details.rssfTotalVolume_text = Double.valueOf(rssfTotalVolumeTxt.getText());
        details.rssfFinalConcentration_text = Double.parseDouble(rssfFinalConcentrationTxt.getText());
        details.rssfConcentrationOfRefernceStndard_text = Double.valueOf(rssfConcentrationOfRefernceStndardTxt.getText());
        details.rssfVolumeOfSample_lbl = (details.rssfTotalVolume_text * details.rssfFinalConcentration_text) / details.rssfFinalConcentration_text;
        details.rssfVolumeOf2XRSOB_lbl = 1000 - details.rssfVolumeOfSample_lbl;

        tssfVolumeOfSampleLabel.setText(String.valueOf(details.rssfVolumeOfSample_lbl));
        tssfVolumeOf2XRSOBLabel.setText(String.valueOf(details.rssfVolumeOf2XRSOB_lbl));

    }

    public void initialize() {
//        Final Concentration needs to be updated based on Bovine haemoglobin or BlueCheck?
        if (("Bovine Haemoglobin").equals(details.bovine_or_globin)) {
            tssfFinalConcentrationTxt.setText("4");
            rssfFinalConcentrationTxt.setText("4");
        } else if (("BlueCheck").equals(details.bovine_or_globin)) {
            tssfFinalConcentrationTxt.setText("8");
            rssfFinalConcentrationTxt.setText("8");
        }
        numberOfGelRunsTxtnumberOfGelRunsTxt.setText(String.valueOf(details.numberOfGelRuns));


    }

    public void onNextButtonClick() throws IOException {
        backNavigation = false;
        details.dilutionTableTestSampleVolumeOfSample_text = Double.valueOf(dilutionTableTestSampleVolumeOfSampleTxt.getText());
        details.dilutionTableTestSampleTotalVolume_text = Double.valueOf(dilutionTableTestSampleTotalVolumeTxt.getText());
        details.dilutionTableReferenceStandardVolumeOfSample_text = Double.valueOf(dilutionTableReferenceStandardVolumeOfSampleTxt.getText());
        details.dilutionTableReferenceStandardTotalVolume_text = Double.valueOf(dilutionTableReferenceStandardTotalVolumeTxt.getText());
        details.numberOfGelRuns = Integer.valueOf(numberOfGelRunsTxtnumberOfGelRunsTxt.getText());
        details.tssfFinalConcentration_text = Double.valueOf(tssfFinalConcentrationTxt.getText());
        details.rssfFinalConcentration_text = Double.valueOf(rssfFinalConcentrationTxt.getText());

//        validations
        if (details.dilutionTableTestSampleVolumeOfSample_text <= 0){
            JOptionPane.showMessageDialog(buttonPanel, "Please enter a valid value for volume of sample in the dilution table ");
        } else if (details.dilutionTableTestSampleTotalVolume_text <= 0){
            JOptionPane.showMessageDialog(buttonPanel, "Please enter a valid value for total volume of sample in the dilution table ");
        }

        else if (details.dilutionTableReferenceStandardVolumeOfSample_text <= 0){
            JOptionPane.showMessageDialog(buttonPanel, "Please enter a valid value for sample volume of reference standard in the dilution table ");
        } else if (details.dilutionTableReferenceStandardTotalVolume_text <= 0){
            JOptionPane.showMessageDialog(buttonPanel, "Please enter a valid value for total volume of reference standard in the dilution table ");
        } else if (details.numberOfGelRuns== null ||numberofGelRuns_Text <= 0) {
            JOptionPane.showMessageDialog(buttonPanel, "Please enter a valid value for gel runs");

        } else {
            FXMLLoader fxmlLoader = new FXMLLoader(LaneContentsPageController.class.getResource("lane-contents-page.fxml"));
            Scene scene = new Scene(fxmlLoader.load(), 1178, 700);
            stage.setTitle("Incisive Lab");
            stage.setScene(scene);
            stage.show();
        }
    }
    public Boolean numberValidation(String str){
        try {
            Double.parseDouble(str);
//            is a number
            if (Double.parseDouble(str) <= 0){
                JOptionPane.showMessageDialog(buttonPanel, "Number Cannot be negative or null for " + str);

            }
            return true;
        } catch(NumberFormatException e){
//            get the name of the field
            JOptionPane.showMessageDialog(buttonPanel, "Please enter a valid number for " + str);
            return false;
        }
//        pass the input inside
    }


    public void tssfFieldsUpdate() {
        details.tssfEstimatedConcentrationOfTestSample_text = Double.valueOf(tssfEstimatedConcentrationOfTestSampleTxt.getText());

        if (details.tssfEstimatedConcentrationOfTestSample_text == null) {
            JOptionPane.showMessageDialog(buttonPanel, "Estimated concentration of Test Sample (mg/mL) cannot be empty ");
        } else if (details.tssfEstimatedConcentrationOfTestSample_text <= 0) {
            JOptionPane.showMessageDialog(buttonPanel, "Estimated concentration of Test Sample (mg/mL) cannot be empty ");
        } else if (details.tssfEstimatedConcentrationOfTestSample_text != null && details.tssfEstimatedConcentrationOfTestSample_text > 0) {
            details.tssfFinalConcentration_txt = Double.parseDouble(tssfFinalConcentrationTxt.getText());
            details.tssfEstimatedConcentrationOfTestSample_txt = Double.parseDouble(tssfEstimatedConcentrationOfTestSampleTxt.getText());
            details.tssfoutput = (1000 * details.tssfFinalConcentration_txt) / details.tssfEstimatedConcentrationOfTestSample_txt;
            tssfVolumeOfSampleLabel.setText(String.valueOf(details.tssfoutput));
            tssfVolumeOf2XRSOBLabel.setText(String.valueOf(1000 - details.tssfoutput));
        }
    }

    public void rssfFieldsUpdate() {
        //todo focus
        details.rssfConcentrationOfRefernceStndard_text = Double.valueOf(rssfConcentrationOfRefernceStndardTxt.getText());

        if (rssfConcentrationOfRefernceStndardTxt.getText() == null || rssfConcentrationOfRefernceStndardTxt.getText() == "") {
            JOptionPane.showMessageDialog(buttonPanel, "Concentration of Reference Standard (mg/mL) cannot be empty ");
        } else if (Integer.valueOf(rssfConcentrationOfRefernceStndardTxt.getText()) <= 0) {
            JOptionPane.showMessageDialog(buttonPanel, "Concentration of Reference Standard (mg/mL) cannot be Negative ");
        } else if (rssfConcentrationOfRefernceStndardTxt.getText() != null && Integer.valueOf(rssfConcentrationOfRefernceStndardTxt.getText()) > 0) {
            details.rssfFinalConcentration_txt = Double.parseDouble(rssfFinalConcentrationTxt.getText());
            details.rssfEstimatedConcentrationOfTestSample_txt = Double.parseDouble(rssfConcentrationOfRefernceStndardTxt.getText());
            details.rssfoutput = (1000 * details.rssfFinalConcentration_txt) / details.rssfEstimatedConcentrationOfTestSample_txt;
            rssfVolumeOfSampleLabel.setText(String.valueOf(details.rssfoutput));
            rssfVolumeOf2XRSOBLabel.setText(String.valueOf(1000 - details.rssfoutput));
        } else if (rssfConcentrationOfRefernceStndardTxt.getText() == null) {
            JOptionPane.showMessageDialog(buttonPanel, "Concentration of Reference Standard (mg/mL) cannot be empty ");
        } else if (Integer.valueOf(rssfConcentrationOfRefernceStndardTxt.getText()) <= 0) {
            JOptionPane.showMessageDialog(buttonPanel, "Concentration of Reference Standard (mg/mL) cannot be Negative ");
        }
    }


    public void onBackButtonClick(ActionEvent actionEvent) throws IOException {
        backNavigation = true;
        FXMLLoader fxmlLoader = new FXMLLoader(DetailsPageOneController.class.getResource("details-page-one.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 1178, 700);
        stage.setTitle("Incisive Lab");
        stage.setScene(scene);
        stage.show();

    }

}
