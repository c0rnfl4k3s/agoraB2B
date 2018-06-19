package Accountsystem;

import java.io.Serializable;
import java.util.ArrayList;

public class Katalog implements Serializable { // Singleton: der katalog ist nur über 'Katalog.instance()' erreichbar

    private static Katalog uniqueInstance;
    private ArrayList<Produkt> bueromaterial;
    private ArrayList<Produkt> elektronik;
    private ArrayList<Produkt> rhb;
    private ArrayList<Produkt> software;
    private int anzahlProdukte;

    private Katalog() {

    }

    public static Katalog instance() {
        if(uniqueInstance == null) {
            uniqueInstance = new Katalog();
        }
        return uniqueInstance;
    }

    public void addProduct(Produkt p) {

        switch(p.getProduktKategorie()) {
            case BUEROMATERIAL:
                bueromaterial.add(p);
                break;
            case ELEKTRONIK:
                elektronik.add(p);
                break;
            case RHB:
                rhb.add(p);
                break;
            case SOFTWARE:
                software.add(p);
                break;
            default:
                return;
        }


//        ArrayList<Produkt> Katalog = new ArrayList<Produkt>();
//        Katalog.add(p);
        anzahlProdukte++;
    }

    public boolean removeProduct(Produkt p) {

        switch(p.getProduktKategorie()) {
            case BUEROMATERIAL:
                bueromaterial.remove(p);
                break;
            case ELEKTRONIK:
                elektronik.remove(p);
                break;
            case RHB:
                rhb.remove(p);
                break;
            case SOFTWARE:
                software.remove(p);
                break;
            default:
                return false;
        }
        anzahlProdukte--;
        return true;
    }

    public ArrayList<Produkt> getFullKatalog() {
        ArrayList<Produkt> ret = new ArrayList<>();
        ret.addAll(bueromaterial);
        ret.addAll(elektronik);
        ret.addAll(rhb);
        ret.addAll(software);
        return ret;
    }

    public ArrayList<Produkt> getBueromaterial() {
        return bueromaterial;
    }

    public void setBueromaterial(ArrayList<Produkt> bueromaterial) {
        this.bueromaterial = bueromaterial;
    }

    public ArrayList<Produkt> getElektronik() {
        return elektronik;
    }

    public void setElektronik(ArrayList<Produkt> elektronik) {
        this.elektronik = elektronik;
    }

    public ArrayList<Produkt> getRhb() {
        return rhb;
    }

    public void setRhb(ArrayList<Produkt> rhb) {
        this.rhb = rhb;
    }

    public ArrayList<Produkt> getSoftware() {
        return software;
    }

    public void setSoftware(ArrayList<Produkt> software) {
        this.software = software;
    }

    public int getAnzahlProdukte() {
        return anzahlProdukte;
    }

    public void setAnzahlProdukte(int anzahlProdukte) {
        this.anzahlProdukte = anzahlProdukte;
    }
}
