package Datenbank;

import Accountsystem.*;

import java.sql.*;
import java.util.ArrayList;


public class AccountDAO implements AccountInterface {

    private Connection conn;
    private ConnectionManager meineSitzung;

    public AccountDAO()
    {
        meineSitzung = new ConnectionManager();
        conn = meineSitzung.connectDB("db4free.net", "swttest", "swttest", "swtadmin");

        if(conn!=null)///Verbindungsaufbau erfolgreich
        {
            System.out.println("Verbindung hergestellt.");
        }
        else
        {
            System.out.println("keine Verbindung zur Datenbank.");
        }
    }

 //   private Connection getInstance()
 //   {
 //       return conn;
 //   }

    @Override
    public AccountDTO accountAbrufen(String username) throws SQLException {

        AccountDTO result = null;
        int accountID = 0;
        if(conn != null) {

            Statement st = conn.createStatement();
            Statement st1 = conn.createStatement();

            String sql = "SELECT * FROM Account WHERE name = '" + username + "'";
            ResultSet rs = st.executeQuery(sql);
            if(rs.next()) {
                accountID = rs.getInt("accountID");
            }
            String sql1 = "SELECT * FROM Benutzer WHERE accountID = " + accountID;
            ResultSet rs1 = st1.executeQuery(sql1);
            rs1.next();

            result = new AccountDTO(rs.getString("name"), rs.getString("passwort"), rs.getString("iban"), rs.getString("bic"), rs.getString("bankname"));
            Benutzer ben;
            if(rs.getString("name").contains("admin")) { // Wenn der String "admin" im Usernamen enthalten ist, wird der neue User als AdminAdapter registriert. (Zum testen der Adminfunktionen)
                ben = new AdminAdapter(rs1.getString("vorname"), rs1.getString("nachname"), rs1.getString("firma"), rs1.getString("telefonnummer"),
                        rs1.getString("emailAdresse"), rs1.getString("strasse"), rs1.getString("hausnummer"), rs1.getString("plz"), rs1.getString("stadt"), rs1.getString("land"));
            } else if (rs.getString("name").contains("verkaeufer")) {
                ben = new Verkaeufer(rs1.getString("vorname"), rs1.getString("nachname"), rs1.getString("firma"), rs1.getString("telefonnummer"),
                        rs1.getString("emailAdresse"), rs1.getString("strasse"), rs1.getString("hausnummer"), rs1.getString("plz"), rs1.getString("stadt"), rs1.getString("land"));
            } else {
                ben = new Kaeufer(rs1.getString("vorname"), rs1.getString("nachname"), rs1.getString("firma"), rs1.getString("telefonnummer"),
                        rs1.getString("emailAdresse"), rs1.getString("strasse"), rs1.getString("hausnummer"), rs1.getString("plz"), rs1.getString("stadt"), rs1.getString("land"));
            }

            ben.setAccountDTO(result);
            result.setBenutzer(ben);
            result.setAccountID(rs.getInt("accountID"));
        }
        return result;
    }

    @Override
    public boolean checkNames(String username) throws SQLException { // return true, wenn username noch nicht vergeben.

        if(conn != null) {

            Statement st = conn.createStatement();
            String sql = "SELECT * FROM Account";
            ResultSet rs = st.executeQuery(sql);
            while(rs.next()) {
                if(rs.getString("name").equals(username)) {
                    return false;
                }
            }
        }
        return true;
    }

    @Override
    public ArrayList<AccountDTO> readAccounts() throws SQLException{
        ArrayList<AccountDTO> ret = new ArrayList<>();
        if(conn != null) {

            Statement st = conn.createStatement();
            Statement st1 = conn.createStatement();
            String sql = "SELECT * FROM Account";
            String sql1 = "SELECT * FROM Benutzer";
            ResultSet rs = st.executeQuery(sql);
            ResultSet rs1 = st1.executeQuery(sql1);
            while(rs.next() && rs1.next()) {
                Benutzer b;
                if(rs.getString("name").contains("admin")) { // Wenn der String "admin" im Usernamen enthalten ist, wird der neue User als AdminAdapter registriert. (Zum testen der Adminfunktionen)
                    b = new AdminAdapter(rs1.getString("vorname"), rs1.getString("nachname"), rs1.getString("firma"), rs1.getString("telefonnummer"),
                            rs1.getString("emailAdresse"), rs1.getString("strasse"), rs1.getString("hausnummer"), rs1.getString("plz"), rs1.getString("stadt"), rs1.getString("land"));
                } else if (rs.getString("name").contains("verkaeufer")) {
                    b = new Verkaeufer(rs1.getString("vorname"), rs1.getString("nachname"), rs1.getString("firma"), rs1.getString("telefonnummer"),
                            rs1.getString("emailAdresse"), rs1.getString("strasse"), rs1.getString("hausnummer"), rs1.getString("plz"), rs1.getString("stadt"), rs1.getString("land"));
                } else {
                    b = new Kaeufer(rs1.getString("vorname"), rs1.getString("nachname"), rs1.getString("firma"), rs1.getString("telefonnummer"),
                            rs1.getString("emailAdresse"), rs1.getString("strasse"), rs1.getString("hausnummer"), rs1.getString("plz"), rs1.getString("stadt"), rs1.getString("land"));
                }
                AccountDTO a = new AccountDTO(rs.getString("name"), rs.getString("passwort"), rs.getString("iban"), rs.getString("bic"), rs.getString("bankname"));
                b.setAccountDTO(a);
                a.setBenutzer(b);
                a.setAccountID(rs.getInt("accountID"));
                ret.add(a);
            }
        }
        return ret;
    }

    @Override
    public void accountErstellen(AccountDTO neuerAccountDTO) {
    //    conn = getInstance(); // getCLass oder getInstance

        if (conn!=null){


            try {
                String user, vorname, nachname, firma, telefonnummer, emailAdresse, passwort, iban;
                String bic, bankname, strasse, hausnummer, plz, stadt, land;
                int accountID = 0;

                user = neuerAccountDTO.getName();
                vorname = neuerAccountDTO.getBenutzer().getVorname();
                nachname = neuerAccountDTO.getBenutzer().getNachname();
                firma = neuerAccountDTO.getBenutzer().getFirma();
                telefonnummer = neuerAccountDTO.getBenutzer().getTelefonnummer();
                emailAdresse = neuerAccountDTO.getBenutzer().getEmailAdresse();
                passwort = neuerAccountDTO.getPasswort();
                iban = neuerAccountDTO.getIban();
                bic = neuerAccountDTO.getBic();
                bankname = neuerAccountDTO.getBankname();
                strasse = neuerAccountDTO.getBenutzer().getStrasse();
                hausnummer = neuerAccountDTO.getBenutzer().getHausnummer();
                plz = neuerAccountDTO.getBenutzer().getPlz();
                stadt = neuerAccountDTO.getBenutzer().getStadt();
                land = neuerAccountDTO.getBenutzer().getLand();


                String sql = "INSERT INTO Account(name, passwort, iban, bic, bankname)" + "VALUES(?,?,?,?,?)"; // id rausgenommen, weil autoinkrement
                String sql1 = "INSERT INTO Benutzer(vorname, nachname, firma, telefonnummer, emailAdresse, strasse, hausnummer, plz, stadt, land, accountID)" + "VALUES(?,?,?,?,?,?,?,?,?,?,?)";
//                String sql2 = "INSERT INTO Bankkonto (iban,bic,bankName, userID)" + "VALUES(?,?,?,?)";
//                String sql3 = "INSERT INTO Adresse(strasse, hausnummer, plz, stadt, land, userID)" + "VALUES(?,?,?,?,?,?)";

                PreparedStatement preparedStatement = conn.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);
                PreparedStatement preparedStatement1 = conn.prepareStatement(sql1);
//                PreparedStatement preparedStatement2 = conn.prepareStatement(sql2);
//                PreparedStatement preparedStatement3 = conn.prepareStatement(sql3);

                // Table Account
                preparedStatement.setString(1, user);
                preparedStatement.setString(2, passwort);
                preparedStatement.setString(3, iban);
                preparedStatement.setString(4, bic);
                preparedStatement.setString(5, bankname);
                preparedStatement.executeUpdate();
                ResultSet rs = preparedStatement.getGeneratedKeys();
                if(rs.next()) {
                    accountID = rs.getInt(1);
                    neuerAccountDTO.setAccountID(accountID);
                }

                //Table Benutzer
                preparedStatement1.setString(1, vorname);
                preparedStatement1.setString(2, nachname);
                preparedStatement1.setString(3, firma);
                preparedStatement1.setString(4, telefonnummer);
                preparedStatement1.setString(5, emailAdresse);
                preparedStatement1.setString(6, strasse);
                preparedStatement1.setString(7, hausnummer);
                preparedStatement1.setString(8, plz);
                preparedStatement1.setString(9, stadt);
                preparedStatement1.setString(10, land);
                preparedStatement1.setInt(11, accountID);
                preparedStatement1.executeUpdate();

            } catch (SQLException e){
                e.printStackTrace();
            }
        }
    }

    @Override
    public void accountUpdaten(AccountDTO acc) {

        if(conn != null) {
            try {
                Statement st = conn.createStatement();
                Statement st1 = conn.createStatement();
                String sql = "UPDATE Account SET" + " name = '" + acc.getName() +
                        "', iban = '" + acc.getIban() +
                        "', bic = '" + acc.getBic() +
                        "', bankname = '" + acc.getBankname() +
                        "' WHERE accountID = " + acc.getAccountID();
                String sql1 = "UPDATE Benutzer SET" + " vorname = '" + acc.getBenutzer().getVorname() +
                        "', nachname = '" + acc.getBenutzer().getNachname() +
                        "', firma = '" + acc.getBenutzer().getFirma() +
                        "', telefonnummer = '" + acc.getBenutzer().getTelefonnummer() +
                        "', emailAdresse = '" + acc.getBenutzer().getEmailAdresse() +
                        "', strasse = '" + acc.getBenutzer().getStrasse() +
                        "', hausnummer = '" + acc.getBenutzer().getHausnummer() +
                        "', plz = '" + acc.getBenutzer().getPlz() +
                        "', stadt = '" + acc.getBenutzer().getStadt() +
                        "', land = '" + acc.getBenutzer().getLand() +
                        "' WHERE accountID = " + acc.getAccountID();
                st.executeUpdate(sql);
                st1.executeUpdate(sql1);
            } catch (Exception e) {
                e.printStackTrace();
            }

        }

    }



    @Override
    public void accountLoeschen(int accountID) {

        if(conn != null) {
            try {
                Statement st = conn.createStatement();
                Statement st1 = conn.createStatement();
                String sql = "DELETE FROM Benutzer WHERE accountID = " + accountID;
                String sql1 = "DELETE FROM Account WHERE accountID = " + accountID;
                st.executeUpdate(sql);
                st1.executeUpdate(sql1);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public void disconnectDB() {

        meineSitzung.close();
    }
}
