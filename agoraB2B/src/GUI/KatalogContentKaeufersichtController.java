package GUI;

import Accountsystem.AccountDTO;
import Infrastruktur.PropertiesKlasse;
import javafx.fxml.Initializable;

import java.net.URL;
import java.util.Locale;
import java.util.ResourceBundle;

public class KatalogContentKaeufersichtController implements Initializable {

    private PropertiesKlasse p = new PropertiesKlasse();
    private String selectedCountry = p.getProp().getProperty("country","DE");
    private String selectedLanguage = p.getProp().getProperty("lang","de");
    private Locale selectedLoacale = new Locale(selectedLanguage, selectedCountry);
    private ResourceBundle mybundle = ResourceBundle.getBundle("messageBundle", selectedLoacale);

    private KatalogPaneController motherPaneController;
    private AccountDTO activeAccountDTO;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }

    public void setActiveAccountDTO(AccountDTO activeAccountDTO) {
        this.activeAccountDTO = activeAccountDTO;
    }

    public void setMotherPaneController(KatalogPaneController katalogPaneController) {
        this.motherPaneController = katalogPaneController;
    }

    public void setResourceBundle(ResourceBundle mybundle) {
        this.mybundle = mybundle;
    }
}
