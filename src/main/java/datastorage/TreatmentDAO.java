package datastorage;

import model.Treatment;
import utils.DateConverter;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

/**
 * Implements the Interface <code>DAOImp</code>. Overrides methods to generate specific treatment-SQL-queries.
 */
public class TreatmentDAO extends DAOimp<Treatment> {

    /**
     * constructs Object. Calls the Constructor from <code>DAOImp</code> to store the connection.
     * @param conn
     */
    public TreatmentDAO(Connection conn) {
        super(conn);
    }

    /**
     * generates a <code>INSERT INTO</code>-Statement for a given treatment
     * @param treatment for which a specific INSERT INTO is to be created
     * @return <code>String</code> with the generated SQL.
     */
    @Override
    protected String getCreateStatementString(Treatment treatment) {
        return String.format("INSERT INTO treatment (pid, cid, treatment_date, begin, end, description, remarks, show, blockeddate) VALUES " +
                "(%d, '%s', '%s', '%s', '%s', '%s', '%s', '%s', %s)", treatment.getPid(), treatment.getCid(), treatment.getDate(),
                treatment.getBegin(), treatment.getEnd(), treatment.getDescription(),
                treatment.getRemarks(), true, null);
    }

    /**
     * generates a <code>select</code>-Statement for a given key
     * @param key for which a specific SELECT is to be created
     * @return <code>String</code> with the generated SQL.
     */
    @Override
    protected String getReadByIDStatementString(long key) {
        return String.format("SELECT * FROM treatment WHERE tid = %d", key);
    }

    /**
     * maps a <code>ResultSet</code> to a <code>Treatment</code>
     * @param result ResultSet with a single row. Columns will be mapped to a treatment-object.
     * @return treatment with the data from the resultSet.
     */
    @Override
    protected Treatment getInstanceFromResultSet(ResultSet result) throws SQLException {
        LocalDate date = DateConverter.convertStringToLocalDate(result.getString(3));
        LocalTime begin = DateConverter.convertStringToLocalTime(result.getString(4));
        LocalTime end = DateConverter.convertStringToLocalTime(result.getString(5));
        Treatment m = new Treatment(result.getLong(1),result.getLong(2),
                result.getLong(8), date, begin, end, result.getString(6),
                result.getString(7), result.getBoolean(9));
        return m;
    }

    /**
     * generates a <code>SELECT</code>-Statement for all treatments.
     * @return <code>String</code> with the generated SQL.
     */
    @Override
    protected String getReadAllStatementString() {
        return "SELECT * FROM treatment";
    }

    /**
     * generates a <code>SELECT</code>-Statement for all treatments, where the show variable is true.
     * @return <code>String</code> with the generated SQL.
     */
    @Override
    protected String getReadAllUnblockedStatementString() {
        return "SELECT * FROM treatment WHERE show = true";
    }

    /**
     * maps a <code>ResultSet</code> to a <code>Treatment-List</code>
     * @param result ResultSet with a multiple rows. Data will be mapped to treatment-object.
     * @return ArrayList with treatments from the resultSet.
     */
    @Override
    protected ArrayList<Treatment> getListFromResultSet(ResultSet result) throws SQLException {
        ArrayList<Treatment> list = new ArrayList<Treatment>();
        Treatment t = null;
        while (result.next()) {
            LocalDate date = DateConverter.convertStringToLocalDate(result.getString(3));
            LocalTime begin = DateConverter.convertStringToLocalTime(result.getString(4));
            LocalTime end = DateConverter.convertStringToLocalTime(result.getString(5));
            t = new Treatment(result.getLong(1), result.getLong(2),
                    result.getLong(8), date, begin, end, result.getString(6),
                    result.getString(7), result.getBoolean(9));
            list.add(t);
        }
        return list;
    }

    /**
     * generates a <code>UPDATE</code>-Statement for a given treatment
     * @param treatment for which a specific update is to be created
     * @return <code>String</code> with the generated SQL.
     */
    @Override
    protected String getUpdateStatementString(Treatment treatment) {
        return String.format("UPDATE treatment SET pid = %d, cid = %d, treatment_date ='%s', begin = '%s', end = '%s'," +
                "description = '%s', remarks = '%s', show = '%s', blockeddate = %s WHERE tid = %d", treatment.getPid(), treatment.getCid(), treatment.getDate(),
                treatment.getBegin(), treatment.getEnd(), treatment.getDescription(), treatment.getRemarks(), treatment.isShown(),
                treatment.isShown() ? "NULL" : "'" + new SimpleDateFormat("yyyy-MM-dd").format(Calendar.getInstance().getTime()) + "'",
                treatment.getTid());
    }

    /**
     * generates a <code>delete</code>-Statement for a given key
     * @param key for which a specific DELETE is to be created
     * @return <code>String</code> with the generated SQL.
     */
    @Override
    protected String getDeleteStatementString(long key) {
        return String.format("Delete FROM treatment WHERE tid= %d", key);
    }

    /**
     * calls getReadAllTreatmentsOfOnePatientByPid method, with given pid
     * @param pid id of the patient
     * @return list of Treatments
     * @throws SQLException
     */
    public List<Treatment> readTreatmentsByPid(long pid) throws SQLException {
        ArrayList<Treatment> list = new ArrayList<Treatment>();
        Treatment object = null;
        Statement st = conn.createStatement();
        ResultSet result = st.executeQuery(getReadAllTreatmentsOfOnePatientByPid(pid));
        list = getListFromResultSet(result);
        return list;
    }

    /**
     * generates a <code>SELECT</code>-Statement for all treatments, where the pid matches the given id of the patient.
     * @param pid pid id of the patient
     * @return <code>String</code> with the generated SQL.
     */
    private String getReadAllTreatmentsOfOnePatientByPid(long pid){
        return String.format("SELECT * FROM treatment WHERE pid = %d", pid);
    }

    /**
     * generates a <code>delete</code>-Statement for a given key
     * @param key of the id of a patient for which a specific DELETE is to be executed
     * @throws SQLException
     */
    public void deleteByPid(long key) throws SQLException {
        Statement st = conn.createStatement();
        st.executeUpdate(String.format("Delete FROM treatment WHERE pid= %d", key));
    }
}