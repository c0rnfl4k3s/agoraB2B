package Datenbank;

import Accountsystem.AccountDTO;
import Accountsystem.ProduktDTO;
import Accountsystem.ProduktKategorie;

import java.sql.*;
import java.util.ArrayList;

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

    
    /**
     * Schreibt alle nötigen Attributwerte eines Produkts in die Datenbank
     */
    @Override
    public void produktAnbieten(ProduktDTO neuesProdukt) throws SQLException {

        if(conn != null) {
            try {
                String sql = "INSERT INTO Produkte(bezeichnung, beschreibung, preis, produktKategorie, accountID) " + "VALUES(?,?,?,?,?)";
                PreparedStatement ps = conn.prepareStatement(sql);
                ps.setString(1, neuesProdukt.getBezeichnung());
                ps.setString(2, neuesProdukt.getBeschreibung());
                ps.setDouble(3, neuesProdukt.getPreis());
                ps.setString(4, neuesProdukt.getProduktKategorie().toString());
                ps.setInt(5, neuesProdukt.getAnbietenderAccountDTO().getAccountID());
                ps.executeUpdate(sql);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * ändert die Attributwerte eines Produkts in der Datenbank durch Ausführung des Benutzers.
     */
    @Override
    public void produktBearbeiten(ProduktDTO produkt) throws SQLException {

        if(conn != null) {
            try {
                Statement st = conn.createStatement();
                String sql = "UPDATE Produkte SET" + " bezeichnung = '" + produkt.getBezeichnung() +
                        "', beschreibung = '" + produkt.getBeschreibung() +
                        "', preis = '" + produkt.getPreis() +
                        "', produktKategorie = '" + produkt.getProduktKategorie() +
                        "' WHERE produktID = " + produkt.getProduktID();
                st.executeUpdate(sql);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * Entfernt ein Produkt aus der Datenbank.
     */
    @Override
    public void produktEntfernen(int produktID) throws SQLException {

        if(conn != null) {
            try {
                Statement st = conn.createStatement();
                String sql = "DELETE FROM Produkte WHERE produktID = " + produktID;
                st.executeUpdate(sql);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * Gibt alle Produkte aus der Datenbank in einer Liste zurück.
     */
    @Override
    public ArrayList<ProduktDTO> readProducts() throws SQLException {
        ArrayList<ProduktDTO> ret = new ArrayList<>();
        if(conn != null) {

            Statement st = conn.createStatement();
            String sql = "SELECT * FROM Produkte";
            ResultSet rs = st.executeQuery(sql);
            while(rs.next()) {
                ProduktDTO p = new ProduktDTO(rs.getString("bezeichnung"), rs.getString("beschreibung"), rs.getDouble("preis")
                        , ProduktKategorie.valueOf(rs.getString("produktKategorie")), new AccountDTO("","","","",""));
                ret.add(p);
            }
        }
        return ret;
    }
}
