import java.util.*;

public class Account {

    public static int anzahlAccounts;
    public Benutzer user;
    public String name;
    private String passwort;
    public int userID;
    public int anzahlRechnungen;
    public Postfach postfach;
    public Bankkonto bankkonto; // als Array-List, wenn die möglichkeit für mehrere da sein muss
    public ArrayList<Bestellung> bestellungen;
    public Einkaufswagen cart;
    public ArrayList<Produkt> angeboteneProdukte;

    public Account(Benutzer nUser, String nName, String nPasswort, Bankkonto nBankkonto){
        anzahlAccounts++;
        userID = anzahlAccounts;
        user = nUser;
        name = nName;
        passwort = nPasswort;
        bankkonto = nBankkonto;
    }

    public void logout(){

    }

    public boolean profilBearbeiten() {
        return false;
    }

}
