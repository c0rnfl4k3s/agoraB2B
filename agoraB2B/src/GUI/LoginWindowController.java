package GUI;

import Accountsystem.AccountDTO;
import Datenbank.AccountDAO;
import Infrastruktur.PropertiesKlasse;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.URL;
import java.util.Locale;
import java.util.ResourceBundle;

public class LoginWindowController implements Initializable {

    private PropertiesKlasse p = new PropertiesKlasse();
    private String selectedCountry = p.getProp().getProperty("country","DE");
    private String selectedLanguage = p.getProp().getProperty("lang","de");
    private Locale selectedLocale = new Locale(selectedLanguage, selectedCountry);
    private ResourceBundle mybundle = ResourceBundle.getBundle("StringBundle", selectedLocale);

    @FXML
    AnchorPane loginPane;
    @FXML
    PasswordField pwTextfield;
    @FXML
    TextField userTextfield;
    @FXML
    ComboBox sprachauswahl;
    @FXML
    Label userLabel, pwLabel;
    @FXML
    Button newAccButton, loginButton;

    FXMLLoader loader; // Um auf Controller der eingelesenen Panes zugreifen zu können

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        sprachauswahl.setItems(FXCollections.observableArrayList("Deutsch", "English"));
        sprachauswahl.setValue("Deutsch");
        sprachauswahl.valueProperty().addListener(new ChangeListener<String>() {
            @Override public void changed(ObservableValue ov, String t, String t1) { // t = old value, t1 = new value
                spracheAendern(t1);
            }
        });


    }

    public void spracheAendern(String sprache) {

        p.getProp().setProperty("lang", "" + (sprache.equals("Deutsch") ? "de" : "eng"));
        mybundle = ResourceBundle.getBundle("StringBundle", new Locale("" + (sprache.equals("Deutsch") ? "de" : "eng"), "" + (sprache.equals("Deutsch") ? "DE" : "US")));

        userLabel.setText(mybundle.getString("Benutzername"));
        pwLabel.setText(mybundle.getString("Passwort"));
        newAccButton.setText(mybundle.getString("neuenAccountErstellen"));
        loginButton.setText(mybundle.getString("Einloggen"));

        Infrastruktur.LoggerKlasse.getInstance().getLog().fine("Sprache wurde auf '" + sprache + "' geändert.");
    }

    @FXML
    public void showNewAccountScene() throws IOException {
        loader = new FXMLLoader(getClass().getResource("register_window.fxml"));
        loginPane.getChildren().setAll((Parent)loader.load());
        ((RegisterWindowController)loader.getController()).setResourceBundle(mybundle);
        loginPane.getScene().getWindow().setHeight(626+40);
        loginPane.getScene().getWindow().setWidth(648+16);
    }

    @FXML
    public void login() throws IOException{
        AccountDTO activeAccountDTO = null;
        // Login Daten checken und AccountDTO-Objekt auslesen:
        try {
            AccountDAO activeAccountDAO = new AccountDAO();
            activeAccountDTO = activeAccountDAO.accountAbrufen(userTextfield.getText());
            if(!activeAccountDTO.getPasswort().equals(pwTextfield.getText())) {
                throw new IllegalArgumentException();
            }

        } catch (Exception e) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setHeaderText(mybundle.getString("UngültigeUserdaten"));
//            alert.setHeight(10);
            alert.showAndWait();
            Infrastruktur.LoggerKlasse.getInstance().getLog().fine("Ungültiger Login.");
            return;
        }


        // Seite laden:
        loader = new FXMLLoader();

        loader.setLocation(getClass().getResource("mainPane.fxml"));
        Parent mainPane = loader.load(); // Immer erst load aufrufen, bevor man auf den Controller zugreift !!
        ((MainPaneController)loader.getController()).setActiveAccountDTO(activeAccountDTO); // AccountDTO-Objekt an Controller übergeben (geht vlt auch einfacher, von innerhalb des Controllers getten?)
        ((MainPaneController)loader.getController()).setResourceBundle(mybundle);
        ((MainPaneController)loader.getController()).produktKatalogAnzeigen();
        loginPane.getScene().getWindow().setHeight(390+48);
        loginPane.getScene().getWindow().setWidth(600+16);
        loginPane.getChildren().setAll(mainPane);

        Infrastruktur.LoggerKlasse.getInstance().getLog().fine("Benutzer '" + activeAccountDTO.getName() + "' eingeloggt.");
    }
}
