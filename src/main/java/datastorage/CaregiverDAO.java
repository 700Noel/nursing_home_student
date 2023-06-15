package datastorage;

import model.Caregiver;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;

/**
 * Implements the Interface <code>DAOImp</code>. Overrides methods to generate specific caregiver-SQL-queries.
 */
public class CaregiverDAO extends DAOimp<Caregiver>{

    /**
     * constructs Object. Calls the Constructor from <code>DAOImp</code> to store the connection.
     * @param conn
     */
    public CaregiverDAO(Connection conn) {
        super(conn);
    }

    /**
     * generates a <code>INSERT INTO</code>-Statement for a given caregiver
     * @param caregiver for which a specific INSERT INTO is to be created
     * @return <code>String</code> with the generated SQL.
     */
    @Override
    protected String getCreateStatementString(Caregiver caregiver) {
        return String.format("INSERT INTO caregiver (firstname, surname, telephone, show, blockeddate) VALUES ('%s', '%s', '%s', '%s', %s)",
                caregiver.getFirstName(), caregiver.getSurname(), caregiver.getTelephone(), true, null);
    }

    /**
     * generates a <code>select</code>-Statement for a given key
     * @param key for which a specific SELECT is to be created
     * @return <code>String</code> with the generated SQL.
     */
    @Override
    protected String getReadByIDStatementString(long key) {
        return String.format("SELECT * FROM caregiver WHERE id = %d AND show = true", key);
    }

    /**
     * maps a <code>ResultSet</code> to a <code>Caregiver</code>
     * @param result ResultSet with a single row. Columns will be mapped to a caregiver-object.
     * @return caregiver with the data from the resultSet.
     */
    @Override
    protected Caregiver getInstanceFromResultSet(ResultSet result) throws SQLException {
        Caregiver caregiver = null;
        caregiver = new Caregiver(result.getInt(1), result.getString(2),
                result.getString(3), result.getString(4), result.getBoolean(5));
        return caregiver;
    }

    /**
     * generates a <code>SELECT</code>-Statement for all caregivers.
     * @return <code>String</code> with the generated SQL.
     */
    @Override
    protected String getReadAllStatementString() {
        return "SELECT * FROM caregiver";
    }

    /**
     * generates a <code>SELECT</code>-Statement for all caregivers, where the show variable is true.
     * @return <code>String</code> with the generated SQL.
     */
    @Override
    protected String getReadAllUnblockedStatementString() {
        return "SELECT * FROM caregiver WHERE show = true";
    }

    /**
     * maps a <code>ResultSet</code> to a <code>Caregiver-List</code>
     * @param result ResultSet with a multiple rows. Data will be mapped to caregiver-object.
     * @return ArrayList with caregivers from the resultSet.
     */
    @Override
    protected ArrayList<Caregiver> getListFromResultSet(ResultSet result) throws SQLException {
        ArrayList<Caregiver> list = new ArrayList<Caregiver>();
        Caregiver caregiver = null;
        while (result.next()) {
            caregiver = new Caregiver(result.getInt(1), result.getString(2),
                    result.getString(3), result.getString(4), result.getBoolean(5));
            list.add(caregiver);
        }
        return list;
    }

    /**
     * generates a <code>UPDATE</code>-Statement for a given caregiver
     * @param caregiver for which a specific update is to be created
     * @return <code>String</code> with the generated SQL.
     */
    @Override
    protected String getUpdateStatementString(Caregiver caregiver) {
        return String.format("UPDATE caregiver SET firstname = '%s', surname = '%s', telephone = '%s', SHOW = '%s', blockeddate = %s  WHERE id = %d",
                caregiver.getFirstName(), caregiver.getSurname(), caregiver.getTelephone(), caregiver.isShown(), caregiver.isShown() ? "NULL" : "'" + new SimpleDateFormat("yyyy-MM-dd").format(Calendar.getInstance().getTime()) + "'",caregiver.getId());
    }

    /**
     * generates a <code>delete</code>-Statement for a given key
     * @param key for which a specific DELETE is to be created
     * @return <code>String</code> with the generated SQL.
     */
    @Override
    protected String getDeleteStatementString(long key) {
        return String.format("Delete FROM caregiver WHERE id=%d", key);
    }
}
