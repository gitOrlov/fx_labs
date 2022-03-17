package example.topic_2_lab4;

import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;

class PetView {
    private PetModel pet;
    private GridPane grid;

    private Text petType = new Text();
    private Text petName = new Text();
    private Text ownerName = new Text();
    private Text petAge = new Text();

    PetView(PetModel pet) {
        createPane();
        setPet(pet);
    }

    private void createPane() {
        grid = new GridPane();
        grid.setAlignment(Pos.CENTER);
        grid.setHgap(10);
        grid.setVgap(10);
        grid.setPadding(new Insets(25, 25, 25, 25));

        //----petOwner
        Label petOwnerTitle = new Label("Pet's owner:");
        petOwnerTitle.setFont(Font.font("Tahoma", FontWeight.NORMAL, 20));
        grid.add(petOwnerTitle, 0, 0);

        ownerName.setFont(Font.font("Tahoma", FontWeight.NORMAL, 20));
        GridPane.setHalignment(ownerName, HPos.CENTER);
        grid.add(ownerName, 0, 1, 2, 1);

        //----petType
        Label petTypeTitle = new Label("Pet's type:");
        petTypeTitle.setFont(Font.font("Tahoma", FontWeight.NORMAL, 20));
        grid.add(petTypeTitle, 0, 2);

        petType.setFont(Font.font("Tahoma", FontWeight.NORMAL, 20));
        GridPane.setHalignment(petType, HPos.CENTER);
        grid.add(petType, 0, 3, 2, 1);

        //----petName
        Label petNameTitle = new Label("Pet's name:");
        petNameTitle.setFont(Font.font("Tahoma", FontWeight.NORMAL, 20));
        grid.add(petNameTitle, 0, 4);

        petName.setFont(Font.font("Tahoma", FontWeight.NORMAL, 20));
        GridPane.setHalignment(petName, HPos.CENTER);
        grid.add(petName, 0, 5, 2, 1);

        //----petAge
        Label ageTitle = new Label("Paet's age");
        ageTitle.setFont(Font.font("Tahoma", FontWeight.NORMAL, 20));
        grid.add(ageTitle, 0, 6);

        petAge.setFont(Font.font("Tahoma", FontWeight.NORMAL, 20));
        GridPane.setHalignment(petAge, HPos.CENTER);
        grid.add(petAge, 0, 7);
    }

    private Label getLabelImage() throws FileNotFoundException {
        Label labelImage = new Label();
        labelImage.setPrefSize(210, 270);

        File file = new File("C:\\Users\\user-adm\\IdeaProjects\\fx\\src\\main\\resources\\example\\topic2_lab3\\cat.jpg");
        InputStream is = new FileInputStream(file);

        Image imageCat = new Image(is);
        ImageView imvCat = new ImageView(imageCat);
        labelImage.setGraphic(imvCat);
        return labelImage;
    }

    private void setPet(PetModel pet) {
        this.pet = pet;
        petName.textProperty().bind(this.pet.nameStringProperty());
        petAge.textProperty().bind(this.pet.ageProperty().asString());
        ownerName.setText(pet.getOwnerName());
        petType.setText(pet.getType());
    }

    Node getPane() {
        return grid;
    }

    void setInform() {
        petType.setText("Pet type is: " + pet.getType());
        petName.setText("Pet name is: " + pet.getName());
        ownerName.setText("Pet owner is: " + pet.getOwnerName());
        petAge.setText("Pet age = " + pet.getYearsOld() + " years, " + pet.getMonthOld() + " month");
    }
}
