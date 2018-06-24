package Accountsystem;

import java.io.Serializable;
import java.util.ArrayList;

public class Katalog implements Serializable { // Singleton: der katalog ist nur über 'Katalog.instance()' erreichbar

    private static Katalog uniqueInstance;
    private ArrayList<ProduktDTO> bueromaterial;
    private ArrayList<ProduktDTO> elektronik;
    private ArrayList<ProduktDTO> rhb;
    private ArrayList<ProduktDTO> software;
    private int anzahlProdukte;

    private Katalog() {

    }

    public static Katalog instance() {
        if(uniqueInstance == null) {
            uniqueInstance = new Katalog();
        }
        return uniqueInstance;
    }

    public void addProduct(ProduktDTO p) {

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


//        ArrayList<ProduktDTO> Katalog = new ArrayList<ProduktDTO>();
//        Katalog.add(p);
        anzahlProdukte++;
    }

    public boolean removeProduct(ProduktDTO p) {

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

    public ArrayList<ProduktDTO> getFullKatalog() {
        ArrayList<ProduktDTO> ret = new ArrayList<>();
        ret.addAll(bueromaterial);
        ret.addAll(elektronik);
        ret.addAll(rhb);
        ret.addAll(software);
        return ret;
    }

    public ArrayList<ProduktDTO> getBueromaterial() {
        return bueromaterial;
    }

    public void setBueromaterial(ArrayList<ProduktDTO> bueromaterial) {
        this.bueromaterial = bueromaterial;
    }

    public ArrayList<ProduktDTO> getElektronik() {
        return elektronik;
    }

    public void setElektronik(ArrayList<ProduktDTO> elektronik) {
        this.elektronik = elektronik;
    }

    public ArrayList<ProduktDTO> getRhb() {
        return rhb;
    }

    public void setRhb(ArrayList<ProduktDTO> rhb) {
        this.rhb = rhb;
    }

    public ArrayList<ProduktDTO> getSoftware() {
        return software;
    }

    public void setSoftware(ArrayList<ProduktDTO> software) {
        this.software = software;
    }

    public int getAnzahlProdukte() {
        return anzahlProdukte;
    }

    public void setAnzahlProdukte(int anzahlProdukte) {
        this.anzahlProdukte = anzahlProdukte;
    }
}
