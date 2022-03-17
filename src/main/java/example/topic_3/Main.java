package example.topic_3;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.FileChooser;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class Main extends Application {

    Label label;

    @Override
    public void start(Stage primaryStage) throws Exception {
        primaryStage.setTitle("Example dialogs");
        VBox root = new VBox();
        root.setAlignment(Pos.CENTER);
        root.setSpacing(20);
        root.setPadding(new Insets(10));

        label = new Label("Example dialog");
        label.setFont(Font.font(24));

        Button btn1 = new Button("Information Alert");
        btn1.setFont(Font.font("Tahoma", 20));
        btn1.setOnAction((ActionEvent e) -> {
            informDialog1();
        });

        Button btn2 = new Button("Information Alert with default Header Text");
        btn2.setFont(Font.font("Tahoma", 20));
        btn2.setOnAction((ActionEvent e) -> {
            informDialog2();
        });

        Button btn3 = new Button("Information Alert without Header Text");
        btn3.setFont(Font.font("Tahoma", 20));
        btn3.setOnAction((ActionEvent e) -> {
            informDialog3();
        });

        Button btn4 = new Button("Warning Alert");
        btn4.setFont(Font.font("Tahoma", 20));
        btn4.setOnAction((ActionEvent e) -> {
            informDialog4();
        });

        Button btn5 = new Button("Confirmation Alert");
        btn5.setFont(Font.font("Tahoma", 20));
        btn5.setOnAction((ActionEvent e) -> {
            informDialog5();
        });

        Button btn6 = new Button("TextInputDialog");
        btn6.setFont(Font.font("Tahoma", 20));
        btn6.setOnAction((ActionEvent e) -> {
            textInputDialog();
        });

        Button btn7 = new Button("ChoiseDialog");
        btn7.setFont(Font.font("Tahoma", 20));
        btn7.setOnAction((ActionEvent e) -> {
            choiceDialog();
        });

        Button btn8 = new Button("FileDialog");
        btn8.setFont(Font.font("Tahoma", 20));
        btn8.setOnAction((ActionEvent e) -> {
            fileDialog(primaryStage);
        });

        Button btn9 = new Button("ColorPicker");
        btn9.setFont(Font.font("Tahoma", 20));
        btn9.setOnAction((ActionEvent e) -> {
            colorPickerExample();
        });

        root.getChildren().addAll(label, btn1, btn2, btn3, btn4, btn5, btn6, btn7, btn8, btn9);

        Scene scene = new Scene(root, 600, 700);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private void colorPickerExample() {
        Stage stage = new Stage();
        stage.setTitle("ColorPicker");

        HBox box = new HBox(20);
        box.setPadding(new Insets(15));

        ColorPicker colorPicker = new ColorPicker();
        colorPicker.setValue(Color.CORAL);

        Text text = new Text("Try the color picker!");
        text.setFont(Font.font("Verdana", 20));
        text.setFill(colorPicker.getValue());

        colorPicker.setOnAction(event -> text.setFill(colorPicker.getValue()));

        box.getChildren().addAll(colorPicker, text);
        Scene scene = new Scene(box, 600, 200);
        stage.setScene(scene);
        stage.show();
    }

    private void fileDialog(Stage primaryStage) {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Open Resource File");
        File file = fileChooser.showOpenDialog(primaryStage);
        label.setText(file.getAbsolutePath());
    }

    private void choiceDialog() {
        List<String> choices = new ArrayList<>();
        choices.add("cat");
        choices.add("dog");
        choices.add("horse");

        ChoiceDialog<String> dialog = new ChoiceDialog<>("dog", choices);
        dialog.setTitle("Choice Dialog");
        dialog.setHeaderText("Look, a Choice Dialog");
        dialog.setContentText("Choose your letter:");
        dialog.getDialogPane().setMinSize(500, 200);

        Optional<String> result = dialog.showAndWait();
        if (result.isPresent())
            label.setText("Your choice: " + result.get());
        else
            label.setText("No choice");
    }

    private void textInputDialog() {
        TextInputDialog dialog = new TextInputDialog();
        dialog.setTitle("Text Input Dialog");
        dialog.setHeaderText("Look, a Text Input Dialog");
        dialog.setContentText("Please enter your name:");
        dialog.getDialogPane().setMinSize(500, 200);

        Optional<String> result = dialog.showAndWait();
        if (result.isPresent())
            label.setText("Your name: " + result.get());
        else
            label.setText("No name");
    }

    private void informDialog5() {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Confirmation Dialog");
        alert.setHeaderText("Look, a Confirmation Dialog");
        alert.setContentText("Are you ok with this?");
        alert.getDialogPane().setMinSize(600, 200);

        Optional<ButtonType> result = alert.showAndWait();
        if (result.get() == ButtonType.OK) {
            label.setText("OK");
        } else {
            label.setText("Cancel");
        }
    }

    private void informDialog4() {
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle("Warning Dialog");
        alert.setHeaderText("Look, a Warning Dialog");
        alert.setContentText("Careful with the next step!");
        alert.getDialogPane().setMinSize(500, 200);
        alert.showAndWait();
    }

    private void informDialog3() {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Information Dialog");
        alert.setHeaderText(null);
        alert.setContentText("A have a great message for you!");
        alert.getDialogPane().setMinSize(500, 200);
        alert.showAndWait();
    }

    private void informDialog2() {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Information Dialog");
        alert.setGraphic(null);
        alert.setContentText("I have a great message for you!");
        alert.getDialogPane().setMinSize(600, 200);
        alert.showAndWait();
    }

    private void informDialog1() {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Information Dialog");
        alert.setHeaderText("Look, an Information Dialog");
        alert.setContentText("I have a great message for you");
        alert.initModality(Modality.NONE);
        alert.showAndWait();
    }
}
