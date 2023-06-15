package model;

import java.util.ArrayList;
import java.util.List;

/**
 * Caregiver nurse residents in a NURSING home.
 */
public class Caregiver extends Person{

    private long id;
    private String telephone;

    private boolean shown;
    private List<Treatment> allTreatments = new ArrayList<Treatment>();

    /**
     * constructs a caregiver from the given params.
     * @param firstName
     * @param surname
     * @param telephone
     * @param shown
     */
    public Caregiver(String firstName, String surname, String telephone, boolean shown) {
        super(firstName, surname);
        this.telephone = telephone;
        this.shown = shown;
    }

    /**
     * constructs a caregiver from the given params.
     * @param id
     * @param firstName
     * @param surname
     * @param telephone
     * @param shown
     */
    public Caregiver(long id, String firstName, String surname, String telephone, boolean shown) {
        super(firstName, surname);
        this.id = id;
        this.telephone = telephone;
        this.shown = shown;
    }

    /**
     *
     * @return caregiver id
     */
    public long getId() {
        return id;
    }

    /**
     *
     * @return telephone number as a string
     */
    public String getTelephone() {
        return telephone;
    }

    /**
     * @param telephone as string
     */
    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    /**
     * @return shown status as boolean
     */
    public boolean isShown() {
        return shown;
    }

    /**
     * @param shown as boolean
     */
    public void setShown(boolean shown) {
        this.shown = shown;
    }

    /**
     * adds a treatment to the treatment-list, if it does not already contain it.
     * @param m Treatment
     * @return true if the treatment was not already part of the list. otherwise false
     */
    public boolean add(Treatment m) {
        if (!this.allTreatments.contains(m)) {
            this.allTreatments.add(m);
            return true;
        }
        return false;
    }

    /**
     *
     * @return string-representation of the caregiver
     */
    public String toString() {
        return "Caregiver" + "\nMNID: " + this.id +
                "\nFirstname: " + this.getFirstName() +
                "\nSurname: " + this.getSurname() +
                "\nBirthday: " + this.telephone +
                "\n";
    }
}
