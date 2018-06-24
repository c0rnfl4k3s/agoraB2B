package Datenbank;

import Accountsystem.AccountDTO;
import Accountsystem.ProduktDTO;

import java.sql.SQLException;
import java.util.ArrayList;

public interface ProduktInterface {

    public void produktAnbieten(ProduktDTO neuesProdukt) throws SQLException;
    public void produktBearbeiten(ProduktDTO produkt) throws SQLException;
    public void produktEntfernen(int produktID) throws SQLException;
    public ArrayList<ProduktDTO> readProducts() throws SQLException;
}
