package Datenbank;

import org.junit.Test;

import java.sql.SQLException;

import static junit.framework.Assert.*;

public class JUnitDBTest {

	/**
    * Verbindet zur DB und prüft ob Accounts in der DB vorhanden sind.
    */
    @Test
    public void readAccounts() { 
        AccountInterface acc = new AccountDAO();
        try {
            assertFalse(acc.readAccounts().isEmpty());
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * Verbindet zur DB und prüft ob Produkte in der DB vorhanden sind.
     */
    @Test
    public void readProducts() { 

        ProduktInterface p = new ProduktDAO();
        try {
            assertFalse(p.readProducts().isEmpty());
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}
