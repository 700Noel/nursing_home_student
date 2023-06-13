package account;

import datastorage.ConnectionBuilder;
import utils.Sha256;

import java.sql.*;
import java.util.Arrays;
import java.util.logging.Logger;

public class UserManager {
    private boolean LoggedIn;
    private int LoggedInUserID;
    Connection connection;

    public UserManager(Connection connection) {
        this.connection = connection;
    }

    private static UserManager instance;

    public boolean isLoggedIn() {
        return LoggedIn;
    }

    public void Login(String username, String password) {
        String password_hashed =  Sha256.getSecurePassword(password, username);
        try {
            Statement statement = connection.createStatement();
            ResultSet result = statement.executeQuery(String.format("SELECT * FROM logins WHERE USERNAME = \'%s\'",  username));
            String s = "";
            while(result.next()) {
                s = result.getString("PASSWORD");
                LoggedInUserID = result.getInt("ID");
            }
            if(s.equals(password_hashed)) {
                LoggedIn = true;
            }
        } catch (SQLException e) {
            Logger.getLogger("loger").entering(getClass().getName(), e.getMessage());
            System.err.println("GO FUCK YOURSELF " + e.getMessage());
        }
    }

    public static UserManager getInstance() {
        if(instance == null) {
            instance = new UserManager(ConnectionBuilder.getConnection());
        }
        return instance;
    }
}
