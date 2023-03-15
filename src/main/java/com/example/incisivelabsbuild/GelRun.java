package com.example.incisivelabsbuild;

import javafx.scene.control.TableView;
import javafx.scene.image.Image;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Date;

public class GelRun {
    private String gelNumber;
    private LocalDate gelRunDate;
    private TableView<LaneContentsPageController.LaneContent> laneContentTableView;

    private TableView<RawDataPageController.RawData> rawDataTableView;
    private Image gelImage;
    private TableView<MassCorrectionPageController.MassCorrectionData> massCorrectionDataTableView;
    private TableView<MassCorrectionPageController.NormalisedToDilutionData> normalisedToDilutionDataTableView;
    private TableView<ProteinConcentrationPageController.ProteinConcentrationData> proteinConcentrationDataTableView;
    private BigDecimal testSampleMean;
    private BigDecimal testSampleStandardDeviation;
    private BigDecimal testSamplePercentage;
    private BigDecimal testSampleMgMlStock;
    private BigDecimal testSampleSDII;
    private BigDecimal testSampleMgMlOriginalSample;
    private BigDecimal testSampleSDIII;
    private BigDecimal testSampleRSD;
    private BigDecimal referenceSampleMean;
    private BigDecimal referenceSampleStandardDeviation;
    private BigDecimal referenceSamplePercentage;
    private BigDecimal referenceSampleMgMlStock;
    private BigDecimal referenceSampleSDII;
    private BigDecimal referenceSampleMgMlOriginalSample;
    private BigDecimal referenceSampleSDIII;
    private BigDecimal referenceSampleRSD;
    private TableView<LinearityPageController.BandIntensityData> bandIntensityDataTableView;

    public GelRun() {
    }

    public String getGelNumber() {
        return gelNumber;
    }

    public void setGelNumber(String gelNumber) {
        this.gelNumber = gelNumber;
    }

    public LocalDate getGelRunDate() {
        return gelRunDate;
    }

    public void setGelRunDate(LocalDate gelRunDate) {
        this.gelRunDate = gelRunDate;
    }

    public TableView<LaneContentsPageController.LaneContent> getLaneContentTableView() {
        return laneContentTableView;
    }

    public void setLaneContentTableView(TableView<LaneContentsPageController.LaneContent> laneContentTableView) {
        this.laneContentTableView = laneContentTableView;
    }

    public TableView<RawDataPageController.RawData> getRawDataTableView() {
        return rawDataTableView;
    }

    public void setRawDataTableView(TableView<RawDataPageController.RawData> rawDataTableView) {
        this.rawDataTableView = rawDataTableView;
    }

    public Image getGelImage() {
        return gelImage;
    }

    public void setGelImage(Image gelImage) {
        this.gelImage = gelImage;
    }

    public TableView<MassCorrectionPageController.MassCorrectionData> getMassCorrectionDataTableView() {
        return massCorrectionDataTableView;
    }

    public void setMassCorrectionDataTableView(TableView<MassCorrectionPageController.MassCorrectionData> massCorrectionDataTableView) {
        this.massCorrectionDataTableView = massCorrectionDataTableView;
    }

    public TableView<MassCorrectionPageController.NormalisedToDilutionData> getNormalisedToDilutionDataTableView() {
        return normalisedToDilutionDataTableView;
    }

    public void setNormalisedToDilutionDataTableView(TableView<MassCorrectionPageController.NormalisedToDilutionData> normalisedToDilutionDataTableView) {
        this.normalisedToDilutionDataTableView = normalisedToDilutionDataTableView;
    }

    public TableView<ProteinConcentrationPageController.ProteinConcentrationData> getProteinConcentrationDataTableView() {
        return proteinConcentrationDataTableView;
    }

    public void setProteinConcentrationDataTableView(TableView<ProteinConcentrationPageController.ProteinConcentrationData> proteinConcentrationDataTableView) {
        this.proteinConcentrationDataTableView = proteinConcentrationDataTableView;
    }

    public BigDecimal getTestSampleMean() {
        return testSampleMean;
    }

    public void setTestSampleMean(BigDecimal testSampleMean) {
        this.testSampleMean = testSampleMean;
    }

    public BigDecimal getTestSampleStandardDeviation() {
        return testSampleStandardDeviation;
    }

    public void setTestSampleStandardDeviation(BigDecimal testSampleStandardDeviation) {
        this.testSampleStandardDeviation = testSampleStandardDeviation;
    }

    public BigDecimal getTestSamplePercentage() {
        return testSamplePercentage;
    }

    public void setTestSamplePercentage(BigDecimal testSamplePercentage) {
        this.testSamplePercentage = testSamplePercentage;
    }

    public BigDecimal getTestSampleMgMlStock() {
        return testSampleMgMlStock;
    }

    public void setTestSampleMgMlStock(BigDecimal testSampleMgMlStock) {
        this.testSampleMgMlStock = testSampleMgMlStock;
    }

    public BigDecimal getTestSampleSDII() {
        return testSampleSDII;
    }

    public void setTestSampleSDII(BigDecimal testSampleSDII) {
        this.testSampleSDII = testSampleSDII;
    }

    public BigDecimal getTestSampleMgMlOriginalSample() {
        return testSampleMgMlOriginalSample;
    }

    public void setTestSampleMgMlOriginalSample(BigDecimal testSampleMgMlOriginalSample) {
        this.testSampleMgMlOriginalSample = testSampleMgMlOriginalSample;
    }

    public BigDecimal getTestSampleSDIII() {
        return testSampleSDIII;
    }

    public void setTestSampleSDIII(BigDecimal testSampleSDIII) {
        this.testSampleSDIII = testSampleSDIII;
    }

    public BigDecimal getTestSampleRSD() {
        return testSampleRSD;
    }

    public void setTestSampleRSD(BigDecimal testSampleRSD) {
        this.testSampleRSD = testSampleRSD;
    }

    public BigDecimal getReferenceSampleMean() {
        return referenceSampleMean;
    }

    public void setReferenceSampleMean(BigDecimal referenceSampleMean) {
        this.referenceSampleMean = referenceSampleMean;
    }

    public BigDecimal getReferenceSampleStandardDeviation() {
        return referenceSampleStandardDeviation;
    }

    public void setReferenceSampleStandardDeviation(BigDecimal referenceSampleStandardDeviation) {
        this.referenceSampleStandardDeviation = referenceSampleStandardDeviation;
    }

    public BigDecimal getReferenceSamplePercentage() {
        return referenceSamplePercentage;
    }

    public void setReferenceSamplePercentage(BigDecimal referenceSamplePercentage) {
        this.referenceSamplePercentage = referenceSamplePercentage;
    }

    public BigDecimal getReferenceSampleMgMlStock() {
        return referenceSampleMgMlStock;
    }

    public void setReferenceSampleMgMlStock(BigDecimal referenceSampleMgMlStock) {
        this.referenceSampleMgMlStock = referenceSampleMgMlStock;
    }

    public BigDecimal getReferenceSampleSDII() {
        return referenceSampleSDII;
    }

    public void setReferenceSampleSDII(BigDecimal referenceSampleSDII) {
        this.referenceSampleSDII = referenceSampleSDII;
    }

    public BigDecimal getReferenceSampleMgMlOriginalSample() {
        return referenceSampleMgMlOriginalSample;
    }

    public void setReferenceSampleMgMlOriginalSample(BigDecimal referenceSampleMgMlOriginalSample) {
        this.referenceSampleMgMlOriginalSample = referenceSampleMgMlOriginalSample;
    }

    public BigDecimal getReferenceSampleSDIII() {
        return referenceSampleSDIII;
    }

    public void setReferenceSampleSDIII(BigDecimal referenceSampleSDIII) {
        this.referenceSampleSDIII = referenceSampleSDIII;
    }

    public BigDecimal getReferenceSampleRSD() {
        return referenceSampleRSD;
    }

    public void setReferenceSampleRSD(BigDecimal referenceSampleRSD) {
        this.referenceSampleRSD = referenceSampleRSD;
    }

    public TableView<LinearityPageController.BandIntensityData> getBandIntensityDataTableView() {
        return bandIntensityDataTableView;
    }

    public void setBandIntensityDataTableView(TableView<LinearityPageController.BandIntensityData> bandIntensityDataTableView) {
        this.bandIntensityDataTableView = bandIntensityDataTableView;
    }

    @Override
    public String toString() {
        return "GelRun{" +
                "gelNumber='" + gelNumber + '\'' +
                ", gelRunDate=" + gelRunDate +
                ", laneContentTableView=" + laneContentTableView +
                ", rawDataTableView=" + rawDataTableView +
                ", gelImage=" + gelImage +
                ", massCorrectionDataTableView=" + massCorrectionDataTableView +
                ", normalisedToDilutionDataTableView=" + normalisedToDilutionDataTableView +
                ", proteinConcentrationDataTableView=" + proteinConcentrationDataTableView +
                ", testSampleMean=" + testSampleMean +
                ", testSampleStandardDeviation=" + testSampleStandardDeviation +
                ", testSamplePercentage=" + testSamplePercentage +
                ", testSampleMgMlStock=" + testSampleMgMlStock +
                ", testSampleSDII=" + testSampleSDII +
                ", testSampleMgMlOriginalSample=" + testSampleMgMlOriginalSample +
                ", testSampleSDIII=" + testSampleSDIII +
                ", testSampleRSD=" + testSampleRSD +
                ", referenceSampleMean=" + referenceSampleMean +
                ", referenceSampleStandardDeviation=" + referenceSampleStandardDeviation +
                ", referenceSamplePercentage=" + referenceSamplePercentage +
                ", referenceSampleMgMlStock=" + referenceSampleMgMlStock +
                ", referenceSampleSDII=" + referenceSampleSDII +
                ", referenceSampleMgMlOriginalSample=" + referenceSampleMgMlOriginalSample +
                ", referenceSampleSDIII=" + referenceSampleSDIII +
                ", referenceSampleRSD=" + referenceSampleRSD +
                ", bandIntensityDataTableView=" + bandIntensityDataTableView +
                '}';
    }
}
