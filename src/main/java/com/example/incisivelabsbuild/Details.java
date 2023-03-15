package com.example.incisivelabsbuild;

import java.util.ArrayList;

public class Details {
    String title_text;
    String batch_number_text;
    String checkPoint_text;
    String analyst_text;
    String checked_text;
    String notes_text;
    String bovine_or_globin;

    Double tssfTotalVolume_text;
    Double tssfFinalConcentration_text;
    Double tssfEstimatedConcentrationOfTestSample_text;
    Double tssfVolumeOfSample_lbl;
    Double tssfVolumeOf2XRSOB_lbl;

    Double rssfTotalVolume_text;
    Double rssfFinalConcentration_text;


    Integer numberOfGelRuns = 1;
    Double dilutionTableTestSampleVolumeOfSample_text;
    Double dilutionTableTestSampleTotalVolume_text;
    Double dilutionTableReferenceStandardVolumeOfSample_text;
    Double dilutionTableReferenceStandardTotalVolume_text;


    Double tssfFinalConcentration_txt;
    Double tssfEstimatedConcentrationOfTestSample_txt;
    Double tssfoutput;

    Double rssfFinalConcentration_txt;

    Double rssfEstimatedConcentrationOfTestSample_txt;
    Double rssfoutput;

    Double rssfConcentrationOfRefernceStndard_text;
    Double rssfVolumeOfSample_lbl;
    Double rssfVolumeOf2XRSOB_lbl;

    ArrayList<GelRun> gelRunArrayList = new ArrayList<>();

    public Details() {
    }

    public ArrayList<GelRun> getGelRunArrayList() {
        return gelRunArrayList;
    }

    public void setGelRunArrayList(ArrayList<GelRun> gelRunArrayList) {
        this.gelRunArrayList = gelRunArrayList;
    }

    public Integer getNumberOfGelRuns() {
        return numberOfGelRuns;
    }

    public void setNumberOfGelRuns(Integer numberOfGelRuns) {
        this.numberOfGelRuns = numberOfGelRuns;
    }

    public Double getDilutionTableTestSampleVolumeOfSample_text() {
        return dilutionTableTestSampleVolumeOfSample_text;
    }

    public void setDilutionTableTestSampleVolumeOfSample_text(Double dilutionTableTestSampleVolumeOfSample_text) {
        this.dilutionTableTestSampleVolumeOfSample_text = dilutionTableTestSampleVolumeOfSample_text;
    }

    public Double getDilutionTableTestSampleTotalVolume_text() {
        return dilutionTableTestSampleTotalVolume_text;
    }

    public void setDilutionTableTestSampleTotalVolume_text(Double dilutionTableTestSampleTotalVolume_text) {
        this.dilutionTableTestSampleTotalVolume_text = dilutionTableTestSampleTotalVolume_text;
    }

    public Double getDilutionTableReferenceStandardVolumeOfSample_text() {
        return dilutionTableReferenceStandardVolumeOfSample_text;
    }

    public void setDilutionTableReferenceStandardVolumeOfSample_text(Double dilutionTableReferenceStandardVolumeOfSample_text) {
        this.dilutionTableReferenceStandardVolumeOfSample_text = dilutionTableReferenceStandardVolumeOfSample_text;
    }

    public Double getDilutionTableReferenceStandardTotalVolume_text() {
        return dilutionTableReferenceStandardTotalVolume_text;
    }

    public void setDilutionTableReferenceStandardTotalVolume_text(Double dilutionTableReferenceStandardTotalVolume_text) {
        this.dilutionTableReferenceStandardTotalVolume_text = dilutionTableReferenceStandardTotalVolume_text;
    }

    public Double getRssfTotalVolume_text() {
        return rssfTotalVolume_text;
    }

    public void setRssfTotalVolume_text(Double rssfTotalVolume_text) {
        this.rssfTotalVolume_text = rssfTotalVolume_text;
    }

    public Double getRssfFinalConcentration_text() {
        return rssfFinalConcentration_text;
    }

    public void setRssfFinalConcentration_text(Double rssfFinalConcentration_text) {
        this.rssfFinalConcentration_text = rssfFinalConcentration_text;
    }

    public Double getRssfConcentrationOfRefernceStndard_text() {
        return rssfConcentrationOfRefernceStndard_text;
    }

    public void setRssfConcentrationOfRefernceStndard_text(Double rssfConcentrationOfRefernceStndard_text) {
        this.rssfConcentrationOfRefernceStndard_text = rssfConcentrationOfRefernceStndard_text;
    }

    public Double getRssfVolumeOfSample_lbl() {
        return rssfVolumeOfSample_lbl;
    }

    public void setRssfVolumeOfSample_lbl(Double rssfVolumeOfSample_lbl) {
        this.rssfVolumeOfSample_lbl = rssfVolumeOfSample_lbl;
    }

    public Double getRssfVolumeOf2XRSOB_lbl() {
        return rssfVolumeOf2XRSOB_lbl;
    }

    public void setRssfVolumeOf2XRSOB_lbl(Double rssfVolumeOf2XRSOB_lbl) {
        this.rssfVolumeOf2XRSOB_lbl = rssfVolumeOf2XRSOB_lbl;
    }
    public Double getTssfTotalVolume_text() {
        return tssfTotalVolume_text;
    }

    public void setTssfTotalVolume_text(Double tssfTotalVolume_text) {
        this.tssfTotalVolume_text = tssfTotalVolume_text;
    }

    public Double getTssfFinalConcentration_text() {
        return tssfFinalConcentration_text;
    }

    public void setTssfFinalConcentration_text(Double tssfFinalConcentration_text) {
        this.tssfFinalConcentration_text = tssfFinalConcentration_text;
    }

    public Double getTssfEstimatedConcentrationOfTestSample_text() {
        return tssfEstimatedConcentrationOfTestSample_text;
    }

    public void setTssfEstimatedConcentrationOfTestSample_text(Double tssfEstimatedConcentrationOfTestSample_text) {
        this.tssfEstimatedConcentrationOfTestSample_text = tssfEstimatedConcentrationOfTestSample_text;
    }

    public Double getTssfVolumeOfSample_lbl() {
        return tssfVolumeOfSample_lbl;
    }

    public void setTssfVolumeOfSample_lbl(Double tssfVolumeOfSample_lbl) {
        this.tssfVolumeOfSample_lbl = tssfVolumeOfSample_lbl;
    }

    public Double getTssfVolumeOf2XRSOB_lbl() {
        return tssfVolumeOf2XRSOB_lbl;
    }

    public void setTssfVolumeOf2XRSOB_lbl(Double tssfVolumeOf2XRSOB_lbl) {
        this.tssfVolumeOf2XRSOB_lbl = tssfVolumeOf2XRSOB_lbl;
    }

    public String getTitle_text() {
        return title_text;
    }

    public void setTitle_text(String title_text) {
        this.title_text = title_text;
    }

    public String getBatch_number_text() {
        return batch_number_text;
    }

    public void setBatch_number_text(String batch_number_text) {
        this.batch_number_text = batch_number_text;
    }

    public String getCheckPoint_text() {
        return checkPoint_text;
    }

    public void setCheckPoint_text(String checkPoint_text) {
        this.checkPoint_text = checkPoint_text;
    }

    public String getAnalyst_text() {
        return analyst_text;
    }

    public void setAnalyst_text(String analyst_text) {
        this.analyst_text = analyst_text;
    }

    public String getChecked_text() {
        return checked_text;
    }

    public void setChecked_text(String checked_text) {
        this.checked_text = checked_text;
    }

    public String getNotes_text() {
        return notes_text;
    }

    public void setNotes_text(String notes_text) {
        this.notes_text = notes_text;
    }

    public String getBovine_or_globin() {
        return bovine_or_globin;
    }

    public void setBovine_or_globin(String bovine_or_globin) {
        this.bovine_or_globin = bovine_or_globin;
    }
}
