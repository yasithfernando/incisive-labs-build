package com.example.incisivelabsbuild;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.util.converter.BigDecimalStringConverter;
import javafx.util.converter.IntegerStringConverter;

import java.io.IOException;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;

import static com.example.incisivelabsbuild.HelloApplication.details;
import static com.example.incisivelabsbuild.HelloApplication.stage;
import static logics.mathFunctions.*;

public class ProteinConcentrationPageController {
    public TableView<ProteinConcentrationData> proteinConcentrationTable;
    public Label tsMeanLabel;
    public Label tsSDILabel;
    public Label tsPercentLabel;
    public Label tsStockLabel;
    public Label tsSDIILabel;
    public Label tsOriginalStockLabel;
    public Label tsSDIIILabel;
    public Label tsRSDLabel;
    public Label rsMeanLabel;
    public Label rsSDILabel;
    public Label rsPercentLabel;
    public Label rsStockLabel;
    public Label rsSDIILabel;
    public Label rsOriginalStockLabel;
    public Label rsSDIIILabel;
    public Label rsRSDLabel;
    public BigDecimal testSampleMean;
    public BigDecimal testSampleStandardDeviation;
    public BigDecimal testSamplePercentage;
    public BigDecimal testSampleMgMlStock;
    public BigDecimal testSampleSDII;
    public BigDecimal testSampleMgMlOriginalStock;
    public BigDecimal testSampleSDIII;
    public BigDecimal testSampleRSD;
    public BigDecimal referenceSampleMean;
    public BigDecimal referenceSampleStandardDeviation;
    public BigDecimal referenceSamplePercentage;
    public BigDecimal referenceSampleMgMlStock;
    public BigDecimal referenceSampleSDII;
    public BigDecimal referenceSampleMgMlOriginalStock;
    public BigDecimal referenceSampleSDIII;
    public BigDecimal referenceSampleRSD;

    public void initialize() {
        //Set up the protein concentration table
        initializeProteinConcentrationTable();

        //map normalized to dilution table to protein concentration
        mapNormalisedTableToProteinConcentration();

        //Calculate Test Sample Mean, SD
        ArrayList<BigDecimal> tsBigDecimalArrayList = getTestSampleTotalValues();
        calculateTestSampleMeanAndStandardDeviation(tsBigDecimalArrayList.toArray(new BigDecimal[tsBigDecimalArrayList.size()]));

        //Calculate Reference Sample Mean, SD
        ArrayList<BigDecimal> rsBigDecimalArrayList = getReferenceSampleTotalValues();
        calculateReferenceSampleMeanAndStandardDeviation(rsBigDecimalArrayList.toArray(new BigDecimal[rsBigDecimalArrayList.size()]));

        //Calculate other derived values using test sample mean, sd and reference sample mean,sd
        calculateDerivedData();
    }

    private void calculateDerivedData() {
        //Test Sample Data
        BigDecimal tsPercentage =  testSampleMean.divide(referenceSampleMean, 5, RoundingMode.DOWN).multiply(new BigDecimal(100));
        testSamplePercentage = tsPercentage;
        tsPercentLabel.setText(tsPercentage.toString());

        testSampleMgMlStock = BigDecimal.valueOf(details.getTssfFinalConcentration_text()).multiply((testSamplePercentage.divide(BigDecimal.valueOf(100),3,RoundingMode.HALF_UP)));
        tsStockLabel.setText(testSampleMgMlStock.toString());

        testSampleSDII = testSampleMgMlStock.multiply((testSampleStandardDeviation.divide(testSampleMean,3,RoundingMode.HALF_UP)));
        tsSDIILabel.setText(testSampleSDII.toString());

        testSampleMgMlOriginalStock = BigDecimal.valueOf(details.dilutionTableTestSampleTotalVolume_text).multiply(
                (testSampleMgMlStock).divide(BigDecimal.valueOf(details.dilutionTableTestSampleVolumeOfSample_text),3,RoundingMode.HALF_UP));
        tsOriginalStockLabel.setText(testSampleMgMlOriginalStock.toString());

        testSampleSDIII = testSampleMgMlOriginalStock.multiply((testSampleStandardDeviation.divide(testSampleMean,3,RoundingMode.HALF_UP)));
        tsSDIIILabel.setText(testSampleSDIII.toString());

        testSampleRSD = BigDecimal.valueOf(100).multiply((testSampleStandardDeviation.divide(testSampleMean,3,RoundingMode.HALF_UP)));
        tsRSDLabel.setText(testSampleRSD.toString());

        //Reference Sample Data
        referenceSamplePercentage = referenceSampleMean.divide(referenceSampleMean, 5, RoundingMode.DOWN).multiply(new BigDecimal(100));
        rsPercentLabel.setText(referenceSamplePercentage.toString());

        referenceSampleMgMlStock = BigDecimal.valueOf(details.getRssfFinalConcentration_text()).multiply((referenceSamplePercentage.divide(BigDecimal.valueOf(100),3,RoundingMode.HALF_UP)));
        rsStockLabel.setText(referenceSampleMgMlStock.toString());

        referenceSampleSDII = referenceSampleMgMlStock.multiply((referenceSampleStandardDeviation.divide(referenceSampleMean,3,RoundingMode.HALF_UP)));
        rsSDIILabel.setText(referenceSampleSDII.toString());

        referenceSampleMgMlOriginalStock = BigDecimal.valueOf(details.dilutionTableReferenceStandardTotalVolume_text).multiply(
                (referenceSampleMgMlStock).divide(BigDecimal.valueOf(details.dilutionTableReferenceStandardVolumeOfSample_text),3,RoundingMode.HALF_UP));
        rsOriginalStockLabel.setText(referenceSampleMgMlOriginalStock.toString());

        referenceSampleSDIII = referenceSampleMgMlOriginalStock.multiply((referenceSampleStandardDeviation.divide(referenceSampleMean,3,RoundingMode.HALF_UP)));
        rsSDIIILabel.setText(referenceSampleSDIII.toString());

        referenceSampleRSD = BigDecimal.valueOf(100).multiply((referenceSampleStandardDeviation.divide(referenceSampleMean,3,RoundingMode.HALF_UP)));
        rsRSDLabel.setText(referenceSampleRSD.toString());

    }

    private void calculateTestSampleMeanAndStandardDeviation(BigDecimal[] testSampleData ) {
        //Calculate Mean and SD
        testSampleMean = calculateMeanBigDecimal(testSampleData);
        testSampleStandardDeviation = calculateStandardDeviationBigDecimal(testSampleData);

        //Set Mean and SD Labels
        tsMeanLabel.setText(testSampleMean.toString());
        tsSDILabel.setText(testSampleStandardDeviation.toString());
    }

    private void calculateReferenceSampleMeanAndStandardDeviation(BigDecimal[] referenceSampleData){
        //Calculate Mean and SD
        referenceSampleMean = calculateMeanBigDecimal(referenceSampleData);
        referenceSampleStandardDeviation = calculateStandardDeviationBigDecimal(referenceSampleData);

        //Set Mean and SD Labels
        rsMeanLabel.setText(referenceSampleMean.toString());
        rsSDILabel.setText(referenceSampleStandardDeviation.toString());
    }

    private ArrayList<BigDecimal> getTestSampleTotalValues(){
        ArrayList<BigDecimal> testSamplesTotalValues = new ArrayList<>();
        for (int i = 0; i < proteinConcentrationTable.getItems().size(); i++) {
            testSamplesTotalValues.add(proteinConcentrationTable.getItems().get(i).tsRepOne);
            testSamplesTotalValues.add(proteinConcentrationTable.getItems().get(i).tsRepTwo);
            testSamplesTotalValues.add(proteinConcentrationTable.getItems().get(i).tsRepThree);
        }
        return testSamplesTotalValues;
    }
    private ArrayList<BigDecimal> getReferenceSampleTotalValues(){
        ArrayList<BigDecimal> referenceSamplesTotalValues = new ArrayList<>();
        for (int i = 0; i < proteinConcentrationTable.getItems().size(); i++) {
            referenceSamplesTotalValues.add(proteinConcentrationTable.getItems().get(i).rsRepOne);
        }
        return referenceSamplesTotalValues;
    }

    private void mapNormalisedTableToProteinConcentration() throws NullPointerException, ArrayIndexOutOfBoundsException {
        System.out.println("at map method");
        System.out.println(MassCorrectionPageController.finalNormalisedToDilutionTable.getItems().get(0).getMonomer());
        System.out.println("get monomer passed");

        TableView<MassCorrectionPageController.NormalisedToDilutionData> normalisedData = MassCorrectionPageController.finalNormalisedToDilutionTable;
        //TODO map normalised data table to protein concentration table
        int numberOfRows = normalisedData.getItems().size();
        ArrayList<BigDecimal> concentrationOneTotalValues = new ArrayList<>();
        ArrayList<BigDecimal> concentrationTwoTotalValues = new ArrayList<>();
        ArrayList<BigDecimal> concentrationFourTotalValues = new ArrayList<>();
        for (MassCorrectionPageController.NormalisedToDilutionData row: normalisedData.getItems()) {
            if (row.getSampleName().contains("D3")){
                concentrationOneTotalValues.add(row.getTotal());
            }
            else if (row.getSampleName().contains("D2")){
                concentrationTwoTotalValues.add(row.getTotal());
            }
            else if(row.getSampleName().contains("D1")){
                concentrationFourTotalValues.add(row.getTotal());
            }
        }

        for (int i = 0; i < 3; i++) {
            if (i == 0){
                ProteinConcentrationData row = new ProteinConcentrationData(
                        1,
                        concentrationOneTotalValues.get(0),
                        concentrationOneTotalValues.get(1),
                        concentrationOneTotalValues.get(2),
                        concentrationOneTotalValues.get(3));
                proteinConcentrationTable.getItems().add(row);
            }
            else if (i == 1){
                ProteinConcentrationData row = new ProteinConcentrationData(
                        2,
                        concentrationTwoTotalValues.get(0),
                        concentrationTwoTotalValues.get(1),
                        concentrationTwoTotalValues.get(2),
                        concentrationTwoTotalValues.get(3));
                proteinConcentrationTable.getItems().add(row);
            }
            else {
                ProteinConcentrationData row = new ProteinConcentrationData(
                        4,
                        concentrationFourTotalValues.get(0),
                        concentrationFourTotalValues.get(1),
                        concentrationFourTotalValues.get(2),
                        concentrationFourTotalValues.get(3));
                proteinConcentrationTable.getItems().add(row);
            }

        }
    }

    private void initializeProteinConcentrationTable() {
        TableColumn<ProteinConcentrationPageController.ProteinConcentrationData, Integer> concentrationColumn = new TableColumn<>("concentration");
        concentrationColumn.setCellValueFactory(new PropertyValueFactory<>("concentration"));
        concentrationColumn.setCellFactory(TextFieldTableCell.forTableColumn(new IntegerStringConverter()));

        TableColumn<ProteinConcentrationPageController.ProteinConcentrationData, BigDecimal> tsRepOneColumn = new TableColumn<>("TS REP 1");
        tsRepOneColumn.setCellValueFactory(new PropertyValueFactory<>("tsRepOne"));
        tsRepOneColumn.setCellFactory(TextFieldTableCell.forTableColumn(new BigDecimalStringConverter()));

        TableColumn<ProteinConcentrationPageController.ProteinConcentrationData, BigDecimal> tsRepTwoColumn = new TableColumn<>("TS REP 2");
        tsRepTwoColumn.setCellValueFactory(new PropertyValueFactory<>("tsRepTwo"));
        tsRepTwoColumn.setCellFactory(TextFieldTableCell.forTableColumn(new BigDecimalStringConverter()));

        TableColumn<ProteinConcentrationPageController.ProteinConcentrationData, BigDecimal> tsRepThreeColumn = new TableColumn<>("TS REP 2");
        tsRepThreeColumn.setCellValueFactory(new PropertyValueFactory<>("tsRepThree"));
        tsRepThreeColumn.setCellFactory(TextFieldTableCell.forTableColumn(new BigDecimalStringConverter()));

        TableColumn<ProteinConcentrationPageController.ProteinConcentrationData, BigDecimal> rsRepOneColumn = new TableColumn<>("RS REP 1");
        rsRepOneColumn.setCellValueFactory(new PropertyValueFactory<>("rsRepOne"));
        rsRepOneColumn.setCellFactory(TextFieldTableCell.forTableColumn(new BigDecimalStringConverter()));
    }

    public void onResetButtonClick(ActionEvent actionEvent) throws IOException{

        FXMLLoader fxmlLoader = new FXMLLoader(DetailsPageTwoController.class.getResource(" mass-correction-page.fxml"));

        //Set the stage with the new scene
        Scene scene = new Scene(fxmlLoader.load(), 1178, 700);
        stage.setTitle("Incisive Lab");
        stage.setScene(scene);
        stage.show();

    }

    public void onNextButtonClick(ActionEvent actionEvent) throws IOException {
        //Add This Screens Data into Gel Run Object
        details.getGelRunArrayList().get(LaneContentsPageController.currentIterativeRunTime).setProteinConcentrationDataTableView(proteinConcentrationTable);

        details.getGelRunArrayList().get(LaneContentsPageController.currentIterativeRunTime).setTestSampleMean(testSampleMean);
        details.getGelRunArrayList().get(LaneContentsPageController.currentIterativeRunTime).setTestSampleStandardDeviation(testSampleStandardDeviation);
        details.getGelRunArrayList().get(LaneContentsPageController.currentIterativeRunTime).setTestSamplePercentage(testSamplePercentage);
        details.getGelRunArrayList().get(LaneContentsPageController.currentIterativeRunTime).setTestSampleMgMlStock(testSampleMgMlStock);
        details.getGelRunArrayList().get(LaneContentsPageController.currentIterativeRunTime).setTestSampleSDII(testSampleSDII);
        details.getGelRunArrayList().get(LaneContentsPageController.currentIterativeRunTime).setTestSampleMgMlOriginalSample(testSampleMgMlOriginalStock);
        details.getGelRunArrayList().get(LaneContentsPageController.currentIterativeRunTime).setTestSampleSDIII(testSampleSDIII);
        details.getGelRunArrayList().get(LaneContentsPageController.currentIterativeRunTime).setTestSampleRSD(testSampleRSD);

        details.getGelRunArrayList().get(LaneContentsPageController.currentIterativeRunTime).setReferenceSampleMean(referenceSampleMean);
        details.getGelRunArrayList().get(LaneContentsPageController.currentIterativeRunTime).setReferenceSampleStandardDeviation(referenceSampleStandardDeviation);
        details.getGelRunArrayList().get(LaneContentsPageController.currentIterativeRunTime).setReferenceSamplePercentage(referenceSamplePercentage);
        details.getGelRunArrayList().get(LaneContentsPageController.currentIterativeRunTime).setReferenceSampleMgMlStock(referenceSampleMgMlStock);
        details.getGelRunArrayList().get(LaneContentsPageController.currentIterativeRunTime).setReferenceSampleSDII(referenceSampleSDII);
        details.getGelRunArrayList().get(LaneContentsPageController.currentIterativeRunTime).setReferenceSampleMgMlOriginalSample(referenceSampleMgMlOriginalStock);
        details.getGelRunArrayList().get(LaneContentsPageController.currentIterativeRunTime).setReferenceSampleSDIII(referenceSampleSDIII);
        details.getGelRunArrayList().get(LaneContentsPageController.currentIterativeRunTime).setReferenceSampleRSD(referenceSampleRSD);



        FXMLLoader fxmlLoader = new FXMLLoader(MassCorrectionPageController.class.getResource("linearity-page.fxml"));

        //Set the stage with the new scene
        Scene scene = new Scene(fxmlLoader.load(), 1200,    800);
        stage.setTitle("Incisive Lab");
        stage.setScene(scene);
        stage.show();
    }

    public void onBackButtonClick(ActionEvent actionEvent)  throws IOException{
        FXMLLoader fxmlLoader = new FXMLLoader(DetailsPageTwoController.class.getResource("MassCorrectionPageController"));

        //Set the stage with the new scene
        Scene scene = new Scene(fxmlLoader.load(), 1178, 700);
        stage.setTitle("Incisive Lab");
        stage.setScene(scene);
        stage.show();

    }

    public void tssfFieldsUpdate(ActionEvent actionEvent) {
    }

    public static class ProteinConcentrationData {
        private int concentration;
        private BigDecimal tsRepOne;
        private BigDecimal tsRepTwo;
        private BigDecimal tsRepThree;
        private BigDecimal rsRepOne;

        public ProteinConcentrationData(int concentration, BigDecimal tsRepOne, BigDecimal tsRepTwo, BigDecimal tsRepThree, BigDecimal rsRepOne) {
            this.concentration = concentration;
            this.tsRepOne = tsRepOne;
            this.tsRepTwo = tsRepTwo;
            this.tsRepThree = tsRepThree;
            this.rsRepOne = rsRepOne;
        }

        public int getConcentration() {
            return concentration;
        }

        public void setConcentration(int concentration) {
            this.concentration = concentration;
        }

        public BigDecimal getTsRepOne() {
            return tsRepOne;
        }

        public void setTsRepOne(BigDecimal tsRepOne) {
            this.tsRepOne = tsRepOne;
        }

        public BigDecimal getTsRepTwo() {
            return tsRepTwo;
        }

        public void setTsRepTwo(BigDecimal tsRepTwo) {
            this.tsRepTwo = tsRepTwo;
        }

        public BigDecimal getTsRepThree() {
            return tsRepThree;
        }

        public void setTsRepThree(BigDecimal tsRepThree) {
            this.tsRepThree = tsRepThree;
        }

        public BigDecimal getRsRepOne() {
            return rsRepOne;
        }

        public void setRsRepOne(BigDecimal rsRepOne) {
            this.rsRepOne = rsRepOne;
        }
    }
}
