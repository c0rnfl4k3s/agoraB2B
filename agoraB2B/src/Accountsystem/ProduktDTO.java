package Accountsystem;

import java.io.Serializable;

public class ProduktDTO implements Serializable {

    private int produktID;
    private String bezeichnung;
    private String beschreibung;
    private double preis;
    private ProduktKategorie produktKategorie;
//    private Einkaufswagen einkaufswagen;
//    private Katalog katalog;
    private AccountDTO anbietenderAccountDTO;
//    private int bestand;

    public ProduktDTO(String bezeichnung, String beschreibung, double preis, ProduktKategorie produktKategorie, AccountDTO anbietenderAccountDTO) {
//        this.produktID = produktID;
        this.bezeichnung = bezeichnung;
        this.beschreibung = beschreibung;
        this.preis = preis;
        this.produktKategorie = produktKategorie;
//        this.einkaufswagen = einkaufswagen;
//        this.katalog = katalog;
        this.anbietenderAccountDTO = anbietenderAccountDTO;
//        this.bestand = bestand;
    }

    public int getProduktID() {
        return produktID;
    }

    public void setProduktID(int produktID) {
        this.produktID = produktID;
    }

    public String getBezeichnung() {
        return bezeichnung;
    }

    public void setBezeichnung(String bezeichnung) {
        this.bezeichnung = bezeichnung;
    }

    public String getBeschreibung() {
        return beschreibung;
    }

    public void setBeschreibung(String beschreibung) {
        this.beschreibung = beschreibung;
    }

    public double getPreis() {
        return preis;
    }

    public void setPreis(double preis) {
        this.preis = preis;
    }

    public ProduktKategorie getProduktKategorie() {
        return produktKategorie;
    }

    public void setProduktKategorie(ProduktKategorie produktKategorie) {
        this.produktKategorie = produktKategorie;
    }

//    public Einkaufswagen getEinkaufswagen() {
//        return einkaufswagen;
//    }
//
//    public void setEinkaufswagen(Einkaufswagen einkaufswagen) {
//        this.einkaufswagen = einkaufswagen;
//    }
//
//    public Katalog getKatalog() {
//        return katalog;
//    }
//
//    public void setKatalog(Katalog katalog) {
//        this.katalog = katalog;
//    }

    public AccountDTO getAnbietenderAccountDTO() {
        return anbietenderAccountDTO;
    }

    public void setAnbietenderAccountDTO(AccountDTO anbietenderAccountDTO) {
        this.anbietenderAccountDTO = anbietenderAccountDTO;
    }

//    public int getBestand() {
//        return bestand;
//    }
//
//    public void setBestand(int bestand) {
//        this.bestand = bestand;
//    }
}
