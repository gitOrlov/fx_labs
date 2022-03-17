package example.topic_1;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.TimeZone;

public class Main extends Application {
    public static void main(String[] args) {
        launch();
    }

    @Override
    public void start(Stage primaryStage) {
        GridPane root = initRootLayout();
        Scene scene = new Scene(root, 600, 500);
        primaryStage.setTitle("Age");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private GridPane initRootLayout() {
        GridPane root = new GridPane();
        root.setAlignment(Pos.CENTER);
        root.setHgap(10);
        root.setVgap(10);
        root.setPadding(new Insets(25));

        Text sceneTitle = new Text("Calculation of age");

        Font fontBig = Font.font("Tahoma", FontWeight.NORMAL, 26);

        sceneTitle.setFont(fontBig);
        root.add(sceneTitle, 0, 0, 2, 1);

        Label userName = new Label("Name: ");
        userName.setFont(fontBig);
        root.add(userName, 0, 1);

        TextField nameTextField = new TextField();
        nameTextField.setFont(fontBig);
        root.add(nameTextField, 1, 1);

        Label userYear = new Label("Year: ");
        userYear.setFont(fontBig);
        root.add(userYear, 0, 2);

        TextField valueYear = new TextField();
        valueYear.setFont(fontBig);
        root.add(valueYear, 1, 2);

        Text result = new Text("Result");
        result.setFont(fontBig);
        root.add(result, 0, 6);

        Button button = new Button("OK");
        button.setFont(fontBig);
        button.setOnAction(event -> {
            Calendar calendar = Calendar.getInstance(TimeZone.getDefault(), Locale.getDefault());
            calendar.setTime(new Date());
            String year = valueYear.getText();
            if (year.matches("[0-9]+")) {
                int age = calendar.get(Calendar.YEAR) - Integer.parseInt(year);
                result.setText(nameTextField.getText() + " is " + age + " years old!");
            } else {
                result.setFill(Color.FIREBRICK);
                result.setText("Wrong year!");
            }
        });

        root.add(button, 1, 5);
        return root;
    }
}
