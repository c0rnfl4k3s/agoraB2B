package Datenbank;


import Accountsystem.AccountDTO;

import java.sql.SQLException;

public interface AccountInterface {

    public AccountDTO accountAbrufen(String username) throws SQLException;
    public void accountErstellen(AccountDTO acc) throws SQLException;
    public void accountLoeschen(int userID) throws SQLException;
    public void accountUpdaten(AccountDTO acc) throws SQLException;
}
