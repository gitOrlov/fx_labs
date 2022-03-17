package example.topic_2_mvc;

import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

import java.time.LocalDate;

public class Organization {
    private StringProperty name;

    public StringProperty nameStringProperty() {
        if (name == null) {
            name = new SimpleStringProperty();
        }
        return name;
    }

    public final void setName(String value) {
        nameStringProperty().set(value);
    }

    public final String getName() {
        return nameStringProperty().get();
    }

    private int personnel;
    private String holiday;
    private LocalDate date;

    private DoubleProperty bonus;

    public DoubleProperty bonusProperty() {
        if (bonus == null) {
            bonus = new SimpleDoubleProperty();
        }
        return bonus;
    }

    public void setBonus(double bonus) {
        bonusProperty().set(bonus);
    }

    public double getBonus() {
        return bonusProperty().get();
    }

    public int getPersonnel() {
        return personnel;
    }

    public String getHoliday() {
        return holiday;
    }

    public LocalDate getDate() {
        return date;
    }

    public Organization(String name, int personnel, String holiday, LocalDate date, double bonus) {
        setName(name);
        this.personnel = personnel;
        this.holiday = holiday;
        this.date = date;
        setBonus(bonus);
    }
}
