package GUI;

import Accountsystem.*;
import Infrastruktur.PropertiesKlasse;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;

import java.net.URL;
import java.util.Locale;
import java.util.ResourceBundle;

public class PostfachPaneController implements Initializable {

    private PropertiesKlasse p = new PropertiesKlasse();
    private String selectedCountry = p.getProp().getProperty("country","DE");
    private String selectedLanguage = p.getProp().getProperty("lang","de");
    private Locale selectedLoacale = new Locale(selectedLanguage, selectedCountry);
    private ResourceBundle mybundle = ResourceBundle.getBundle("StringBundle", selectedLoacale);

    @FXML
    ListView<Chat> chatListView; // wird noch hinzugefügt
    @FXML
    ScrollPane chatField2;
    @FXML
    ScrollPane chatField1;
    @FXML
    Button sendenButton, neuerChatButton;
    @FXML
    Label chatverlaufLabel;
    @FXML
    TextField messageTextfield;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        // Chatverlauf getten und erstmal alles der Liste hinzufügen mit:
        // chatListView.getItems().add(Chat einChat);    (oder so)
        chatField2.setDisable(true);
        chatField1.setDisable(true); // damit diese 3 Container nicht anklickbar sind
        chatListView.setDisable(true);
    }

    public void setResourceBundle(ResourceBundle mybundle) {
        this.mybundle = mybundle;
        sendenButton.setText(mybundle.getString("Senden"));
        neuerChatButton.setText(mybundle.getString("neuenChatErstellen"));
        chatverlaufLabel.setText(mybundle.getString("Chatverlauf"));
        messageTextfield.setPromptText(mybundle.getString("NachrichtSchreiben"));
    }
}
