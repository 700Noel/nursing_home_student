package controller;

import account.UserManager;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import utils.Sha256;

import java.io.IOException;

public class LoginWindowController {

    @FXML
    private BorderPane mainBorderPane;

    @FXML
    TextField txtUsername;

    @FXML
    TextField txtPassword;

    /**
     *  handles a login-click-event. Checks the user credentials and changes the window to the main view if the credentials are correct else wise shows an alert
     */
    @FXML
    private void handleLogin(ActionEvent e) {
        UserManager.getInstance().Login(txtUsername.getText(), txtPassword.getText());

        if(UserManager.getInstance().isLoggedIn()) {
            FXMLLoader loader = new FXMLLoader(Main.class.getResource("/MainWindowView.fxml"));
            try {
                mainBorderPane.setCenter(loader.load());
            } catch (IOException ex) {
                ex.printStackTrace();
            }
            MainWindowController controller = loader.getController();
        } else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText("Password or Username was incorrect");
            alert.showAndWait();
        }
    }
}
