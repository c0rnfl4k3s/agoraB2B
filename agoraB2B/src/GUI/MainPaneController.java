package GUI;

import Accountsystem.*;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.net.URL;
import java.util.ResourceBundle;

public class MainPaneController implements Initializable {

    @FXML
    BorderPane rootPane;
    @FXML
    Pane contentPane;

    FXMLLoader loader; // Um auf die Controller-Objekte der eingelesenen Panes zugreifen zu können
    private AccountDTO activeAccountDTO;


    @Override
    public void initialize(URL location, ResourceBundle resources) {
//        AdminAdapter testAdmin = new AdminAdapter("Test", "AccountDTO", "Test", "0123456789", "testemail@test.de" ,
//                new Adresse("Teststreet", "123", "45279", "Essen", "DE", AdressTyp.KontaktAdresse));
//        activeAccountDTO = new AccountDTO();
        try {
            produktKatalogAnzeigen();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    public void produktKatalogAnzeigen() throws IOException {
//        Parent content = FXMLLoader.load(getClass().getResource("katalogPane.fxml"));
        loader = new FXMLLoader();
        loader.setController(new KatalogPaneController(activeAccountDTO)); // Muss manuell gesetzt werden, um activeAccountDTO zu übergeben. Controller ist in der FXML Datei bewusst nicht gesetzt.
        loader.setLocation(getClass().getResource("katalogPane.fxml"));
        Parent content = loader.load();
//        ((KatalogPaneController)loader.getController()).setActiveAccountDTO(activeAccountDTO); // AccountDTO-Objekt an Controller übergeben (geht vlt auch einfacher, von innerhalb des Controllers getten?)
        ((KatalogPaneController)loader.getController()).setMotherPaneController(this);
//        Parent content = loader.load();
        contentPane.getChildren().setAll(content);
//        System.out.println(contentPane);
    }

    @FXML
    public void postfachAnzeigen() throws IOException {
//        Parent content = FXMLLoader.load(getClass().getResource("postfachPane.fxml"));
        loader = new FXMLLoader(getClass().getResource("postfachPane.fxml"));
        Parent content = loader.load();
        contentPane.getChildren().setAll(content);
    }

    @FXML
    public void accountAnzeigen() throws IOException, IllegalAccessException, NoSuchMethodException, InvocationTargetException {

//        Parent content = FXMLLoader.load(getClass().getResource("accountPane.fxml"));
        loader = new FXMLLoader(getClass().getResource("accountPane.fxml"));
        Parent content = loader.load(); // Immer erst load aufrufen, bevor man auf den Controller zugreift !!
//        System.out.println(loader.getController().toString());
//        System.out.println(loader.toString());
        ((AccountPaneController)loader.getController()).setActiveAccountDTO(activeAccountDTO); // AccountDTO-Objekt an Controller übergeben (geht vlt auch einfacher, von innerhalb des Controllers getten?)
        ((AccountPaneController)loader.getController()).setMotherPaneController(this);
        contentPane.getChildren().setAll(content);
    }

    @FXML
    public void einkaufswagenAnzeigen() throws IOException { // TODO BUGGY
//        Parent content = FXMLLoader.load(getClass().getResource("einkaufswagenPane.fxml"));
        loader = new FXMLLoader(getClass().getResource("einkaufswagenPane.fxml"));

            Parent content = loader.load();
            contentPane.getChildren().setAll(content);

        EinkaufswagenPaneController ekwController = loader.getController(); // ekwController wird zugreifbar
        ekwController.setActiveAccountDTO(activeAccountDTO); // ekwController kriegt Zugriff auf den Einkaufswagen im Accountsystem
    }

    @FXML
    public void logout() throws IOException {

        activeAccountDTO = null; // vorher alles speichern! Und auch bei window close!!

//        Parent root = FXMLLoader.load(getClass().getResource("login_window.fxml"));
        loader = new FXMLLoader(getClass().getResource("login_window.fxml"));
        Parent root = loader.load();
        rootPane.getChildren().setAll(root);
        rootPane.getScene().getWindow().setHeight(137 + 40);
        rootPane.getScene().getWindow().setWidth(311 + 16);
    }

    public AccountDTO getActiveAccountDTO() {
        return activeAccountDTO;
    }

    public void setActiveAccountDTO(AccountDTO activeAccountDTO) {
        this.activeAccountDTO = activeAccountDTO;
    }
}
