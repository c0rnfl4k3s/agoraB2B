import java.util.*;

public class Einkaufswagen {

    public Account account;
    public Bestellung bestellung; // Muss identisch sein aus dem Account!
    public ArrayList<Produkt> produkte;
    public int anzahlProdukte;
    public double mwst;

    public boolean leeren() {
        return false;
    }

    public boolean addProduct(Produkt p) {
        return false;
    }

    public boolean removeProduct(Produkt p) {
        return false;
    }

    public double berechneGesamtpreis() {
        return 0.0;
    }
}