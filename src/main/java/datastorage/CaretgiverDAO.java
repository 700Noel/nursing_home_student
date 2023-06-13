package datastorage;

import model.Caregiver;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class CaretgiverDAO extends DAOimp<Caregiver>{
    public CaretgiverDAO(Connection conn) {
        super(conn);
    }

    @Override
    protected String getCreateStatementString(Caregiver caregiver) {
        return String.format("INSERT INTO caregiver (firstname, surname, telephone) VALUES ('%s', '%s', '%s')",
                caregiver.getFirstName(), caregiver.getSurname(), caregiver.getTelephone());
    }

    @Override
    protected String getReadByIDStatementString(long key) {
        return String.format("SELECT * FROM caregiver WHERE id = %d", key);
    }

    @Override
    protected Caregiver getInstanceFromResultSet(ResultSet result) throws SQLException {
        Caregiver caregiver = null;
        caregiver = new Caregiver(result.getInt(1), result.getString(2),
                result.getString(3), result.getString(4));
        return caregiver;
    }

    @Override
    protected String getReadAllStatementString() {
        return "SELECT * FROM caregiver";
    }

    @Override
    protected ArrayList<Caregiver> getListFromResultSet(ResultSet result) throws SQLException {
        ArrayList<Caregiver> list = new ArrayList<Caregiver>();
        Caregiver caregiver = null;
        while (result.next()) {
            caregiver = new Caregiver(result.getInt(1), result.getString(2),
                    result.getString(3), result.getString(4));
            list.add(caregiver);
        }
        return list;
    }

    @Override
    protected String getUpdateStatementString(Caregiver caregiver) {
        return String.format("UPDATE caregiver SET firstname = '%s', surname = '%s', telephone = '%s' WHERE id = %d",
                caregiver.getFirstName(), caregiver.getSurname(), caregiver.getTelephone(), caregiver.getId());
    }

    @Override
    protected String getDeleteStatementString(long key) {
        return String.format("Delete FROM caregiver WHERE id=%d", key);
    }
}
