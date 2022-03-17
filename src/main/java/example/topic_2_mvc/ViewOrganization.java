package example.topic_2_mvc;

import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;

import java.time.format.DateTimeFormatter;

public class ViewOrganization {
    private Organization org;
    private GridPane grid;
    private Text nameOrg;
    private Text holidayOrg;
    private Text cashBonus;

    private void createPane() {
        grid = new GridPane();
        grid.setAlignment(Pos.CENTER);
        grid.setHgap(10);
        grid.setVgap(10);
        grid.setPadding(new Insets(25, 25, 25, 25));

        nameOrg = new Text();
        nameOrg.setFont(Font.font("Tahoma", FontWeight.NORMAL, 20));

        GridPane.setHalignment(nameOrg, HPos.CENTER);
        grid.add(nameOrg, 0, 0, 2, 1);

        holidayOrg = new Text();
        holidayOrg.setFont(Font.font("Tahoma", FontWeight.NORMAL, 20));
        grid.add(holidayOrg, 0, 1, 2, 1);

        Label cashBonusTitle = new Label("Bonus");
        cashBonusTitle.setFont(Font.font("Tahoma", FontWeight.NORMAL, 20));
        grid.add(cashBonusTitle, 0, 2);

        cashBonus = new Text();
        cashBonus.setFont(Font.font("Tahoma", FontWeight.NORMAL, 20));
        grid.add(cashBonus, 1, 2);
    }

    public GridPane getPane() {
        return grid;
    }

    public void setOrganization(Organization org) {
        this.org = org;
        nameOrg.textProperty().bind(this.org.nameStringProperty());
//        Bindings.bindBidirectional(nameOrg.textProperty(), org.nameStringProperty());
        holidayOrg.setText(org.getHoliday() + " - " + org.getDate().format(DateTimeFormatter.ofPattern("dd.MM.uuu")));
        cashBonus.textProperty().bind(this.org.bonusProperty().asString());
    }

    public ViewOrganization(Organization org) {
        createPane();
        setOrganization(org);
    }
}
