package Datenbank;

import org.junit.Test;

import java.sql.SQLException;

import static junit.framework.Assert.*;

public class JUnitDBTest {

    @Test
    public void readAccounts() { // Verbindet zur DB und prüft ob Accounts in der DB vorhanden sind
        AccountInterface acc = new AccountDAO();
        try {
            assertFalse(acc.readAccounts().isEmpty());
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void readProducts() { // Verbindet zur DB und prüft ob Produkte vorhanden sind

        ProduktInterface p = new ProduktDAO();
        try {
            assertFalse(p.readProducts().isEmpty());
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}
