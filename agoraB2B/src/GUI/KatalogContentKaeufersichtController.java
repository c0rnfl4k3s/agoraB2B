package GUI;

import Accountsystem.AccountDTO;
import javafx.fxml.Initializable;

import java.net.URL;
import java.util.ResourceBundle;

public class KatalogContentKaeufersichtController implements Initializable {

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
}
