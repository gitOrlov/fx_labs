package example.topic_1;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Side;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.chart.*;
import javafx.scene.control.Label;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class ChartExample extends Application {

    public static void main(String[] args) {
        launch();
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        Tab tabBarChart = new Tab("BarChart", createBarChart());
        Tab tabLineChart = new Tab("LineChart", createLineChart());
        Tab tabPieChart = new Tab("PieChart", createPieChart());

        TabPane root = new TabPane();
        root.getTabs().addAll(tabBarChart, tabLineChart, tabPieChart);
        primaryStage.setTitle("Charts example");
        primaryStage.setScene(new Scene(root));
        primaryStage.show();
    }

    private Group createPieChart() {
        Group groupChart = new Group();
        ObservableList<PieChart.Data> pieChartData = FXCollections.observableArrayList(
                new PieChart.Data("Apple", 73.8),
                new PieChart.Data("Samsung", 69.4),
                new PieChart.Data("Huawei", 56.2),
                new PieChart.Data("Xiaomi", 32.8),
                new PieChart.Data("OPPO", 30.6),
                new PieChart.Data("Others", 106)
        );
        PieChart chart = new PieChart(pieChartData);
        chart.setTitle("Smartphone sales");
        chart.setLegendSide(Side.LEFT);

        Label caption = new Label("");
        caption.setTextFill(Color.DARKBLUE);
        caption.setStyle("-fx-font: 16 arial;");
        groupChart.getChildren().add(caption);

        for (PieChart.Data data : chart.getData()) {
            data.getNode().addEventHandler(MouseEvent.MOUSE_PRESSED, (MouseEvent e) -> {
                caption.setTranslateX(e.getSceneX() - caption.getWidth() / 2);
                caption.setTranslateY(e.getSceneY() - caption.getHeight());
                caption.setText(data.getPieValue() + "");
                caption.toFront();
            });
        }

        groupChart.getChildren().add(chart);
        return groupChart;
    }

    private Group createLineChart() {
        Group groupChart = new Group();
        Func f = new Func(-5, 5, 600);
        NumberAxis x = new NumberAxis();
        NumberAxis y = new NumberAxis();

        LineChart<Number, Number> numberLineChart = new LineChart<>(x, y);
        numberLineChart.setCreateSymbols(false);
        numberLineChart.setLayoutX(400);
        numberLineChart.setLayoutY(60);
        numberLineChart.setMinHeight(600);
        numberLineChart.setMinWidth(600);
        numberLineChart.getData().add(f.getSeries());

        groupChart.getChildren().add(numberLineChart);
        return groupChart;
    }

    static class Func {
        private double xmin, xmax;
        private int step;

        Func(double min, double max, int numStep) {
            xmin = min;
            xmax = max;
            step = numStep;
        }

        private double f(double x) {
            return (3 - 4 * x) / (x * x + 1);
        }

        String getName() {
            return "(3- 4*x)/(x*x+1)";
        }

        XYChart.Series getSeries() {
            ObservableList<XYChart.Data> datas = FXCollections.observableArrayList();
            double x0 = xmin;
            double h = (xmax - x0) / step;

            for (int i = 0; i < step; i++) {
                datas.add(new XYChart.Data(x0 + h * i, f(x0 + h * i)));
            }

            XYChart.Series series = new XYChart.Series();
            series.setName(getName());
            series.setData(datas);
            return series;
        }
    }

    private Group createBarChart() {
        Group groupChart = new Group();

        String austria = "Austria", brazil = "Brazil", france = "france", italy = "Italy", usa = "USA";
        final CategoryAxis xAxis = new CategoryAxis();
        final NumberAxis yAxis = new NumberAxis();
        final BarChart<String, Number> bc = new BarChart<>(xAxis, yAxis);

        bc.setTitle("Country weather");
        xAxis.setLabel("Country");
        yAxis.setLabel("Temperature");

        XYChart.Series series1 = new XYChart.Series();
        series1.setName("June");
        series1.getData().add(new XYChart.Data<>(austria, 16.4));
        series1.getData().add(new XYChart.Data<>(brazil, 26.6));
        series1.getData().add(new XYChart.Data<>(france, 19.2));
        series1.getData().add(new XYChart.Data<>(italy, 20.5));
        series1.getData().add(new XYChart.Data<>(usa, 22.1));

        XYChart.Series series2 = new XYChart.Series();
        series2.setName("July");
        series2.getData().add(new XYChart.Data<>(austria, 17.6));
        series2.getData().add(new XYChart.Data<>(brazil, 21.8));
        series2.getData().add(new XYChart.Data<>(france, 21.1));
        series2.getData().add(new XYChart.Data<>(italy, 22.5));
        series2.getData().add(new XYChart.Data<>(usa, 24.6));

        XYChart.Series series3 = new XYChart.Series();
        series3.setName("August");
        series3.getData().add(new XYChart.Data<>(austria, 17.2));
        series3.getData().add(new XYChart.Data<>(brazil, 22.1));
        series3.getData().add(new XYChart.Data<>(france, 20.4));
        series3.getData().add(new XYChart.Data<>(italy, 22.3));
        series3.getData().add(new XYChart.Data<>(usa, 23.0));

        bc.getData().addAll(series1, series2, series3);

        groupChart.getChildren().add(bc);
        return groupChart;
    }
}
