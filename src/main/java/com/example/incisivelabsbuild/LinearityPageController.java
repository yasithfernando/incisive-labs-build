package com.example.incisivelabsbuild;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.util.converter.BigDecimalStringConverter;
import javafx.util.converter.IntegerStringConverter;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import static com.example.incisivelabsbuild.HelloApplication.details;
import static com.example.incisivelabsbuild.HelloApplication.stage;
import static logics.mathFunctions.addTrendLine;

public class LinearityPageController {

    public TableView<BandIntensityData> bandIntensityTable;
    public LineChart<Double,BigDecimal> tsRepOneChart;
    public LineChart<Double,BigDecimal> tsRepTwoChart;
    public LineChart<Double,BigDecimal> tsRepThreeChart;
    public LineChart<Double,BigDecimal> rsRepOneChart;

    public void initialize(){
        initialiseBandIntensityTable();
        mapMassCorrectionToBandIntensity();
        initializeLineCharts();
    }

    private void initializeLineCharts() {
        List<DataPoint> tsRepOneDataPoints = new ArrayList<>();
        List<DataPoint> tsRepTwoDataPoints = new ArrayList<>();
        List<DataPoint> tsRepThreeDataPoints = new ArrayList<>();
        List<DataPoint> rsRepOneDataPoints = new ArrayList<>();

        for (BandIntensityData row : bandIntensityTable.getItems()) {
            tsRepOneDataPoints.add(new DataPoint(row.getConcentration(),row.getTsRepOne()));
            tsRepTwoDataPoints.add(new DataPoint(row.getConcentration(),row.getTsRepTwo()));
            tsRepThreeDataPoints.add(new DataPoint(row.getConcentration(),row.getTsRepThree()));
            rsRepOneDataPoints.add(new DataPoint(row.getConcentration(),row.getRsRepOne()));
        }

        populateLineChart(tsRepOneChart,tsRepOneDataPoints);
        populateLineChart(tsRepTwoChart,tsRepTwoDataPoints);
        populateLineChart(tsRepThreeChart,tsRepThreeDataPoints);
        populateLineChart(rsRepOneChart,rsRepOneDataPoints);


    }

    private void populateLineChart(LineChart<Double,BigDecimal> chart,List<DataPoint> dataPoints){
        XYChart.Series<Double, BigDecimal> series = new XYChart.Series<>();
        XYChart.Series<Double, BigDecimal> trendLineSeries = new XYChart.Series<>();
        for (DataPoint point : dataPoints) {
            series.getData().add(new XYChart.Data<Double,BigDecimal>(point.getProteinConcentration(), point.getAuc()));
        }
        chart.getData().add(series);
        trendLineSeries = addTrendLine(series);
        chart.getData().add(trendLineSeries);

        series.getNode().setStyle("-fx-stroke: transparent; -fx-stroke-width: 0;");
        trendLineSeries.getNode().setStyle("-fx-stroke: #0073ff; -fx-stroke-width: 1; -fx-stroke-dash-array: 7 4;");
        for (XYChart.Data<Double, BigDecimal> dataPoint : trendLineSeries.getData()) {
            Node node = dataPoint.getNode();
            node.setStyle("-fx-background-color: transparent, transparent;");
        }

    }

    public void initialiseBandIntensityTable(){
        TableColumn<LinearityPageController.BandIntensityData, Integer> concentrationColumn = new TableColumn<>("concentration");
        concentrationColumn.setCellValueFactory(new PropertyValueFactory<>("concentration"));
        concentrationColumn.setCellFactory(TextFieldTableCell.forTableColumn(new IntegerStringConverter()));

        TableColumn<LinearityPageController.BandIntensityData, BigDecimal> tsRepOneColumn = new TableColumn<>("TS REP 1");
        tsRepOneColumn.setCellValueFactory(new PropertyValueFactory<>("tsRepOne"));
        tsRepOneColumn.setCellFactory(TextFieldTableCell.forTableColumn(new BigDecimalStringConverter()));

        TableColumn<LinearityPageController.BandIntensityData, BigDecimal> tsRepTwoColumn = new TableColumn<>("TS REP 2");
        tsRepTwoColumn.setCellValueFactory(new PropertyValueFactory<>("tsRepTwo"));
        tsRepTwoColumn.setCellFactory(TextFieldTableCell.forTableColumn(new BigDecimalStringConverter()));

        TableColumn<LinearityPageController.BandIntensityData, BigDecimal> tsRepThreeColumn = new TableColumn<>("TS REP 2");
        tsRepThreeColumn.setCellValueFactory(new PropertyValueFactory<>("tsRepThree"));
        tsRepThreeColumn.setCellFactory(TextFieldTableCell.forTableColumn(new BigDecimalStringConverter()));

        TableColumn<LinearityPageController.BandIntensityData, BigDecimal> rsRepOneColumn = new TableColumn<>("RS REP 1");
        rsRepOneColumn.setCellValueFactory(new PropertyValueFactory<>("rsRepOne"));
        rsRepOneColumn.setCellFactory(TextFieldTableCell.forTableColumn(new BigDecimalStringConverter()));
    }

    private void mapMassCorrectionToBandIntensity() throws NullPointerException, ArrayIndexOutOfBoundsException {
        TableView<MassCorrectionPageController.MassCorrectionData> massCorrectionData = MassCorrectionPageController.finalMassCorrectionTable;
        ArrayList<BigDecimal> concentrationOneTotalProteinValues = new ArrayList<>();
        ArrayList<BigDecimal> concentrationTwoTotalProteinValues = new ArrayList<>();
        ArrayList<BigDecimal> concentrationFourTotalProteinValues = new ArrayList<>();
        //TODO map normalised data table to protein concentration table
        int numberOfRows = massCorrectionData.getItems().size();
        for (MassCorrectionPageController.MassCorrectionData row: massCorrectionData.getItems()) {
            if (row.getSampleName().contains("D3")){
                concentrationOneTotalProteinValues.add(row.getTotalProtein());
            }
            else if (row.getSampleName().contains("D2")){
                concentrationTwoTotalProteinValues.add(row.getTotalProtein());
            }
            else if(row.getSampleName().contains("D1")){
                concentrationFourTotalProteinValues.add(row.getTotalProtein());
            }
        }

        for (int i = 0; i < 3; i++) {
            if (i == 0){
                LinearityPageController.BandIntensityData row = new LinearityPageController.BandIntensityData(
                        1,
                        concentrationOneTotalProteinValues.get(0),
                        concentrationOneTotalProteinValues.get(1),
                        concentrationOneTotalProteinValues.get(2),
                        concentrationOneTotalProteinValues.get(3));
                bandIntensityTable.getItems().add(row);
            }
            else if (i == 1){
                LinearityPageController.BandIntensityData row = new LinearityPageController.BandIntensityData(
                        2,
                        concentrationTwoTotalProteinValues.get(0),
                        concentrationTwoTotalProteinValues.get(1),
                        concentrationTwoTotalProteinValues.get(2),
                        concentrationTwoTotalProteinValues.get(3));
                bandIntensityTable.getItems().add(row);
            }
            else {
                LinearityPageController.BandIntensityData row = new LinearityPageController.BandIntensityData(
                        4,
                        concentrationFourTotalProteinValues.get(0),
                        concentrationFourTotalProteinValues.get(1),
                        concentrationFourTotalProteinValues.get(2),
                        concentrationFourTotalProteinValues.get(3));
                bandIntensityTable.getItems().add(row);
            }

        }
    }

    public void onResetButtonClick(ActionEvent actionEvent) throws IOException{
        FXMLLoader fxmlLoader = new FXMLLoader(DetailsPageTwoController.class.getResource("final-screen.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 1178, 700);
        stage.setTitle("Incisive Lab");
        stage.setScene(scene);
        stage.show();
    }

    public void onNextButtonClick(ActionEvent actionEvent) throws IOException {

        //Add Linearity data to gel run
        details.getGelRunArrayList().get(LaneContentsPageController.currentIterativeRunTime).setBandIntensityDataTableView(bandIntensityTable);

        System.out.println(details.getGelRunArrayList().get(LaneContentsPageController.currentIterativeRunTime).toString());

        //Increment iterative run time (gel run)
        LaneContentsPageController.currentIterativeRunTime++;
        //TODO Navigate to lane content page



        //Dummy navigation to final screen
        FXMLLoader fxmlLoader = new FXMLLoader(DetailsPageTwoController.class.getResource("final-screen.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 1178, 700);
        stage.setTitle("Incisive Lab");
        stage.setScene(scene);
        stage.show();
    }

    public void onBackButtonClick(ActionEvent actionEvent) {

    }

    public class BandIntensityData{
        private int concentration;
        private BigDecimal tsRepOne;
        private BigDecimal tsRepTwo;
        private BigDecimal tsRepThree;
        private BigDecimal rsRepOne;

        public BandIntensityData(int concentration, BigDecimal tsRepOne, BigDecimal tsRepTwo, BigDecimal tsRepThree, BigDecimal rsRepOne) {
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

    public class DataPoint {
        private double proteinConcentration;
        private BigDecimal auc;

        public DataPoint(double proteinConcentration, BigDecimal auc) {
            this.proteinConcentration = proteinConcentration;
            this.auc = auc;
        }

        public double getProteinConcentration() {
            return proteinConcentration;
        }

        public void setProteinConcentration(double proteinConcentration) {
            this.proteinConcentration = proteinConcentration;
        }

        public BigDecimal getAuc() {
            return auc;
        }

        public void setAuc(BigDecimal auc) {
            this.auc = auc;
        }
    }
}
