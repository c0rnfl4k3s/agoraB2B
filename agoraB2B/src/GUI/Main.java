package GUI;
import java.sql.*;
import Accountsystem.AccountDTO;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;


import java.io.File;
import java.io.FilenameFilter;
import java.io.IOException;
public class Main extends Application {

    public static void main(String[] args) throws SQLException { launch(args);}

    @Override
    public void start(Stage primaryStage) throws IOException {

        AccountDTO.setAnzahlAccounts(new File(System.getProperty("user.dir")).listFiles(new FilenameFilter() {
            @Override
            public boolean accept(File dir, String name) { // alle Dateien im Verzeichnis die mit ".ser" enden werden mitgezählt
                return name.endsWith(".ser");
            }
        }).length); // userID des neuen accounts hängt ab von der anzahl der bereits existierenden accounts.

        Scene loginScene = new Scene(FXMLLoader.load(getClass().getResource("login_window.fxml"))); // bekommt die FXML-Datei als Rootnode übergeben
        primaryStage.setTitle("agoraB2B");
        primaryStage.setScene(loginScene);
        primaryStage.setResizable(false);
        primaryStage.show();

    }
}
