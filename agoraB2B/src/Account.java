import java.util.*;

public class Account {

    public ArrayList<Benutzer> users;
    public String name;
    private String passwort;
    public int userID;
    public int anzahlRechnungen;
    public Postfach postfach;
    public Bankkonto bankkonto; // als Array-List, wenn die möglichkeit für mehrere da sein muss
    public Bestellung bestellung;
    public Einkaufswagen cart;
    public ArrayList<Produkt> angeboteneProdukte;

    public Account

    public void logout(){

    }

    public boolean profilBearbeiten() {
        if (bankkonto != this.bankkonto){
            return true;}
        else
        {return false;}
    }

}
