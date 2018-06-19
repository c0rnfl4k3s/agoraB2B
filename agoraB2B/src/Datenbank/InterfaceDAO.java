package Datenbank;


import Accountsystem.AccountDTO;
import Accountsystem.NotfallAccount;

import java.sql.SQLException;

public interface InterfaceDAO {

    public void accountErstellen(AccountDTO acc) throws SQLException;
    public boolean accountLoeschen(int userID) throws SQLException;
    public void accountUpdaten(AccountDTO acc) throws SQLException;
}
