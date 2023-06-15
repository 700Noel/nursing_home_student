package controller;

import datastorage.DAOFactory;
import datastorage.PatientDAO;
import datastorage.TreatmentDAO;
import datastorage.CaregiverDAO;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.stage.Stage;
import model.Caregiver;
import model.Patient;
import model.Treatment;
import utils.DateConverter;
import java.sql.SQLException;
import java.time.LocalDate;

/**
 * The <code>TreatmentController</code> contains the entire logic of the treatment view. It determines which data is displayed and how to react to events.
 */
public class TreatmentController {
    @FXML
    private Label lblPatientName;
    @FXML
    private Label lblCaregiverName;
    @FXML
    private Label lblCarelevel;
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
    private Button btnChange;
    @FXML
    private Button btnCancel;

    private AllTreatmentController controller;
    private Stage stage;
    private Patient patient;
    private Caregiver caregiver;
    private Treatment treatment;

    /**
     * Initializes the corresponding fields. Is called as soon as the corresponding FXML file is to be displayed.
     * @param controller saves the controller of the original AllTreatmentController to later display the changes there
     * @param stage the stage initialized in the AllTreatmentController
     * @param treatment treatment, which needs to be displayed
     */
    public void initializeController(AllTreatmentController controller, Stage stage, Treatment treatment) {
        this.stage = stage;
        this.controller= controller;
        PatientDAO pDao = DAOFactory.getDAOFactory().createPatientDAO();
        CaregiverDAO cDao = DAOFactory.getDAOFactory().createCaregiverDAO();
        try {
            this.patient = pDao.read((int) treatment.getPid());
            this.caregiver = cDao.read((int) treatment.getCid());
            this.treatment = treatment;
            showData();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * displays full name of the patient and caregiver and full data of the treatment
     */
    private void showData(){
        this.lblPatientName.setText(patient.getSurname()+", "+patient.getFirstName());
        this.lblCaregiverName.setText(caregiver.getSurname()+", "+caregiver.getFirstName());
        this.lblCarelevel.setText(patient.getCareLevel());
        LocalDate date = DateConverter.convertStringToLocalDate(treatment.getDate());
        this.datepicker.setValue(date);
        this.txtBegin.setText(this.treatment.getBegin());
        this.txtEnd.setText(this.treatment.getEnd());
        this.txtDescription.setText(this.treatment.getDescription());
        this.taRemarks.setText(this.treatment.getRemarks());
    }

    /**
     * handle changes made in the treatment fields, calls the doUpdate method, calls the readAllAndShowInTableView in the {@link AllTreatmentController}
     * and closes the window
     */
    @FXML
    public void handleChange(){
        this.treatment.setDate(this.datepicker.getValue().toString());
        this.treatment.setBegin(txtBegin.getText());
        this.treatment.setEnd(txtEnd.getText());
        this.treatment.setDescription(txtDescription.getText());
        this.treatment.setRemarks(taRemarks.getText());
        doUpdate();
        controller.readAllAndShowInTableView();
        stage.close();
    }

    /**
     * Calls the update method in the {@link TreatmentDAO}, with the new treatment data
     */
    private void doUpdate(){
        TreatmentDAO dao = DAOFactory.getDAOFactory().createTreatmentDAO();
        try {
            dao.update(treatment);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * closes window
     */
    @FXML
    public void handleCancel(){
        stage.close();
    }
}