package Datenbank;

import java.sql.SQLException;

public interface ProduktInterface {

    public void produktAnbieten() throws SQLException;
    public void produktBearbeiten() throws SQLException;
    public void produktEntfernen() throws SQLException;
}
