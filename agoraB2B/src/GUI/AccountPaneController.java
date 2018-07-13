package GUI;

import Accountsystem.AccountDTO;
import Datenbank.AccountDAO;
import Infrastruktur.PropertiesKlasse;
import javafx.beans.property.ObjectProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.URL;
import java.util.Locale;
import java.util.Optional;
import java.util.ResourceBundle;

public class AccountPaneController implements Initializable {

    private PropertiesKlasse p = new PropertiesKlasse();
    private String selectedCountry = p.getProp().getProperty("country","DE");
    private String selectedLanguage = p.getProp().getProperty("lang","de");
    private Locale selectedLoacale = new Locale(selectedLanguage, selectedCountry);
    private ResourceBundle mybundle = ResourceBundle.getBundle("StringBundle", selectedLoacale);

    @FXML
    AnchorPane accountPane;

    @FXML
    public TextField vornameTextfield, userTextfield, emailTextfield, telTextfield, nachnameTextfield, firmaTextfield, ibanTextfield,
            bicTextfield, banknameTextfield, strasseTextfield, hausnummerTextfield, stadtTextfield, plzTextfield, landTextfield;
    @FXML
    ComboBox adressTypComboBox;
    @FXML
    Accordion accordion;
    @FXML
    Button bearbeitenButton, speichernButton, abbrechenButton, accountLoeschenButton;
    @FXML
    TitledPane allgemeinPane, bankverbindungPane, adressdetailsPane;
    @FXML
    Label vornameLabel, nachnameLabel, userLabel, firmaLabel, strasseLabel, hausnummerLabel, plzLabel, stadtLabel, landLabel;

    MainPaneController motherPaneController;

    private AccountDTO activeAccountDTO;

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
        adressTypComboBox.setItems(FXCollections.observableArrayList("Kontaktadresse", "Lieferadresse", "Rechnungsadresse", "Absendeadresse"));
        adressTypComboBox.setValue("Kontaktadresse");
        accordion.setExpandedPane(accordion.getPanes().get(0));

    }

    public void setResourceBundle(ResourceBundle mybundle) {

        this.mybundle = mybundle;
        vornameLabel.setText(mybundle.getString("Vorname") + ":");
        nachnameLabel.setText(mybundle.getString("Nachname") + ":");
        userLabel.setText(mybundle.getString("Benutzername") + ":");
        firmaLabel.setText(mybundle.getString("Firma") + ":");
        strasseLabel.setText(mybundle.getString("Strasse") + ":");
        hausnummerLabel.setText(mybundle.getString("Hausnummer") + ":");
        plzLabel.setText(mybundle.getString("PLZ") + ":");
        stadtLabel.setText(mybundle.getString("Stadt") + ":");
        landLabel.setText(mybundle.getString("Land") + ":");

        allgemeinPane.setText(mybundle.getString("Allgemein"));
        bankverbindungPane.setText(mybundle.getString("Bankverbindung"));
        adressdetailsPane.setText(mybundle.getString("Adressdetails"));

        bearbeitenButton.setText(mybundle.getString("Bearbeiten"));
        speichernButton.setText(mybundle.getString("Speichern"));
        abbrechenButton.setText(mybundle.getString("Abbrechen"));
        accountLoeschenButton.setText(mybundle.getString("AccountLöschen"));

        if(mybundle.getString("Vorname").equals("First name")) { // Zu umständlich über Properties
            adressTypComboBox.setItems(FXCollections.observableArrayList("Contact Address", "Delivery Address", "Billing Address", "Return Address"));
            adressTypComboBox.setValue("Contact Address");
        }
    }
    
    /**
     * 
     * @return Es wird der aktive Account zurückgegeben
     */
    public AccountDTO getActiveAccountDTO() {
        return activeAccountDTO;
    }

    /**
     * Die Userdaten des aktiven Accounts werden in den Textfeldern angezeigt
     * @param activeAccountDTO
     */
    public void setActiveAccountDTO(AccountDTO activeAccountDTO) { // setzt auch alle Textfelder

//        if(this.activeAccountDTO == null || !activeAccountDTO.equals(this.activeAccountDTO)) {
            this.activeAccountDTO = activeAccountDTO;
//        }
//        Set<Node> nodes = accountPane.lookupAll(".titled-pane"); // Sammlung aller TextFields in der AccountPane
        for(Node node: accordion.getPanes()) { // schrittweise alle 3 Panes durchlaufen
//            Set<Node> nodes1 = node.lookupAll(".text-field");
            ObjectProperty<Node> contentNode = ((TitledPane)node).contentProperty(); // Inhalte der Titled Pane getten
            for(Node node1: ((AnchorPane)contentNode.getValue()).getChildren()) { // Alle Inhalte der Titled Pane nacheinander abrufen
                if(node1 instanceof TextField) {
                    ((TextField)node1).setText(activeAccountDTO.getAttribute(node1.getId()));
                }
            }
        }
    }

    
    /**
     * Ein Benutzer kann einen ihm zugeordneten aktivenAccount löschen.
     */
    public void accountLoeschen() { // Die vorgebauten Eigenschaften der Alert Dialogs, zB Abbrechen-Button, lassen sich nicht so einfach Bearbeiten! Daher bleiben zwei Wörter der Einfachkeit halber auf deutsch.
        Alert deleteAccAlert = new Alert(Alert.AlertType.CONFIRMATION, "" + (!mybundle.getString("Vorname").equals("First name") ? "Sind Sie sicher, dass Sie Ihren Account löschen möchten?"
            : "Do you want to delete your Account?"));
        Optional<ButtonType> result = deleteAccAlert.showAndWait();
        if (result.get() == ButtonType.OK){
            // Benutzer hat OK geklickt
            AccountDAO accountDAO = new AccountDAO();
            accountDAO.accountLoeschen(activeAccountDTO.getAccountID());
            try {
                motherPaneController.logout();
            } catch (IOException e) {
                e.printStackTrace();
            }
            Infrastruktur.LoggerKlasse.getInstance().getLog().fine("Benutzer '" + activeAccountDTO.getName() + "' wurde gelöscht.");
        }
    }

    
    /**
     * 
     * Ermöglicht die Bearbeitung der User-Daten
     */
    public void bearbeitungAktivieren(ActionEvent actionEvent) {

        switchButtons();
        switchEditable();
    }

    /**
     * Speichert die neuen Eingaben nach der Bearbeitung der User-Daten
     */
    public void bearbeitungSpeichern() {

        File checkFile = new File(userTextfield.getText() + ".ser");
        if(!activeAccountDTO.getName().equals(userTextfield.getText()) && checkFile.exists()) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setHeaderText("Es ist ein Fehler aufgetreten!");
            alert.setContentText("Es gibt bereits einen anderen Benutzer mit diesem Benutzernamen.");
            alert.showAndWait();
            return;
        }

        switchButtons();
        switchEditable();

        File updatedFile = new File(activeAccountDTO.getName() + ".ser"); // Solange der Benutzername noch nicht aktualisiert wurde

//        ArrayList<TextField> updatedAttributes = new ArrayList<>();
        for(Node node: accordion.getPanes()) { // schrittweise alle 3 Panes durchlaufen
            ObjectProperty<Node> contentNode = ((TitledPane)node).contentProperty(); // Inhalte der TitledPane im Accordion getten
            for(Node node1: ((AnchorPane)contentNode.getValue()).getChildren()) { // Alle Inhalte der TitledPane nacheinander abrufen
                if(node1 instanceof TextField) {
//                   updatedAttributes.add(((TextField) node1));
                    activeAccountDTO.setAttribute(((TextField) node1).getText(), ((TextField) node1).getId()); //TODO
                }
            }
        }
        AccountDAO accountDAO = new AccountDAO();
        accountDAO.accountUpdaten(activeAccountDTO);
        setActiveAccountDTO(activeAccountDTO);

        Infrastruktur.LoggerKlasse.getInstance().getLog().fine("Benutzer '" + activeAccountDTO.getName() + "' wurde bearbeitet.");
    }

    
    /**
     * Beendet den Bearbeitungsmodus ohne Speicherung
     */
    public void bearbeitungAbbrechen() {

        switchButtons();
        switchEditable();
        setActiveAccountDTO(activeAccountDTO); // liest wieder die ursprünglichen TextField-Inhalte ein
    }

    /**
     * Aktiviert bzw. deaktiviert die Bearbeitung der Formularfelder
     */    
    public void switchEditable() {
        for(Node node: accordion.getPanes()) { // schrittweise alle 3 Panes durchlaufen
            ObjectProperty<Node> contentNode = ((TitledPane)node).contentProperty(); // Inhalte der TitledPane im Accordion getten
            for(Node node1: ((AnchorPane)contentNode.getValue()).getChildren()) { // Alle Inhalte der TitledPane nacheinander abrufen
                if(node1 instanceof TextField) {
                    ((TextField) node1).setEditable(!((TextField) node1).isEditable());
                }
            }
        }
    }

    /**
     * Aktiviert bzw. deaktiviert den Speicher-, Bearbeitungs- und Abbrechen-Button
     */
    public void switchButtons() {
        bearbeitenButton.setDisable(!bearbeitenButton.isDisabled()); // später in Methode "switchButtons()" oder so auslagern
        abbrechenButton.setDisable(!abbrechenButton.isDisabled());
        speichernButton.setDisable(!speichernButton.isDisabled());
    }

    public void setMotherPaneController(MainPaneController mainPaneController) {
        this.motherPaneController = mainPaneController;
    }
}
