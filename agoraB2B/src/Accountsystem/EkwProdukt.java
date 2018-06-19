package Accountsystem;

import java.io.Serializable;

public class EkwProdukt implements Serializable {

    private Produkt produkt;
    private int anzahl = 1;

    public EkwProdukt(Produkt produkt) {
        this.produkt = produkt;
    }

    public Produkt getProdukt() {
        return produkt;
    }

    public void setProdukt(Produkt produkt) {
        this.produkt = produkt;
    }

    public int getAnzahl() {
        return anzahl;
    }

    public void setAnzahl(int anzahl) {

        if(anzahl < 1)
            return;
        this.anzahl = anzahl;
    }
}
