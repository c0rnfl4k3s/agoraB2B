package GUI;

import Accountsystem.AccountDTO;
import Datenbank.AccountDAO;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.URL;
import java.util.ResourceBundle;

public class LoginWindowController implements Initializable {

    @FXML
    AnchorPane loginPane;
    @FXML
    PasswordField pwTextfield;
    @FXML
    TextField userTextfield;

    FXMLLoader loader; // Um auf Controller der eingelesenen Panes zugreifen zu können

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }

    @FXML
    public void showNewAccountScene() throws IOException {
        loader = new FXMLLoader(getClass().getResource("register_window.fxml"));
        loginPane.getChildren().setAll((Parent)loader.load());
        loginPane.getScene().getWindow().setHeight(626+40);
        loginPane.getScene().getWindow().setWidth(648+16);
    }

    @FXML
    public void login() throws IOException{
        AccountDTO activeAccountDTO = null;
        // Login Daten checken und AccountDTO-Objekt auslesen:
        try {

            // TODO: activeAccountDTO mit Datenbankeinträgen initialisieren! (möglichst ohne Konstruktoraufruf wegen Counter)


            AccountDAO activeAccountDAO = new AccountDAO();
            activeAccountDTO = activeAccountDAO.accountAbrufen(userTextfield.getText());
            if(!activeAccountDTO.getPasswort().equals(pwTextfield.getText())) {
                throw new IllegalArgumentException();
            }

        } catch (Exception e) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setHeaderText("Ungültige Userdaten.");
//            alert.setHeight(10);
            alert.showAndWait();
            return;
        }


        // Seite laden:
        loader = new FXMLLoader(getClass().getResource("mainPane.fxml"));
        loginPane.getChildren().setAll((Parent)loader.load()); // Immer erst load aufrufen, bevor man auf den Controller zugreift !!
        ((MainPaneController)loader.getController()).setActiveAccountDTO(activeAccountDTO); // AccountDTO-Objekt an Controller übergeben (geht vlt auch einfacher, von innerhalb des Controllers getten?)
        loginPane.getScene().getWindow().setHeight(390+48);
        loginPane.getScene().getWindow().setWidth(600+16);
    }
}
