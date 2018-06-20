package Datenbank;

import Accountsystem.AccountDTO;
import Accountsystem.NotfallAccount;

import java.sql.*;



public class AccountDAO implements InterfaceDAO {

    private Connection conn;

    public AccountDAO()
    {
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

 //   private Connection getInstance()
 //   {
 //       return conn;
 //   }

    //@Override
    public void accountErstellen(AccountDTO neuerAccountDTO) {
    //    conn = getInstance(); // getCLass oder getInstance

        if (conn!=null){


            try {
                String vorname, nachname, firma, telefonnummer, emailAdresse, passwort, iban;
                String bic, bankName, strasse, hausnummer, plz, stadt, land;
                int userID;

                vorname = neuerAccountDTO.getBenutzer().getVorname();
                nachname = neuerAccountDTO.getBenutzer().getNachname();
                firma = neuerAccountDTO.getBenutzer().getFirma();
                telefonnummer = neuerAccountDTO.getBenutzer().getTelefonnummer();
                emailAdresse = neuerAccountDTO.getBenutzer().getEmailAdresse();
                passwort = neuerAccountDTO.getPasswort();
                iban = neuerAccountDTO.getBankkonten().get(0).getIban();
                bic = neuerAccountDTO.getBankkonten().get(0).getBic();
                bankName = neuerAccountDTO.getBankkonten().get(0).getBankName();
                strasse = neuerAccountDTO.getBenutzer().getKontaktAdresse().getStrasse();
                hausnummer = neuerAccountDTO.getBenutzer().getKontaktAdresse().getHausnummer();
                plz = neuerAccountDTO.getBenutzer().getKontaktAdresse().getPlz();
                stadt = neuerAccountDTO.getBenutzer().getKontaktAdresse().getStadt();
                land = neuerAccountDTO.getBenutzer().getKontaktAdresse().getLand();
                userID = neuerAccountDTO.getUserID();


                // TODO: VAriabelen direktt in Queries
                String sql = "INSERT INTO Account(userID, passwort)" + "VALUES(?,?)";
                String sql1 = "INSERT INTO Benutzer(vorname, nachname, firma, telefonnummer, emailAdresse, userID)" + "VALUES(?,?,?,?,?,?)";
                String sql2 = "INSERT INTO Bankkonto (iban,bic,bankName, userID)" + "VALUES(?,?,?,?)";
                String sql3 = "INSERT INTO Adresse(strasse, hausnummer, plz, stadt, land, userID)" + "VALUES(?,?,?,?,?,?)";

                PreparedStatement preparedStatement = conn.prepareStatement(sql);
                PreparedStatement preparedStatement1 = conn.prepareStatement(sql1);
                PreparedStatement preparedStatement2 = conn.prepareStatement(sql2);
                PreparedStatement preparedStatement3 = conn.prepareStatement(sql3);

                // Table Account
                preparedStatement.setInt(1, userID);
                preparedStatement.setString(2, passwort);

                //Table Benutzer
                preparedStatement1.setString(1, vorname);
                preparedStatement1.setString(2, nachname);
                preparedStatement1.setString(3, firma);
                preparedStatement1.setString(4, telefonnummer);
                preparedStatement1.setString(5, emailAdresse);
                preparedStatement1.setInt(6, userID);

                //Table Bankkonto
                preparedStatement2.setString(1, iban);
                preparedStatement2.setString(2, bic);
                preparedStatement2.setString(3, bankName);
                preparedStatement2.setInt(4, userID);

                //Table Adresse
                preparedStatement3.setString(1, strasse);
                preparedStatement3.setString(2, hausnummer);
                preparedStatement3.setString(3, plz);
                preparedStatement3.setString(4, stadt);
                preparedStatement3.setString(5, land);
                preparedStatement3.setInt(6, userID);

                preparedStatement.executeUpdate();
                preparedStatement1.executeUpdate();
                preparedStatement2.executeUpdate();
                preparedStatement3.executeUpdate();

            } catch (SQLException e){
                e.printStackTrace();
            }
        }
    }

    //@Override
    public void accountUpdaten(AccountDTO acc) {

    }



    @Override
    public boolean accountLoeschen(int userID) {
        return false;
    }
}
