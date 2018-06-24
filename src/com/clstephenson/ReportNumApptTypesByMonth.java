package com.clstephenson;

import com.clstephenson.dataaccess.ReportData;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Side;
import javafx.scene.Scene;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.stage.Stage;

import java.time.LocalDate;
import java.time.Month;
import java.time.format.TextStyle;
import java.util.Locale;
import java.util.stream.Collectors;

public class ReportNumApptTypesByMonth {

    public static void showReport() {
        Stage stage = new Stage();
        CategoryAxis categoryAxis = new CategoryAxis();
        NumberAxis numberAxis = new NumberAxis();
        categoryAxis.setLabel("Months of Year (" + LocalDate.now().getYear() + ")");
        BarChart<String, Number> barChart = new BarChart<>(categoryAxis, numberAxis);
        numberAxis.setLabel("Number of Appointments");

        ObservableList<NumApptType> list;
        try {
            list = new ReportData().getNumApptTypesByMonth(LocalDate.now().getYear(), LoginSessionHelper.getSession());
        } catch (DataRepositoryException e) {
            Dialog.showDBError(e.getMessage());
            return;
        }

        for(AppointmentType type : AppointmentType.values()) {
            XYChart.Series<String, Number> series = new XYChart.Series<>();
            series.setName(type.toString());
            // using stream and lambda here to filter the data down the the current iteration of "type",
            // and mapping the month and numAppointment properties to the chart data property.  The data is then
            // collected back into a observable arraylist that can be then added as a series to the barChart data.
            // With streams and lambdas, this could be done with less code/additional methods making it more concise.
            series.setData(list.stream()
                    .filter(data -> data.getType().equals(type))
                    .map(data -> new XYChart.Data<String, Number>(
                            Month.of(data.getMonth()).getDisplayName(TextStyle.FULL, Locale.getDefault()),
                            data.getNumAppointments()))
                    .collect(Collectors.toCollection(FXCollections::observableArrayList))
            );
            barChart.getData().add(series);
        }

        barChart.setTitle("Number of Appointments by Month for User: " + LoginSessionHelper.getUsername());
        barChart.setCategoryGap(25);
        barChart.setLegendSide(Side.RIGHT);
        Scene scene = new Scene(barChart, 800, 600);
        stage.setTitle("Report:  Appointments by Month");
        stage.setScene(scene);
        stage.show();
    }
}
