package model;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Caretaker extends Person{

    private long cid;
    private String dateOfBirth;
    private String careLevel;
    private String roomnumber;
    private List<Treatment> allTreatments = new ArrayList<Treatment>();
    public Caretaker(String firstName, String surname) {
        super(firstName, surname);
    }
}
