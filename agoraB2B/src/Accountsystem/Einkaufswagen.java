package Accountsystem;

import java.io.Serializable;
import java.util.*;

public class Einkaufswagen implements Serializable {

    private AccountDTO accountDTO;
//    private Bestellung bestellung; // Kann über den kaeuferAccount darauf zugreifen
    private ArrayList<EkwProdukt> produkte = new ArrayList<EkwProdukt>(); // Muss unterschieden werden von Produkten im Katalog!
    private int anzahlProdukte = 0; // Soll nur Produkte mit verschiedenen Produkt-IDs berücksichtigen
//    private double mwst; // erstmal nicht nötig


    public Einkaufswagen() {

    }

    public void leeren() {
                                  // Man braucht keinen return Wert, der Button zum leeren wird einfach deaktiviert wenn der Einkaufswagen leer ist.
        produkte.clear();
    }

    public boolean addProduct(EkwProdukt p) {

        anzahlProdukte++;
        return produkte.add(p);
    }

    public boolean removeProduct(EkwProdukt p) { //

//        for (Iterator<Produkt> it = produkte.iterator(); it.hasNext();){
//            Produkt produkte = it.next();          //Produkt produkte checken
//            if (produkte.equals(p)){
//                it.remove();
//                return true;
//            }
//        }
//        return false;

        anzahlProdukte--;
        return produkte.remove(p);  // etwas einfacher ;)
    }

    public boolean produktAnzahlAendern(int anzahl, EkwProdukt p) {

        for (EkwProdukt ekwp : produkte) {
            if(ekwp.equals(p) && ekwp.getProdukt().getBestand() >= anzahl) {
                ekwp.setAnzahl(anzahl);
                return true;
            }
        }
        return false;
    }

    public double berechneGesamtpreis() {

        double sum = 0.0;
        for(EkwProdukt ekwp : produkte) { // "for each"-Schleife
            sum += ekwp.getProdukt().getPreis() * ekwp.getAnzahl();
        }
        return sum;
    }

    public AccountDTO getAccountDTO() {
        return accountDTO;
    }

    public boolean isEmpty() {
        return produkte.isEmpty();
    }

    public void setAccountDTO(AccountDTO accountDTO) {
        this.accountDTO = accountDTO;
    }

//    public Bestellung getBestellung() {
//        return bestellung;
//    }
//
//    public void setBestellung(Bestellung bestellung) {
//        this.bestellung = bestellung;
//    }

    public ArrayList<EkwProdukt> getProdukte() {
        return produkte;
    }

    public void setProdukte(ArrayList<EkwProdukt> produkte) {
        this.produkte = produkte;
    }

    public int getAnzahlProdukte() {
        return anzahlProdukte;
    }

    public void setAnzahlProdukte(int anzahlProdukte) {
        this.anzahlProdukte = anzahlProdukte;
    }

}