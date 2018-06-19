package GUI;

import Accountsystem.*;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ListView;
import javafx.scene.control.ScrollPane;

import java.net.URL;
import java.util.ResourceBundle;

public class PostfachPaneController implements Initializable {

    @FXML
    ListView<Chat> chatListView; // wird noch hinzugefügt
    @FXML
    ScrollPane chatField2;
    @FXML
    ScrollPane chatField1;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        // Chatverlauf getten und erstmal alles der Liste hinzufügen mit:
        // chatListView.getItems().add(Chat einChat);    (oder so)
        chatField2.setDisable(true);
        chatField1.setDisable(true); // damit diese 3 Container nicht anklickbar sind
        chatListView.setDisable(true);
    }
}
