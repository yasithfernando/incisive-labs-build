package logics;

import javafx.collections.ObservableList;
import javafx.scene.Node;
import javafx.scene.chart.XYChart;

import java.math.BigDecimal;
import java.math.MathContext;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.function.ToDoubleFunction;
import org.apache.commons.math3.stat.regression.SimpleRegression;

public class mathFunctions {

    public static double calculateMean(double[] dataArray){
        double mean = 0;
        double sum ;
        int i ;
        int n = dataArray.length;
        sum = 0;
        for (i = 0; i < n; i++) {
        sum = dataArray[i];
        }
        return mean;
    }

    public static double calculateStandardDeviation(Double[] dataArray){
        double sum = 0;
        for(double i : dataArray){
            sum += i;
        }
        int length = dataArray.length;
        double mean = sum/length;

        double standardDeviation = 0;
        for(double i : dataArray) {
            standardDeviation += Math.pow(i - mean , 2);
        }
        return Math.sqrt(standardDeviation/length);
    }

    public static BigDecimal calculateMeanBigDecimal(BigDecimal[] data) {
        // Calculate the mean
        BigDecimal sum = Arrays.stream(data).reduce(BigDecimal.ZERO, BigDecimal::add);
        BigDecimal mean = sum.divide(BigDecimal.valueOf(data.length), MathContext.DECIMAL32);
        return mean;
    }

    public static BigDecimal calculateStandardDeviationBigDecimal(BigDecimal[] data) {
        // Calculate the mean
        BigDecimal mean = calculateMeanBigDecimal(data);

        // Calculate the standard deviation
        BigDecimal sumOfSquaredDiffs = Arrays.stream(data)
                .map(x -> x.subtract(mean).pow(2))
                .reduce(BigDecimal.ZERO, BigDecimal::add);
        BigDecimal variance = sumOfSquaredDiffs.divide(BigDecimal.valueOf(data.length-1), MathContext.DECIMAL32);
        BigDecimal standardDeviation = BigDecimal.valueOf(Math.sqrt(variance.doubleValue()));

        System.out.println("Input data: " + Arrays.toString(data));
        System.out.println("Mean: " + mean);
        System.out.println("Variance: " + variance);
        System.out.println("Standard deviation: " + standardDeviation);


        //New

        return standardDeviation;
    }

    public static XYChart.Series<Double,BigDecimal> addTrendLine(XYChart.Series<Double, BigDecimal> series) {
        // Get the data points in the series
        ObservableList<XYChart.Data<Double, BigDecimal>> data = series.getData();

        // Create a regression object and add the data points
        SimpleRegression regression = new SimpleRegression();
        for (XYChart.Data<Double, BigDecimal> point : data) {
            regression.addData(point.getXValue(), point.getYValue().doubleValue());
        }

        // Get the slope and y-intercept of the trend line
        double slope = regression.getSlope();
        double yIntercept = regression.getIntercept();

        // Add the start and end points of the trend line to the series
        double minX = data.get(0).getXValue();
        double maxX = data.get(data.size()-1).getXValue();

        XYChart.Series<Double,BigDecimal> trendLineSeries = new XYChart.Series<>();
        trendLineSeries.getData().add(new XYChart.Data<>(minX, BigDecimal.valueOf(slope * minX + yIntercept)));
        trendLineSeries.getData().add(new XYChart.Data<>(maxX, BigDecimal.valueOf(slope * maxX + yIntercept)));


        return trendLineSeries;
    }






}
