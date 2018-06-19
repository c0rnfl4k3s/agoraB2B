package GUI;

import Accountsystem.AccountDTO;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableView;
import javafx.scene.layout.Pane;

import java.net.URL;
import java.util.ResourceBundle;

public class EinkaufswagenPaneController implements Initializable {

//    private Einkaufswagen ekw; // Wird von der MainPane deklariert
    private AccountDTO activeAccountDTO;
    @FXML
    private Button bestellungAufgebenButton;
    @FXML
    private TableView table;

    @Override
    public void initialize(URL location, ResourceBundle resources) { // TODO Buggy
        bestellungAufgebenButton.setDisable(/*activeAccountDTO.getEkw().isEmpty()*/true); // erstmal nur zu testzwecken
        Pane header = (Pane) table.lookup("TableHeaderRow");
//        header.setMaxHeight(0);
//        header.setMinHeight(0);
//        header.setPrefHeight(0);
//        header.setVisible(false);
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
