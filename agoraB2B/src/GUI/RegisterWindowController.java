package GUI;

import Accountsystem.*;
import Datenbank.AccountDAO;
import Datenbank.AccountInterface;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;

import java.io.*;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.Set;

public class RegisterWindowController implements Initializable {

    @FXML
    AnchorPane regPane;
    @FXML
    ChoiceBox anredeChoiceBox;
    @FXML
    TextField vornameTextfield, nachnameTextfield, userTextfield, emailTextfield, firmaTextfield, telTextfield, ibanTextfield,
        banknameTextfield, bicTextfield, strasseTextfield, hausnummerTextfield, plzTextfield, stadtTextfield, landTextfield;
    @FXML
    PasswordField pw1Textfield, pw2Textfield;

    Alert alert = new Alert(Alert.AlertType.INFORMATION); // Fehlermeldung-Fenster

    FXMLLoader loader;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        plzTextfield.textProperty().addListener(new ChangeListener<String>() { // Damit nur Zahlen als PLZ eingetragen werden können
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue,
                                String newValue) {
                if (!newValue.matches("\\d*")) {
                    plzTextfield.setText(newValue.replaceAll("[^\\d]", ""));
                }
            }
        });
        anredeChoiceBox.setItems(FXCollections.observableArrayList("Herr", "Frau"));
        anredeChoiceBox.setValue("Herr"); // Um stadtardmäßig "Herr" auszuwählen
        alert.setTitle("Hinweis");
        alert.setHeaderText("Es ist ein Fehler aufgetreten!");
    }

    @FXML
    public void showLoginScene() throws IOException {

        loader = new FXMLLoader(getClass().getResource("login_window.fxml"));
        regPane.getChildren().setAll((Parent)loader.load());
        regPane.getScene().getWindow().setHeight(137 + 40);
        regPane.getScene().getWindow().setWidth(311 + 16);
    }

    @FXML
    public void createAccount() throws IOException{

        AccountInterface neuerAccountDAO = new AccountDAO();

        if (!formCompleted()){    // Alle Felder ausgefüllt?
            alert.setContentText("Die Felder wurden nicht korrekt ausgefüllt.\nBitte achten Sie auf die Verwendung von zulässigen Zeichen.");
            alert.showAndWait(); // Fehlermeldung zeigen
            return;
        } else if (!checkBenutzername((AccountDAO) neuerAccountDAO)) {
            alert.setContentText("Dieser Benutzername ist leider schon vergeben!");
            alert.showAndWait(); // Fehlermeldung zeigen
            return;
        } else if(!checkPWs()) {
            alert.setContentText("Die beiden Passwörter stimmen nicht überein!");
            alert.showAndWait(); // Fehlermeldung zeigen
            return;
        }

        // Wenn alles in Ordnung ist:

//        Adresse neueKontaktAdresse = new Adresse(strasseTextfield.getText().trim(), hausnummerTextfield.getText().trim(), plzTextfield.getText().trim(),
//                stadtTextfield.getText().trim(), landTextfield.getText().trim() , AdressTyp.KontaktAdresse);
        Benutzer neuerBenutzer;
        if(userTextfield.getText().contains("admin")) { // Wenn der String "admin" im Usernamen enthalten ist, wird der neue User als AdminAdapter registriert. (Zum testen der Adminfunktionen)
            neuerBenutzer = new AdminAdapter(vornameTextfield.getText().trim(), nachnameTextfield.getText().trim(), firmaTextfield.getText().trim(),
                    telTextfield.getText().trim(), emailTextfield.getText().trim(), strasseTextfield.getText().trim(), hausnummerTextfield.getText().trim(), plzTextfield.getText().trim(),
                    stadtTextfield.getText().trim(), landTextfield.getText().trim());
        } else {
            neuerBenutzer = new Kaeufer(vornameTextfield.getText().trim(), nachnameTextfield.getText().trim(), firmaTextfield.getText().trim(),
                    telTextfield.getText().trim(), emailTextfield.getText().trim(), strasseTextfield.getText().trim(), hausnummerTextfield.getText().trim(), plzTextfield.getText().trim(),
                    stadtTextfield.getText().trim(), landTextfield.getText().trim());
        }
//        Bankkonto neuesBankkonto = new Bankkonto(1337.420, ibanTextfield.getText().trim(), bicTextfield.getText().trim(), banknameTextfield.getText().trim());
        // In dieser Zeile (zw. Bankkonto und AccountDTO) stand vorher das setzen der static-variable "anzahlAccounts", jetzt in Main.java
        AccountDTO neuerAccountDTO = new AccountDTO(userTextfield.getText().trim(), pw1Textfield.getText(), ibanTextfield.getText().trim(), bicTextfield.getText().trim(), banknameTextfield.getText().trim());
        neuerBenutzer.setAccountDTO(neuerAccountDTO);
        neuerAccountDTO.setBenutzer(neuerBenutzer);
//        neuesBankkonto.setAccountDTO(neuerAccountDTO);

        System.out.println(neuerBenutzer);

        try {

            neuerAccountDAO.accountErstellen(neuerAccountDTO);
//            System.out.println(neuerBenutzer);

        } catch (Exception e) {
            e.printStackTrace();
        }

        // Scene laden:
        loader = new FXMLLoader(getClass().getResource("mainPane.fxml"));
        regPane.getChildren().setAll((Parent)loader.load());
        ((MainPaneController)(loader.getController())).setActiveAccountDTO(neuerAccountDTO);
        regPane.getScene().getWindow().setHeight(390+48);
        regPane.getScene().getWindow().setWidth(600+16);


//        ((AccountDAO) neuerAccountDAO).disconnectDB();
    }

    public boolean formCompleted() { // Soll checken ob in allen Feldern was drin steht (später: mit Exception-Handling lösen)
        Set<Node> nodes = regPane.lookupAll(".text-field");
        for(Node node: nodes) {
            if(((TextField)node).getText().trim().equals("")) { // trim() entfernt führende und endende Leerzeichen
                return false;
            }
        }
        return true;
    }

    public boolean checkBenutzername(AccountDAO neuerAccountDAO) { // TODO: Soll in die Datenbank gucken und nicht ins Verzeichnis

        try {
            return neuerAccountDAO.checkNames(userTextfield.getText().trim());
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean checkPWs() {
        return pw1Textfield.getText().contentEquals(pw2Textfield.getText());
    }

}
