package com.example.incisivelabsbuild;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.util.converter.DoubleStringConverter;

import java.io.IOException;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.DecimalFormat;

import static com.example.incisivelabsbuild.HelloApplication.details;
import static com.example.incisivelabsbuild.HelloApplication.stage;

public class MassCorrectionPageController {

    public Button resetBtn;
    public Button nextBtn;
    public Button backBtn;
    public TableView<MassCorrectionData> massCorrectionTable;
    public TableView<NormalisedToDilutionData> normalisedToDilutionTable;

    public static TableView<NormalisedToDilutionData> finalNormalisedToDilutionTable;
    public static TableView<MassCorrectionData> finalMassCorrectionTable;

    public void initialize(){
        // Set up the mass correction table columns
        initialiseMassCorrectionTable();

        // Set up the normalised to dilution table columns
        initialiseNormalisedToDilutionTableColumn();

        //Calculate Mass Correction
        calculateMassCorrection(RawDataPageController.finalisedRawDataTable);

        //Calculate Normalised To Dilution Data
        BigDecimal dilutionFactor = BigDecimal.valueOf(0.025);
        normaliseToDilution(massCorrectionTable, dilutionFactor);

        // Add some sample data to the table
//        RawDataPageController.RawData sample1 = new RawDataPageController.RawData("TS D3",22033.898, 14276.46, 2803.54, 1470.62);
//        massCorrectionTable.getItems().add(sample1);
    }

    public void calculateMassCorrection(TableView<RawDataPageController.RawData> rawDataTable){
        try {
            for (int i = 0; i < rawDataTable.getItems().size(); i++){
                String sampleName = rawDataTable.getItems().get(i).getSampleName();
                BigDecimal monomer = doubleFormatter(BigDecimal.valueOf(rawDataTable.getItems().get(i).getMonomer()));
                BigDecimal dimer = doubleFormatter(BigDecimal.valueOf(rawDataTable.getItems().get(i).getDimer()/2));
                BigDecimal trimer = doubleFormatter(BigDecimal.valueOf(rawDataTable.getItems().get(i).getTrimer()/3));
                BigDecimal tretramer = doubleFormatter(BigDecimal.valueOf(rawDataTable.getItems().get(i).getTretramer()/4));

                MassCorrectionData dataRow = new MassCorrectionData(sampleName,monomer,dimer,trimer,tretramer);
                massCorrectionTable.getItems().add(dataRow);
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }

    public void normaliseToDilution(TableView<MassCorrectionData> massCorrectionDataTable, BigDecimal dilutionFactor){
        //TODO Confirm Usage of Dilution Factor
        for (int i = 0; i < massCorrectionDataTable.getItems().size(); i++) {
            if (i == 0 || i == 3 ||i == 6 || i == 9){
                dilutionFactor = BigDecimal.valueOf(0.025);
                mapMassDataToDilutionData(massCorrectionDataTable.getItems().get(i),dilutionFactor);
            }
            else if (i == 1 || i == 4 ||i == 7 || i == 10){
                dilutionFactor = BigDecimal.valueOf(0.05);
                mapMassDataToDilutionData(massCorrectionDataTable.getItems().get(i),dilutionFactor);
            }
            else if (i == 2 || i == 5 ||i == 8 || i == 11){
                dilutionFactor = BigDecimal.valueOf(0.1);
                mapMassDataToDilutionData(massCorrectionDataTable.getItems().get(i),dilutionFactor);
            }


        }

    }

    private void mapMassDataToDilutionData(MassCorrectionData dataRow, BigDecimal dilutionFactor){
        String sampleName = dataRow.getSampleName();
        BigDecimal monomer = doubleFormatter(dataRow.getMonomer().divide(dilutionFactor, RoundingMode.HALF_EVEN));
        BigDecimal dimer = doubleFormatter(dataRow.getDimer().divide(dilutionFactor,RoundingMode.HALF_EVEN));
        BigDecimal trimer = doubleFormatter(dataRow.getTrimer().divide(dilutionFactor,RoundingMode.HALF_EVEN));
        BigDecimal tretramer = doubleFormatter(dataRow.getTretramer().divide(dilutionFactor,RoundingMode.HALF_EVEN));

        NormalisedToDilutionData mappedDataRow = new NormalisedToDilutionData(sampleName,monomer,dimer,trimer,tretramer);
        normalisedToDilutionTable.getItems().add(mappedDataRow);
    }

    private BigDecimal doubleFormatter(BigDecimal value){

        // Create a DecimalFormat object with a pattern that formats values to two decimal points
        DecimalFormat df = new DecimalFormat("#.###");

        // Use the DecimalFormat object to format the value
        String formattedValue = df.format(value);

        // Convert the formatted value back to a double and return
        return BigDecimal.valueOf(Double.parseDouble(formattedValue));
    }

    public void initialiseMassCorrectionTable(){
        TableColumn<MassCorrectionPageController.MassCorrectionData, String> sampleColumn = new TableColumn<>("Sample");
        sampleColumn.setCellValueFactory(new PropertyValueFactory<>("sample"));
        sampleColumn.setCellFactory(TextFieldTableCell.forTableColumn());

        TableColumn<MassCorrectionPageController.MassCorrectionData, Double> monomerColumn = new TableColumn<>("Monomer 14 kDa");
        monomerColumn.setCellValueFactory(new PropertyValueFactory<>("monomer"));
        monomerColumn.setCellFactory(TextFieldTableCell.forTableColumn(new DoubleStringConverter()));

        TableColumn<MassCorrectionPageController.MassCorrectionData, Double> dimerColumn = new TableColumn<>("Dimer 14 kDa");
        dimerColumn.setCellValueFactory(new PropertyValueFactory<>("dimer"));
        dimerColumn.setCellFactory(TextFieldTableCell.forTableColumn(new DoubleStringConverter()));

        TableColumn<MassCorrectionPageController.MassCorrectionData, Double> trimerColumn = new TableColumn<>("Trimer 14 kDa");
        trimerColumn.setCellValueFactory(new PropertyValueFactory<>("trimer"));
        trimerColumn.setCellFactory(TextFieldTableCell.forTableColumn(new DoubleStringConverter()));

        TableColumn<MassCorrectionPageController.MassCorrectionData, Double> tretramerColumn = new TableColumn<>("Tretramer 14 kDa");
        tretramerColumn.setCellValueFactory(new PropertyValueFactory<>("tretramer"));
        tretramerColumn.setCellFactory(TextFieldTableCell.forTableColumn(new DoubleStringConverter()));

        TableColumn<MassCorrectionPageController.MassCorrectionData, Double> totalProteinColumn = new TableColumn<>("Tretramer 14 kDa");
        totalProteinColumn.setCellValueFactory(new PropertyValueFactory<>("tretramer"));
        totalProteinColumn.setCellFactory(TextFieldTableCell.forTableColumn(new DoubleStringConverter()));
    }

    public void initialiseNormalisedToDilutionTableColumn(){
        TableColumn<MassCorrectionPageController.NormalisedToDilutionData, String> sampleColumnNormalised = new TableColumn<>("Sample");
        sampleColumnNormalised.setCellValueFactory(new PropertyValueFactory<>("sample"));
        sampleColumnNormalised.setCellFactory(TextFieldTableCell.forTableColumn());

        TableColumn<MassCorrectionPageController.MassCorrectionData, Double> monomerColumnNormalised = new TableColumn<>("Monomer 14 kDa");
        monomerColumnNormalised.setCellValueFactory(new PropertyValueFactory<>("monomer"));
        monomerColumnNormalised.setCellFactory(TextFieldTableCell.forTableColumn(new DoubleStringConverter()));

        TableColumn<MassCorrectionPageController.MassCorrectionData, Double> dimerColumnNormalised = new TableColumn<>("Dimer 14 kDa");
        dimerColumnNormalised.setCellValueFactory(new PropertyValueFactory<>("dimer"));
        dimerColumnNormalised.setCellFactory(TextFieldTableCell.forTableColumn(new DoubleStringConverter()));

        TableColumn<MassCorrectionPageController.MassCorrectionData, Double> trimerColumnNormalised = new TableColumn<>("Trimer 14 kDa");
        trimerColumnNormalised.setCellValueFactory(new PropertyValueFactory<>("trimer"));
        trimerColumnNormalised.setCellFactory(TextFieldTableCell.forTableColumn(new DoubleStringConverter()));

        TableColumn<MassCorrectionPageController.MassCorrectionData, Double> tretramerColumnNormalised = new TableColumn<>("Tretramer 14 kDa");
        tretramerColumnNormalised.setCellValueFactory(new PropertyValueFactory<>("tretramer"));
        tretramerColumnNormalised.setCellFactory(TextFieldTableCell.forTableColumn(new DoubleStringConverter()));

        TableColumn<MassCorrectionPageController.MassCorrectionData, Double> totalColumnNormalised = new TableColumn<>("Tretramer 14 kDa");
        totalColumnNormalised.setCellValueFactory(new PropertyValueFactory<>("tretramer"));
        totalColumnNormalised.setCellFactory(TextFieldTableCell.forTableColumn(new DoubleStringConverter()));
    }

    public void onResetButtonClick(ActionEvent actionEvent) {
    }

    public void onNextButtonClick(ActionEvent actionEvent) throws IOException {
        HelloApplication.backNavigation = false;

        //Don't change these two lines
        finalNormalisedToDilutionTable = normalisedToDilutionTable;
        finalMassCorrectionTable = massCorrectionTable;

        //Add Mass Correction Table and Dilution Table to Gel Run Object
        details.getGelRunArrayList().get(LaneContentsPageController.currentIterativeRunTime).setMassCorrectionDataTableView(finalMassCorrectionTable);
        details.getGelRunArrayList().get(LaneContentsPageController.currentIterativeRunTime).setNormalisedToDilutionDataTableView(finalNormalisedToDilutionTable);

        FXMLLoader fxmlLoader = new FXMLLoader(MassCorrectionPageController.class.getResource("protein-concentration-page.fxml"));

        //Set the stage with the new scene
        Scene scene = new Scene(fxmlLoader.load(), 1178, 700);
        stage.setTitle("Incisive Lab");
        stage.setScene(scene);
        stage.show();
    }

    public void onBackButtonClick(ActionEvent actionEvent)  throws IOException{
        HelloApplication.backNavigation = true;

        FXMLLoader fxmlLoader = new FXMLLoader(DetailsPageOneController.class.getResource("raw-data-page.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 1178, 700);
        stage.setTitle("Incisive Lab");
        stage.setScene(scene);
        stage.show();
    }

    public static class MassCorrectionData{
        private String sampleName;
        private BigDecimal monomer;
        private BigDecimal dimer;
        private BigDecimal trimer;
        private BigDecimal tretramer;
        private BigDecimal totalProtein;

        public MassCorrectionData(String sampleName, BigDecimal monomer, BigDecimal dimer, BigDecimal trimer, BigDecimal tretramer) {
            this.sampleName = sampleName;
            this.monomer = monomer;
            this.dimer = dimer;
            this.trimer = trimer;
            this.tretramer = tretramer;
            this.totalProtein = monomer.add(dimer).add(trimer).add(tretramer);
        }

        public String getSampleName() {
            return sampleName;
        }

        public void setSampleName(String sampleName) {
            this.sampleName = sampleName;
        }

        public BigDecimal getMonomer() {
            return monomer;
        }

        public void setMonomer(BigDecimal monomer) {
            this.monomer = monomer;
        }

        public BigDecimal getDimer() {
            return dimer;
        }

        public void setDimer(BigDecimal dimer) {
            this.dimer = dimer;
        }

        public BigDecimal getTrimer() {
            return trimer;
        }

        public void setTrimer(BigDecimal trimer) {
            this.trimer = trimer;
        }

        public BigDecimal getTretramer() {
            return tretramer;
        }

        public void setTretramer(BigDecimal tretramer) {
            this.tretramer = tretramer;
        }

        public BigDecimal getTotalProtein() {
            return totalProtein;
        }

        public void setTotalProtein(BigDecimal totalProtein) {
            this.totalProtein = totalProtein;
        }
    }

    public static class NormalisedToDilutionData{
        private String sampleName;
        private BigDecimal monomer;
        private BigDecimal dimer;
        private BigDecimal trimer;
        private BigDecimal tretramer;
        private BigDecimal total;

        public NormalisedToDilutionData(String sampleName, BigDecimal monomer, BigDecimal dimer, BigDecimal trimer, BigDecimal tretramer) {
            this.sampleName = sampleName;
            this.monomer = monomer;
            this.dimer = dimer;
            this.trimer = trimer;
            this.tretramer = tretramer;
            this.total = monomer.add(dimer).add(trimer).add(tretramer);
        }

        public String getSampleName() {
            return sampleName;
        }

        public void setSampleName(String sampleName) {
            this.sampleName = sampleName;
        }

        public BigDecimal getMonomer() {
            return monomer;
        }

        public void setMonomer(BigDecimal monomer) {
            this.monomer = monomer;
        }

        public BigDecimal getDimer() {
            return dimer;
        }

        public void setDimer(BigDecimal dimer) {
            this.dimer = dimer;
        }

        public BigDecimal getTrimer() {
            return trimer;
        }

        public void setTrimer(BigDecimal trimer) {
            this.trimer = trimer;
        }

        public BigDecimal getTretramer() {
            return tretramer;
        }

        public void setTretramer(BigDecimal tretramer) {
            this.tretramer = tretramer;
        }

        public BigDecimal getTotal() {
            return total;
        }

        public void setTotal(BigDecimal total) {
            this.total = total;
        }
    }
}
