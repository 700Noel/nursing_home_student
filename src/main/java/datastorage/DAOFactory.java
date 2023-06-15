package datastorage;

/**
 * creates the DAOs for the Patient, Treatment and Caregiver
 */
public class DAOFactory {

    private static DAOFactory instance;

    /**
     * privater constructor to prevent free initializing of a new object
     */
    private DAOFactory() {

    }


    /**
     * Singleton which instantiates a new Object of this class if none already exists
     * @return instance of a new object of this class
     */
    public static DAOFactory getDAOFactory() {
        if (instance == null) {
            instance = new DAOFactory();
        }
        return instance;
    }

    /**
     * creates a new instance of the TreatmentDAO
     * @return instance of the TreatmentDAO
     */
    public TreatmentDAO createTreatmentDAO() {
        return new TreatmentDAO(ConnectionBuilder.getConnection());
    }

    /**
     * creates a new instance of the PatientDAO
     * @return instance of the PatientDAO
     */
    public PatientDAO createPatientDAO() {
        return new PatientDAO(ConnectionBuilder.getConnection());
    }

    /**
     * creates a new instance of the CaregiverDAO
     * @return instance of the CaregiverDAO
     */
    public CaregiverDAO createCaregiverDAO() {
        return new CaregiverDAO(ConnectionBuilder.getConnection());
    }
}