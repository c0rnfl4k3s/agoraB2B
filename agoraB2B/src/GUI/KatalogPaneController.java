package GUI;

import Accountsystem.AccountDTO;
import Accountsystem.AdminAdapter;
import Accountsystem.Verkaeufer;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.layout.Pane;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class KatalogPaneController implements Initializable {

    @FXML
    private Button software, elektronik, buero, rhb;

    private AccountDTO activeAccountDTO;
    private MainPaneController motherPaneController;
    private FXMLLoader loader;

    @FXML
    private Pane katalogContent;


    public KatalogPaneController(AccountDTO activeAccountDTO) { // Um die Variable setzen zuu können, bevor die initialize-Methode aufgerufen wird (Der Controller ist in der FXML nicht definiert, sondern manuell!)
        setActiveAccountDTO(activeAccountDTO);
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        if(activeAccountDTO.getBenutzer() instanceof AdminAdapter) {
            adminsichtAnzeigen();
        } else if (activeAccountDTO.getBenutzer() instanceof Verkaeufer) {
            verkaeufersichtAnzeigen();
        } else {
            kaeufersichtAnzeigen();
        }
        // TODO: (evtl. nicht an dieser stelle): Buttons der Tabelle hinzufügen für Adminsicht: https://stackoverflow.com/questions/29489366/how-to-add-button-in-javafx-table-view
    }

    public void adminsichtAnzeigen() {
        Parent content = null;
        try {
            loader = new FXMLLoader(getClass().getResource("katalogContentAdminsicht.fxml"));
            content = loader.load();
            ((KatalogContentAdminsichtController)loader.getController()).setActiveAccountDTO(activeAccountDTO); // AccountDTO-Objekt an Controller übergeben (geht vlt auch einfacher, von innerhalb des Controllers getten?)
            ((KatalogContentAdminsichtController)loader.getController()).setMotherPaneController(this);
            katalogContent.getChildren().setAll(content);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void verkaeufersichtAnzeigen() {
        Parent content = null;
        try {
            loader = new FXMLLoader(getClass().getResource("katalogContentVerkaeufersicht.fxml"));
            content = loader.load();
            ((KatalogContentVerkaeufersichtController)loader.getController()).setActiveAccountDTO(activeAccountDTO); // AccountDTO-Objekt an Controller übergeben (geht vlt auch einfacher, von innerhalb des Controllers getten?)
            ((KatalogContentVerkaeufersichtController)loader.getController()).setMotherPaneController(this);
            katalogContent.getChildren().setAll(content);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void kaeufersichtAnzeigen() {
        Parent content = null;
        try {
            loader = new FXMLLoader(getClass().getResource("katalogContentKaeufersicht.fxml"));
            content = loader.load();
            ((KatalogContentKaeufersichtController)loader.getController()).setActiveAccountDTO(activeAccountDTO); // AccountDTO-Objekt an Controller übergeben (geht vlt auch einfacher, von innerhalb des Controllers getten?)
            ((KatalogContentKaeufersichtController)loader.getController()).setMotherPaneController(this);
            katalogContent.getChildren().setAll(content);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void setActiveAccountDTO(AccountDTO activeAccountDTO) {
        this.activeAccountDTO = activeAccountDTO;
    }

    public void setMotherPaneController(MainPaneController mainPaneController) {
        this.motherPaneController = mainPaneController;
    }
}
