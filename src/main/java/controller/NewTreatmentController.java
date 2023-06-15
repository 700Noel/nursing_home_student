package controller;

import datastorage.CaregiverDAO;
import datastorage.DAOFactory;
import datastorage.TreatmentDAO;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.stage.Stage;
import model.Caregiver;
import model.Patient;
import model.Treatment;
import utils.DateConverter;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

/**
 * The <code>NewTreatmentController</code> contains the entire logic of the new treatment view. It determines which data is displayed and how to react to events.
 */
public class NewTreatmentController {
    @FXML
    private TableView<Treatment> tableView;
    @FXML
    private Label lblSurname;
    @FXML
    private Label lblFirstname;
    @FXML
    private TextField txtBegin;
    @FXML
    private TextField txtEnd;
    @FXML
    private TextField txtDescription;
    @FXML
    private TextArea taRemarks;
    @FXML
    private DatePicker datepicker;
    @FXML
    private ComboBox<String> comboBox;

    private ObservableList<Treatment> tableviewContent =
            FXCollections.observableArrayList();
    private TreatmentDAO dao;
    private ObservableList<String> myComboBoxData =
            FXCollections.observableArrayList();
    private ArrayList<Caregiver> caregiverList;
    private AllTreatmentController controller;
    private Patient patient;
    private Stage stage;

    /**
     * Initializes the corresponding fields. Is called when clicked on btnNewTreatment.
     */
    public void initialize(AllTreatmentController controller, Stage stage, Patient patient) {
        this.controller= controller;
        this.patient = patient;
        this.stage = stage;
        comboBox.setItems(myComboBoxData);
        createComboBoxDataCaregiver();
        showPatientData();
    }

    /**
     * Sets full name of the Patient, to be shown in the new Window.
     */
    private void showPatientData(){
        this.lblFirstname.setText(patient.getFirstName());
        this.lblSurname.setText(patient.getSurname());
    }

    /**
     * Sets Data that will be show when clicked on ComboBox.
     */
    private void createComboBoxDataCaregiver(){
        CaregiverDAO dao = DAOFactory.getDAOFactory().createCaregiverDAO();
        try {
            caregiverList = (ArrayList<Caregiver>) dao.readAll();
            for (Caregiver caregiver: caregiverList) {
                this.myComboBoxData.add(caregiver.getSurname());
            }
        }catch(SQLException e){
            e.printStackTrace();
        }
    }


    /**
     * Goes through List of all Caregivers.
     * @param surname String which all caregivers will be evaluated with
     * @return caregiver with the corresponding surname.
     */
    private Caregiver searchInListCaregiver(String surname){
        for (int i =0; i<this.caregiverList.size();i++){
            if(this.caregiverList.get(i).getSurname().equals(surname)){
                return this.caregiverList.get(i);
            }
        }
        return null;
    }

    /**
     * handles an add-click-event. Takes in necessary data and calls createTreatment method
     */
    @FXML
    public void handleAdd(){
        LocalDate date = this.datepicker.getValue();
        String s_begin = txtBegin.getText();
        LocalTime begin = DateConverter.convertStringToLocalTime(txtBegin.getText());
        LocalTime end = DateConverter.convertStringToLocalTime(txtEnd.getText());
        String description = txtDescription.getText();
        String remarks = taRemarks.getText();
        if(description.equals("")) {
            return;
        }
        try{
            String c = this.comboBox.getSelectionModel().getSelectedItem();
            Caregiver caregiver = searchInListCaregiver(c);
            Treatment treatment = new Treatment(patient.getPid(), caregiver.getId(), date,
                    begin, end, description, remarks, true);
            createTreatment(treatment);
            controller.readAllAndShowInTableView();
            stage.close();
        }
        catch(NullPointerException e){
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Information");
            alert.setHeaderText("Pfleger für die Behandlung fehlt!");
            alert.setContentText("Wählen Sie über die Combobox einen Pfleger aus!");
            alert.showAndWait();
        }

    }


    /**
     * Creates a treatment and calls the create method in the {@link TreatmentDAO}
     * @param treatment treatment which will be created in the dao
     */
    private void createTreatment(Treatment treatment) {
        TreatmentDAO dao = DAOFactory.getDAOFactory().createTreatmentDAO();
        try {
            dao.create(treatment);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * handles a cancel-click-event. Closes the window
     */
    @FXML
    public void handleCancel(){
        stage.close();
    }
}