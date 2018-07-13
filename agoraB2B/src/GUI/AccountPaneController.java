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
    private ResourceBundle mybundle = ResourceBundle.getBundle("messageBundle", selectedLoacale);

    @FXML
    AnchorPane accountPane;

    @FXML
    public TextField vornameTextfield, userTextfield, emailTextfield, telTextfield, nachnameTextfield, firmaTextfield, ibanTextfield,
            bicTextfield, banknameTextfield, strasseTextfield, hausnummerTextfield, stadtTextfield, plzTextfield, landTextfield;
    @FXML
    ComboBox adressTypComboBox; // Funktioniert noch nicht!
    @FXML
    Accordion accordion;
    @FXML
    Button bearbeitenButton, speichernButton, abbrechenButton, accountLoeschenButton;

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
        adressTypComboBox.setItems(FXCollections.observableArrayList("Kontaktadresse", "Lieferadresse", "Rechnungsadresse", "Absendeadresse", "Rücksendeadresse"));
        adressTypComboBox.setValue("Kontaktadresse");
        accordion.setExpandedPane(accordion.getPanes().get(0));

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
    public void accountLoeschen() {
        Alert deleteAccAlert = new Alert(Alert.AlertType.CONFIRMATION, "Sind Sie sicher, dass Sie Ihren Account löschen möchten?");
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
