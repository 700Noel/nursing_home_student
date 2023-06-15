package datastorage;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public abstract class DAOimp<T> implements DAO<T>{
    protected Connection conn;

    /**
     * sets Connection
     * @param conn
     */
    public DAOimp(Connection conn) {
        this.conn = conn;
    }

    /**
     * creates a new Statement of the given Object
     * @param t
     * @throws SQLException
     */
    @Override
    public void create(T t) throws SQLException {
        Statement st = conn.createStatement();
        st.executeUpdate(getCreateStatementString(t));
    }

    /**
     * calls getReadByIDStatementString method and makes a new object out of the result
     * @param key id after which is being evaluated
     * @return found object
     * @throws SQLException
     */
    @Override
    public T read(long key) throws SQLException {
        T object = null;
        Statement st = conn.createStatement();
        ResultSet result = st.executeQuery(getReadByIDStatementString(key));
        if (result.next()) {
            object = getInstanceFromResultSet(result);
        }
        return object;
    }

    /**
     * calls getReadAllStatementString method and makes a new list of objects out of the result
     * @return list of objects
     * @throws SQLException
     */
    @Override
    public List<T> readAll() throws SQLException {
        ArrayList<T> list = new ArrayList<T>();
        T object = null;
        Statement st = conn.createStatement();
        ResultSet result = st.executeQuery(getReadAllStatementString());
        list = getListFromResultSet(result);
        return list;
    }

    /**
     * calls getReadAllUnblockedStatementString method and makes a new list of objects out of the result
     * @return list of objects
     * @throws SQLException
     */
    @Override
    public List<T> readAllUnblocked() throws SQLException {
        ArrayList<T> list = new ArrayList<T>();
        T object = null;
        Statement st = conn.createStatement();
        ResultSet result = st.executeQuery(getReadAllUnblockedStatementString());
        list = getListFromResultSet(result);
        return list;
    }

    /**
     * calls getUpdateStatementString method, with object
     * @param t Object which gets updated
     * @throws SQLException
     */
    @Override
    public void update(T t) throws SQLException {
        Statement st = conn.createStatement();
        st.executeUpdate(getUpdateStatementString(t));
    }

    /**
     * calls getDeleteStatementString method, with key
     * @param key id of Object which gets deleted
     * @throws SQLException
     */
    @Override
    public void deleteById(long key) throws SQLException {
        Statement st = conn.createStatement();
        st.executeUpdate(getDeleteStatementString(key));
    }

    protected abstract String getCreateStatementString(T t);

    protected abstract String getReadByIDStatementString(long key);

    protected abstract T getInstanceFromResultSet(ResultSet set) throws SQLException;

    protected abstract String getReadAllStatementString();

    protected abstract String getReadAllUnblockedStatementString();

    protected abstract ArrayList<T> getListFromResultSet(ResultSet set) throws SQLException;

    protected abstract String getUpdateStatementString(T t);

    protected abstract String getDeleteStatementString(long key);

    public Connection getConn() {
        return conn;
    }
}
