package example.topic_1;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.util.HashMap;
import java.util.Map;

public class lab1_CalorieCalculation extends Application {
    private Map<String, Integer> cal = new HashMap<>();
    private ObservableList products;

    public static void main(String[] args) {
        launch();
    }

    @Override
    public void start(Stage primaryStage) {
        initObservableList();
        GridPane root = initGridPane();
        Scene scene = new Scene(root, 600, 600);

        primaryStage.setTitle("Calorie Calculation");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private void initObservableList() {
        cal.put("colbasa", 301);
        cal.put("indeyka", 198);
        cal.put("omlet", 209);
        cal.put("wafles", 543);
        cal.put("cherry", 52);
        cal.put("cucumber", 13);

        products = FXCollections.observableArrayList(
                "colbasa",
                "indeyka",
                "omlet",
                "wafles",
                "cherry",
                "cucumber");
    }

    private GridPane initGridPane() {
        GridPane root = new GridPane();
        root.setAlignment(Pos.CENTER);
        root.setHgap(10);
        root.setVgap(10);
        root.setPadding(new Insets(25));

        Text sceneTitle = new Text("Callorie Calculation");
//        Locale.setDefault(new Locale("ru"));//todo: узнать ставит ли этот метод локаль на все приложение или как и как поставить русский язык

        Font font = Font.font("Verdana", FontWeight.MEDIUM, 20);
        sceneTitle.setFont(font);
        root.add(sceneTitle, 0, 0, 2, 1);

        Label changeProduct = new Label("Change product: ");
        changeProduct.setFont(font);
        root.add(changeProduct, 0, 1);

        ComboBox comboBox = new ComboBox(products);
        root.add(comboBox, 1, 1);

        Label enterWeight = new Label("enter weight in gramms: ");
        enterWeight.setFont(font);
        root.add(enterWeight, 0, 2);

        TextField weight = new TextField();
        weight.setFont(font);
        root.add(weight, 1, 2);

        Text result = new Text("Calorie in product: ");
        result.setFont(font);
        root.add(result, 0, 4);

        Button button = new Button();
        button.setFont(font);
        button.setOnAction(action -> {
            String stringWeight = weight.getText();
            if (stringWeight.matches("[0-9]+")) {
                float intWeight = Float.parseFloat(stringWeight);
                String prod = (String) comboBox.getValue();
                int currentCal = cal.get(prod);

                float cal = intWeight / 100 * currentCal;

                result.setText("In product: " + prod + " has weight: " + intWeight + " - " + cal + " calorie!");
            } else {
                result.setFill(Color.FIREBRICK);
                result.setText("enter incorrect weight!");
            }
        });

        root.add(button, 1, 3);

        return root;
    }
}
