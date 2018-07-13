package GUI;

import Accountsystem.AccountDTO;
import Infrastruktur.PropertiesKlasse;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableView;
import javafx.scene.layout.Pane;

import java.net.URL;
import java.util.Locale;
import java.util.ResourceBundle;

public class EinkaufswagenPaneController implements Initializable {

    private PropertiesKlasse p = new PropertiesKlasse();
    private String selectedCountry = p.getProp().getProperty("country","DE");
    private String selectedLanguage = p.getProp().getProperty("lang","de");
    private Locale selectedLoacale = new Locale(selectedLanguage, selectedCountry);
    private ResourceBundle mybundle = ResourceBundle.getBundle("StringBundle", selectedLoacale);

//    private Einkaufswagen ekw; // Wird von der MainPane deklariert
    private AccountDTO activeAccountDTO;
    @FXML
    private Button bestellungAufgebenButton;
    @FXML
    private TableView table;
    @FXML
    Label headerLabel, sumLabel;

    @Override
    public void initialize(URL location, ResourceBundle resources) { // TODO Buggy
        bestellungAufgebenButton.setDisable(/*activeAccountDTO.getEkw().isEmpty()*/true); // erstmal nur zu testzwecken
        Pane header = (Pane) table.lookup("TableHeaderRow");
//        header.setMaxHeight(0);
//        header.setMinHeight(0);
//        header.setPrefHeight(0);
//        header.setVisible(false);
    }

    public void setResourceBundle(ResourceBundle mybundle) {
        this.mybundle = mybundle;
        bestellungAufgebenButton.setText(mybundle.getString("BestellungAufgeben"));
        headerLabel.setText(mybundle.getString("Einkaufswagen"));
        sumLabel.setText(mybundle.getString("Summe") + ": 0,00");
    }

    @FXML
    public void bestellungAufgeben() {
        System.out.println("test"); // zu testzwecken
    }

//    public Einkaufswagen getEkw() {
//        return ekw;
//    }
//
//    public void setEkw(Einkaufswagen ekw) {
//        this.ekw = ekw;
//    }


    public AccountDTO getActiveAccountDTO() {
        return activeAccountDTO;
    }

    public void setActiveAccountDTO(AccountDTO activeAccountDTO) {
        this.activeAccountDTO = activeAccountDTO;
    }
}
