package model;

import java.util.ArrayList;
import java.util.List;

public class Caregiver extends Person{

    private long id;
    private String telephone;

    private boolean shown;
    private List<Treatment> allTreatments = new ArrayList<Treatment>();
    public Caregiver(String firstName, String surname, String telephone, boolean shown) {
        super(firstName, surname);
        this.telephone = telephone;
        this.shown = shown;
    }

    public Caregiver(long id, String firstName, String surname, String telephone, boolean shown) {
        super(firstName, surname);
        this.id = id;
        this.telephone = telephone;
        this.shown = shown;
    }

    public long getId() {
        return id;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public boolean isShown() {
        return shown;
    }

    public void setShown(boolean shown) {
        this.shown = shown;
    }

    public boolean add(Treatment m) {
        if (!this.allTreatments.contains(m)) {
            this.allTreatments.add(m);
            return true;
        }
        return false;
    }

    public String toString() {
        return "Caregiver" + "\nMNID: " + this.id +
                "\nFirstname: " + this.getFirstName() +
                "\nSurname: " + this.getSurname() +
                "\nBirthday: " + this.telephone +
                "\n";
    }
}
