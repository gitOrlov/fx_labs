package example.topic_2_lab4;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Orientation;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Separator;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.stage.Stage;

import java.io.*;

public class MainController extends Application {
    @Override
    public void start(Stage primaryStage) throws FileNotFoundException {
        primaryStage.setTitle("Pet Age");

        PetModel model = new PetModel("Cat", "Barsick", "Eugene", 2.1);
        HBox root = new HBox(10);

        PetView petView = new PetView(model);
        root.getChildren().add(petView.getPane());
        root.getChildren().add(new Separator(Orientation.VERTICAL));
        root.getChildren().add(editBlock(model));

        Scene scene = new Scene(root, 900, 300);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private VBox editBlock(PetModel model) {
        VBox editPane = new VBox(10);
        editPane.setPadding(new Insets(20));

        Label labelTitle = new Label("Enter the pet's name\n and age");
        labelTitle.setFont(new Font(20));

        TextField editName = new TextField();
        editName.setFont(Font.font(20));
        editName.setPrefSize(150, 40);

        TextField editAge = new TextField();
        editAge.setFont(Font.font(20));
        editAge.setPrefSize(150, 40);

        Button btn = new Button("Calculate");
        btn.setFont(Font.font(20));
        btn.setOnAction(event -> {
            model.setName(editName.getText());
            setAge(model, editAge);
        });

        editPane.getChildren().addAll(labelTitle, editName, editAge, btn);
        return editPane;
    }

    private void setAge(PetModel model, TextField editAge) {
        double age = 0.0;

        String editAgeText = editAge.getText();
        if (editAgeText == null || editAgeText.isEmpty())
            return;

        try {
            age = Double.parseDouble(editAgeText);
        } catch (NumberFormatException e) {
            editAge.setText("Wrong format age!");
        }

        model.setAge(age);
    }

    private PetModel createPet() {
        String type = null;
        String name = null;
        String ownerName = null;
        byte year = 0;
        byte month = 0;
        double age = 0;

        File file = new File("C:\\Users\\user-adm\\IdeaProjects\\fx\\src\\main\\resources\\example\\topic2_lab3\\info.txt");
        try {
            FileReader fileReader = new FileReader(file);
            BufferedReader bufferedReader = new BufferedReader(fileReader);
            type = bufferedReader.readLine();
            name = bufferedReader.readLine();
            ownerName = bufferedReader.readLine();
            year = Byte.parseByte(bufferedReader.readLine());
            month = Byte.parseByte(bufferedReader.readLine());
        } catch (IOException e) {
            e.printStackTrace();
        }

        return new PetModel(type, name, ownerName, age);
    }

    public static void main(String[] args) {
        launch();
    }
}
