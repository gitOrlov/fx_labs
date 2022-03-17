package example.topic_2_lab4;

import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

class PetModel {
    private StringProperty name;

    public StringProperty nameStringProperty() {
        if (name == null)
            name = new SimpleStringProperty();

        return name;
    }

    public final void setName(String value) {
        nameStringProperty().set(value);
    }

    public final String getName() {
        return nameStringProperty().get();
    }

    private String type;
    private String ownerName;
    private byte yearsOld;
    private byte monthOld;

    private DoubleProperty age;

    public DoubleProperty ageProperty() {
        if (age == null)
            age = new SimpleDoubleProperty();

        return age;
    }

    public void setAge(double age) {
        ageProperty().set(age);
    }

    PetModel(String type, String name, String ownerName, double age) {
        this.type = type;
        setName(name);
        this.ownerName = ownerName;
        setAge(age);
    }

    String getType() {
        return type;
    }

    String getOwnerName() {
        return ownerName;
    }

    byte getYearsOld() {
        return yearsOld;
    }

    byte getMonthOld() {
        return monthOld;
    }
}
