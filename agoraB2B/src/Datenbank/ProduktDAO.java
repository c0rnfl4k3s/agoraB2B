package Datenbank;

import java.sql.Connection;
import java.sql.SQLException;

public class ProduktDAO implements ProduktInterface {

    Connection conn;

    public ProduktDAO() {
        ConnectionManager meineSitzung = new ConnectionManager();
        conn = meineSitzung.connectDB("db4free.net", "swttest", "swttest", "swtadmin");

        if(conn!=null)///Verbindungsaufbau erfolgreich
        {
            System.out.println("Verbindung hergestellt");
        }
        else
        {
            System.out.println("keine Verbindung zur Datenbank");
        }
    }

    @Override
    public void produktAnbieten() throws SQLException {

    }

    @Override
    public void produktBearbeiten() throws SQLException {

    }

    @Override
    public void produktEntfernen() throws SQLException {

    }
}
