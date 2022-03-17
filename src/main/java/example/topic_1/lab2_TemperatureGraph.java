package example.topic_1;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Side;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.PieChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Stage;

import java.util.Arrays;
import java.util.List;

/**
 * Создайте однооконное приложение для решения следующей задачи.
 * Задан дневник температуры за март:
 * {-2, -5, -2, -4, 3, -6, -2, -1, 5, 1, 1, 0, -1, 0, 3, -1, 2, 5, 2, 4, 4, 0, 6, 1, 4, 6, -1, 2, 4, 7, 11}
 * Iзобразите график температуры по дням и соотношение между днями с положительной, отрицательной и нулевой температурой в виде круговой диаграммы.
 * <p>
 * В поле ответа на задание введите количество дней, соответствующее самому большому сектору получившейся диаграммы.
 * <p>
 * Дополнительно: реализуйте возможность изменения дневника температуры.
 * Это можно сделать разными способами. Например, создать поле ввода температуры для каждого дня месяца.
 * Второй вариант – вводить все числа в одной строке, которую затем преобразовать в массив чисел методами классов String и Integer,
 * в этом случае, не забудьте проверить корректность ввода данных.
 */

@SuppressWarnings("ALL")
public class lab2_TemperatureGraph extends Application {
    private XYChart.Series series = new XYChart.Series();
    private int positiveDays;
    private int negativeDays;
    private int zeroDays;

    List<Integer> list;

    LineChart<Number, Number> numberLineChart;

    public static void main(String[] args) {
        launch();
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        initData();

        Tab temperatureInput = new Tab("TemperatureInput", createTemperatureInput());
        Tab schedule = new Tab("Schedule", createSchedule());
        Tab tabPieChart = new Tab("Ratio", createRatio());

        TabPane root = new TabPane();
        root.getTabs().addAll(temperatureInput, schedule, tabPieChart);

        primaryStage.setTitle("Temperature Graph");
        primaryStage.setScene(new Scene(root));
        primaryStage.show();
    }

    private VBox createTemperatureInput() {
        VBox vBox1 = new VBox(5);
        vBox1.setPadding(new Insets(10, 100, 10, 10));

        for (int i = 1; i <= 15; i++) {
            Label label = getLabel(i);
            Spinner spinner = getSpinner();

            HBox hbox = new HBox(20, label, spinner);
            vBox1.getChildren().add(hbox);
        }

        VBox vBox2 = new VBox(5);
        vBox2.setPadding(new Insets(10, 100, 10, 10));

        for (int i = 16; i <= 31; i++) {
            Label label = getLabel(i);
            Spinner spinner = getSpinner();

            int finalI = i;
            spinner.valueProperty().addListener((obs, oldValue, newValue) -> {
                list.add(finalI - 1, Integer.parseInt(newValue.toString()));
            });

            HBox hbox = new HBox(20, label, spinner);
            vBox2.getChildren().add(hbox);
        }

        HBox hbox = new HBox(20, vBox1, vBox2);

        Button btn = new Button("send data");
        btn.setFont(Font.font(20));
        btn.setOnAction(event -> {
            //todo здесь должна быть отправка данных графики для вывода
        });

        VBox vBox = new VBox(20, hbox, btn);

        return vBox;
    }

    private Label getLabel(int i) {
        Label label = new Label();
        label.setFont(Font.font(10));
        label.setText("Temp for " + i + " March");
        return label;
    }

    private Spinner getSpinner() {
        Spinner<Integer> spInt = new Spinner<>(-30, 30, 0);
        spInt.setStyle("-fx-font-size: 10px");
        return spInt;
    }

    private Node createRatio() {
        Group groupChart = new Group();
        ObservableList<PieChart.Data> pieChartData = FXCollections.observableArrayList(
                new PieChart.Data("Pozitive", positiveDays),
                new PieChart.Data("Negative", negativeDays),
                new PieChart.Data("Zero", zeroDays)
        );
        PieChart chart = new PieChart(pieChartData);
        chart.setTitle("Temperature Ratio");
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

    private Node createSchedule() {
        Group groupChart = new Group();
        NumberAxis x = new NumberAxis();
        NumberAxis y = new NumberAxis(-7, 12, 1);

        numberLineChart = new LineChart<>(x, y);
        numberLineChart.setLayoutX(400);
        numberLineChart.setLayoutY(60);
        numberLineChart.setMinHeight(600);
        numberLineChart.setMinWidth(600);
        numberLineChart.getData().add(series);

        groupChart.getChildren().add(numberLineChart);
        return groupChart;
    }

    private void initData() {
        list = Arrays.asList(-2, -5, -2, -4, 3, -6, -2, -1, 5, 1, 1, 0, -1, 0, 3, -1, 2, 5, 2, 4, 4, 0, 6, 1, 4, 6, -1, 2, 4, 7, 11);

        for (Integer integer : list) {
            if (integer > 0) positiveDays++;
            else if (integer < 0) negativeDays++;
            else if (integer == 0) zeroDays++;
        }

        ObservableList<XYChart.Data> datas = FXCollections.observableArrayList();

        int i = 0;
        for (Integer temp : list) {
            datas.add(new XYChart.Data(++i, temp));
        }

        series.setName("Temperature in March");
        series.setData(datas);
    }

}
