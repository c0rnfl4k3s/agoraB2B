package GUI;

import Accountsystem.*;
import Infrastruktur.PropertiesKlasse;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.net.URL;
import java.util.Locale;
import java.util.ResourceBundle;

public class MainPaneController implements Initializable {

    private PropertiesKlasse p = new PropertiesKlasse();
    private String selectedCountry = p.getProp().getProperty("country","DE");
    private String selectedLanguage = p.getProp().getProperty("lang","de");
    private Locale selectedLoacale = new Locale(selectedLanguage, selectedCountry);
    private ResourceBundle mybundle = ResourceBundle.getBundle("StringBundle", selectedLoacale);

    @FXML
    BorderPane rootPane;
    @FXML
    Pane contentPane;

    FXMLLoader loader; // Um auf die Controller-Objekte der eingelesenen Panes zugreifen zu können
    private AccountDTO activeAccountDTO;
    @FXML
    private Button einkaufswagenButton, produktKatalogButton, postfachButton;


    @Override
    public void initialize(URL location, ResourceBundle resources) {
//        AdminAdapter testAdmin = new AdminAdapter("Test", "AccountDTO", "Test", "0123456789", "testemail@test.de" ,
//                new Adresse("Teststreet", "123", "45279", "Essen", "DE", AdressTyp.KontaktAdresse));
//        activeAccountDTO = new AccountDTO();
        try {
            produktKatalogAnzeigen();
//            accountAnzeigen();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Ruft den Produktkatalog auf
     * @throws IOException
     */
    @FXML
    public void produktKatalogAnzeigen() throws IOException {
//        Parent content = FXMLLoader.load(getClass().getResource("katalogPane.fxml"));
        loader = new FXMLLoader(getClass().getResource("katalogPane.fxml"));
//        loader.setController(new KatalogPaneController(/*activeAccountDTO*/)); // Muss manuell gesetzt werden, um activeAccountDTO zu übergeben. Controller ist in der FXML Datei bewusst nicht gesetzt.
//        loader.setLocation(getClass().getResource("katalogPane.fxml"));
        Parent content = loader.load();
//        ((KatalogPaneController)loader.getController()).setActiveAccountDTO(activeAccountDTO); // AccountDTO-Objekt an Controller übergeben (geht vlt auch einfacher, von innerhalb des Controllers getten?)
        ((KatalogPaneController)loader.getController()).setMotherPaneController(this);
        ((KatalogPaneController)loader.getController()).setResourceBundle(mybundle);
        contentPane.getChildren().setAll(content);
        Infrastruktur.LoggerKlasse.getInstance().getLog().fine("Produktkatalog aufgerufen.");
    }

    /**
     * Ruft das Postfach auf.
     * @throws IOException
     */
    @FXML
    public void postfachAnzeigen() throws IOException {
//        Parent content = FXMLLoader.load(getClass().getResource("postfachPane.fxml"));
        loader = new FXMLLoader(getClass().getResource("postfachPane.fxml"));
        Parent content = loader.load();
        ((PostfachPaneController)loader.getController()).setResourceBundle(mybundle);
        contentPane.getChildren().setAll(content);
        Infrastruktur.LoggerKlasse.getInstance().getLog().fine("Postfach aufgerufen.");
    }
    
    /**
     * Ruft die Account-Seite auf.
     * @throws IOException
     */
    @FXML
    public void accountAnzeigen() throws IOException, IllegalAccessException, NoSuchMethodException, InvocationTargetException {

//        Parent content = FXMLLoader.load(getClass().getResource("accountPane.fxml"));
        loader = new FXMLLoader(getClass().getResource("accountPane.fxml"));
        Parent content = loader.load(); // Immer erst load aufrufen, bevor man auf den Controller zugreift !!
//        System.out.println(loader.getController().toString());
//        System.out.println(loader.toString());
        ((AccountPaneController)loader.getController()).setActiveAccountDTO(activeAccountDTO); // AccountDTO-Objekt an Controller übergeben (geht vlt auch einfacher, von innerhalb des Controllers getten?)
        ((AccountPaneController)loader.getController()).setMotherPaneController(this);
        ((AccountPaneController)loader.getController()).setResourceBundle(mybundle);
        contentPane.getChildren().setAll(content);
        Infrastruktur.LoggerKlasse.getInstance().getLog().fine("Accountpage aufgerufen");
    }
    
    /**
     * Ruft das Einkaufswagen-Fenster auf.
     * @throws IOException
     */
    @FXML
    public void einkaufswagenAnzeigen() throws IOException {
//        Parent content = FXMLLoader.load(getClass().getResource("einkaufswagenPane.fxml"));
        loader = new FXMLLoader(getClass().getResource("einkaufswagenPane.fxml"));

            Parent content = loader.load();
            contentPane.getChildren().setAll(content);

        EinkaufswagenPaneController ekwController = loader.getController(); // ekwController wird zugreifbar
        ekwController.setActiveAccountDTO(activeAccountDTO); // ekwController kriegt Zugriff auf den Einkaufswagen im Accountsystem
        ekwController.setResourceBundle(mybundle);
        Infrastruktur.LoggerKlasse.getInstance().getLog().fine("Einkaufswagen aufgerufen.");
    }

    /**
     * Der User wird aus seiner Session ausgeloggt.
     * @throws IOException
     */
    @FXML
    public void logout() throws IOException {

//        Parent root = FXMLLoader.load(getClass().getResource("login_window.fxml"));
        loader = new FXMLLoader(getClass().getResource("login_window.fxml"));
        Parent root = loader.load();
        rootPane.getChildren().setAll(root);
        rootPane.getScene().getWindow().setHeight(137 + 40);
        rootPane.getScene().getWindow().setWidth(311 + 16);
        Infrastruktur.LoggerKlasse.getInstance().getLog().fine("Benutzer '" + activeAccountDTO.getName() + "' ausgeloggt.");
        activeAccountDTO = null;
    }

    public AccountDTO getActiveAccountDTO() {
        return activeAccountDTO;
    }

    public void setActiveAccountDTO(AccountDTO activeAccountDTO) {
        this.activeAccountDTO = activeAccountDTO;
    }

    public void setResourceBundle(ResourceBundle mybundle) {
        this.mybundle = mybundle;
        einkaufswagenButton.setText(mybundle.getString("Einkaufswagen"));
        postfachButton.setText(mybundle.getString("Postfach"));
        produktKatalogButton.setText(mybundle.getString("Produktkatalog"));
    }

}
