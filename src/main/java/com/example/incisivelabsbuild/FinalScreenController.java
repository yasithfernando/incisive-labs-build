package com.example.incisivelabsbuild;

import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import logics.mathFunctions;
import org.w3c.dom.Document;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;

//import org.apache.pdfbox.pdmodel.PDDocument;
//import org.apache.pdfbox.pdmodel.PDPage;
//import org.apache.pdfbox.pdmodel.PDPageContentStream;
//import org.apache.pdfbox.pdmodel.font.PDType1Font;

import static com.example.incisivelabsbuild.HelloApplication.details;

public class FinalScreenController {
    public Label tssfVolumeOfSampleLabel;
    public TextField tssfEstimatedConcentrationOfTestSampleTxt;
    public TextArea finalScreenPromptText;
    public TextField finalScreenMeanLabel;
    public TextField finalScreenStandardDeviationLabel;
    public Button resetBtn1;


    public void initialize(){
        BigDecimal finalMean;
        BigDecimal finalStandardDeviation;
        BigDecimal sum = BigDecimal.valueOf(0);
        ArrayList<BigDecimal> gelRunStandardDeviations = new ArrayList<>();
        for (GelRun gelRun : details.getGelRunArrayList()) {
            sum = sum.add(gelRun.getTestSampleMgMlOriginalSample());
            gelRunStandardDeviations.add(gelRun.getTestSampleStandardDeviation());
        }
        finalMean = sum.divide(BigDecimal.valueOf(details.getGelRunArrayList().size()),3, RoundingMode.HALF_UP);
        finalStandardDeviation = mathFunctions.calculateStandardDeviationSampleBigDecimal(gelRunStandardDeviations.toArray(new BigDecimal[gelRunStandardDeviations.size()]));

        finalScreenMeanLabel.setText(finalMean.toString());
        finalScreenStandardDeviationLabel.setText(finalStandardDeviation.toString());
    }

    public void onResetButtonClick(ActionEvent actionEvent) {
    }

    public void onNextButtonClick(ActionEvent actionEvent) {
    }

    public void onBackButtonClick(ActionEvent actionEvent) {
    }

    public void tssfFieldsUpdate(ActionEvent actionEvent) {
    }

    public void pdfGeneration(){
//        try {
//            PDDocument document = new PDDocument();
//            PDPage page = new PDPage();
//            document.addPage(page);
//            PDPageContentStream contentStream = new PDPageContentStream(document, page);
//            contentStream.beginText();
//            contentStream.setFont(PDType1Font.HELVETICA_BOLD, 12);
//            contentStream.newLineAtOffset(100, 700);
//            contentStream.showText("Hello, world!");
//            contentStream.endText();
//            contentStream.close();
//            document.save("output.pdf");
//            document.close();
//            System.out.println("PDF generated successfully.");
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
    }
}
