package com.example.incisivelabsbuild;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.util.converter.DoubleStringConverter;

import javax.swing.*;
import java.io.IOException;

import static com.example.incisivelabsbuild.HelloApplication.details;
import static com.example.incisivelabsbuild.HelloApplication.stage;

public class RawDataPageController {
    public Button addRowButton;
    public Button removeRowButton;
    public TextField sampleNameTxt;
    public Button resetBtn;
    public Button nextBtn;
    public Button backBtn;
    public TableView<RawData> rawDataTable;

    public static TableView<RawData> finalisedRawDataTable;
    public TextField monomerText;
    public TextField dimerText;
    public TextField trimerText;
    public TextField tretramerText;
    JPanel buttonPanel = new JPanel();


    public void initialize(){
        // Set up the table columns
        TableColumn<RawDataPageController.RawData, String> sampleColumn = new TableColumn<>("Sample");
        sampleColumn.setCellValueFactory(new PropertyValueFactory<>("sample"));
        sampleColumn.setCellFactory(TextFieldTableCell.forTableColumn());

        TableColumn<RawDataPageController.RawData, Double> monomerColumn = new TableColumn<>("Monomer 14 kDa");
        monomerColumn.setCellValueFactory(new PropertyValueFactory<>("monomer"));
        monomerColumn.setCellFactory(TextFieldTableCell.forTableColumn(new DoubleStringConverter()));

        TableColumn<RawDataPageController.RawData, Double> dimerColumn = new TableColumn<>("Dimer 14 kDa");
        dimerColumn.setCellValueFactory(new PropertyValueFactory<>("dimer"));
        dimerColumn.setCellFactory(TextFieldTableCell.forTableColumn(new DoubleStringConverter()));

        TableColumn<RawDataPageController.RawData, Double> trimerColumn = new TableColumn<>("Trimer 14 kDa");
        trimerColumn.setCellValueFactory(new PropertyValueFactory<>("trimer"));
        trimerColumn.setCellFactory(TextFieldTableCell.forTableColumn(new DoubleStringConverter()));

        TableColumn<RawDataPageController.RawData, Double> tretramerColumn = new TableColumn<>("Tretramer 14 kDa");
        tretramerColumn.setCellValueFactory(new PropertyValueFactory<>("tretramer"));
        tretramerColumn.setCellFactory(TextFieldTableCell.forTableColumn(new DoubleStringConverter()));

        addSampleData();
    }

    private void addSampleData() {
        // Add some sample data to the table
        rawDataTable.getItems().add(new RawDataPageController.RawData("TS D3",22033.898, 14276.46, 2803.54, 1470.62));
        rawDataTable.getItems().add(new RawDataPageController.RawData("TS D2",39888.63, 28092.94, 4658.97, 1928.539));
        rawDataTable.getItems().add(new RawDataPageController.RawData("TS D1",62363.19,54456.53,10109.53,4269.05));
        rawDataTable.getItems().add(new RawDataPageController.RawData("TS D3",22459.33,13955.56,3358.20,2573.49));
        rawDataTable.getItems().add(new RawDataPageController.RawData("TS D2",37795.12,27066.38,4721.10,2733.90));
        rawDataTable.getItems().add(new RawDataPageController.RawData("TS D1",62665.48,53016.58,10025.82,4350.19));
        rawDataTable.getItems().add(new RawDataPageController.RawData("TS D3",17195.26,16472.56,3116.54,1927.15));
        rawDataTable.getItems().add(new RawDataPageController.RawData("TS D2",37297.33,28494.63,5440.63,3169.32));
        rawDataTable.getItems().add(new RawDataPageController.RawData("TS D1",65684.06,55832.84,9938.89,4723.19));
        rawDataTable.getItems().add(new RawDataPageController.RawData("RS D3",20406.38,11704.61,3135.35,2528.47));
        rawDataTable.getItems().add(new RawDataPageController.RawData("RS D2",35432.66,23007.36,5842.53,2758.81));
        rawDataTable.getItems().add(new RawDataPageController.RawData("RS D1",59902.75,43333.80,11136.84,6217.63));

    }

    public void addRow(ActionEvent actionEvent) {
        if (sampleNameTxt.getText() != null && monomerText.getText() != null && dimerText.getText() != null && trimerText.getText() != null && tretramerText.getText() != null){
//            if (Double.parseDouble(monomerText.getText()) <= 0 ){
//
//            if (Double.parseDouble(monomerText.getText()) <= 0 ){
//                JOptionPane.showMessageDialog(buttonPanel, "monomer Value Cannot be negative or 0");
//            }
//            else if (Double.parseDouble(dimerText.getText()) <= 0 ){
//                JOptionPane.showMessageDialog(buttonPanel, "monomer Value Cannot be negative or 0");
//            }
//            else if (Double.parseDouble(trimerText.getText()) <= 0 ){
//                JOptionPane.showMessageDialog(buttonPanel, "monomer Value Cannot be negative or 0");
//            }
//            else if (Double.parseDouble(tretramerText.getText()) <= 0 ){
//                JOptionPane.showMessageDialog(buttonPanel, "monomer Value Cannot be negative or 0");
//            } else {
            try {
                rawDataTable.getItems().add(new RawData(
                        sampleNameTxt.getText(),
                        Double.parseDouble(monomerText.getText()),
                        Double.parseDouble(dimerText.getText()),
                        Double.parseDouble(trimerText.getText()),
                        Double.parseDouble(tretramerText.getText())
                ));
                sampleNameTxt.clear();
                monomerText.clear();
                dimerText.clear();
                trimerText.clear();
                tretramerText.clear();
            } catch (NumberFormatException e) {
                e.printStackTrace();
                throw new RuntimeException(e);
            }
        }
//        }
    }

    public void removeRow(ActionEvent actionEvent) {
        // Remove the selected row from the table
        int selectedIndex = rawDataTable.getSelectionModel().getSelectedIndex();
        if (selectedIndex >= 0) {
            rawDataTable.getItems().remove(selectedIndex);
        }
    }

    public void onResetButtonClick(ActionEvent actionEvent) {
    }

    public void onNextButtonClick(ActionEvent actionEvent) throws IOException {
        HelloApplication.backNavigation = false;
        /*added finalisedRawDataTable object inorder to handle static limitation on original table.
        * TLDR - don't change this line :D*/
        finalisedRawDataTable = rawDataTable;

        //Add Raw Data Table to Gel Run object
        details.getGelRunArrayList().get(LaneContentsPageController.currentIterativeRunTime).setRawDataTableView(finalisedRawDataTable);

        FXMLLoader fxmlLoader = new FXMLLoader(MassCorrectionPageController.class.getResource("mass-correction-page.fxml"));

        //Set the stage with the new scene
        Scene scene = new Scene(fxmlLoader.load(), 1178, 700);
        stage.setTitle("Incisive Lab");
        stage.setScene(scene);
        stage.show();
    }

    public void onBackButtonClick(ActionEvent actionEvent) throws IOException {
        HelloApplication.backNavigation = true;
        FXMLLoader fxmlLoader = new FXMLLoader(LaneIndicatorPageController.class.getResource("lane-indicator-page.fxml"));

        //Set the stage with the new scene
        Scene scene = new Scene(fxmlLoader.load(), 1178, 750);
        stage.setTitle("Incisive Lab");
        stage.setScene(scene);
        stage.show();
    }


    public static class RawData{
        private String sampleName;
        private double monomer;
        private double dimer;
        private double trimer;
        private double tretramer;

        public RawData(String sampleName, double monomer, double dimer, double trimer, double tretramer) {
            this.sampleName = sampleName;
            this.monomer = monomer;
            this.dimer = dimer;
            this.trimer = trimer;
            this.tretramer = tretramer;
        }

        public String getSampleName() {
            return sampleName;
        }

        public void setSampleName(String sampleName) {
            this.sampleName = sampleName;
        }

        public double getMonomer() {
            return monomer;
        }

        public void setMonomer(double monomer) {
            this.monomer = monomer;
        }

        public double getDimer() {
            return dimer;
        }

        public void setDimer(double dimer) {
            this.dimer = dimer;
        }

        public double getTrimer() {
            return trimer;
        }

        public void setTrimer(double trimer) {
            this.trimer = trimer;
        }

        public double getTretramer() {
            return tretramer;
        }

        public void setTretramer(double tretramer) {
            this.tretramer = tretramer;
        }
    }
}
