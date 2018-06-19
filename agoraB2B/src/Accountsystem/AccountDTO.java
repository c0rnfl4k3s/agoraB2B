package Accountsystem;

import java.io.Serializable;
import java.util.*;

public class AccountDTO implements Serializable { // Als DTO Objekt benutzt

    private static int anzahlAccounts = 0;
    private Benutzer benutzer;
    private String name;
    private String passwort;
    private int userID;
    private int anzahlRechnungen;
    private Postfach postfach;
    private ArrayList<Bankkonto> bankkonten = new ArrayList<>(); // als Array-List, wenn die möglichkeit für mehrere da sein muss
    private ArrayList<Bestellung> bestellungen;
    private Einkaufswagen ekw;
    private ArrayList<Produkt> angeboteneProdukte;

    public AccountDTO(/*Benutzer nBenutzer,*/ String nName, String nPasswort, Bankkonto bankkonto){
        anzahlAccounts++;
        userID = anzahlAccounts;
//        benutzer = nBenutzer; // Circular dependency Issue
        name = nName;
        passwort = nPasswort;
        bankkonten.add(bankkonto);
 //       ekw = new Einkaufswagen();
//        Statement stat = conn.createStatement(); // JDBC einlesen
//        stat.execute("INSERT INTO namen VALUES(3, 'max', '32')");

        // postfach = new Postfach();// (postfach muss danach den account "kennenlernen" !!
    }

//    public AccountDTO() { // Nur zu testzwecken, bitte wieder löschen
//
//        ekw = new Einkaufswagen();
//    }

//    public void logout(){
//        // öffnet wieder die Start-Scene
//    }

//    public void profilBearbeiten() {
//        wird innerhalb der Profil-Scene über einen Button "Profil bearbeiten" aufgerufen.
//        dadurch werden die Textfelder in denen die Userdaten stehen EDITABLE und der Button "Profil bearbeiten" verschwindet.
//        Außerdem erscheinen zwei neue Buttons: "Speichern" und "Abbrechen"
//    }

    public String getAttribute(String fxid) {
        switch(fxid) {
            case "vornameTextfield":
                return benutzer.getVorname();
            case "userTextfield":
                return name;
            case "emailTextfield":
                return benutzer.getEmailAdresse();
            case "telTextfield":
                return benutzer.getTelefonnummer();
            case "nachnameTextfield":
                return benutzer.getNachname();
            case "firmaTextfield":
                return benutzer.getFirma();
            case "ibanTextfield":
                return bankkonten.get(0).getIban(); // Später berücksichtigen, dass mehrere Bankkonten angelegt werden können!
            case "bicTextfield":
                return bankkonten.get(0).getBic();
            case "banknameTextfield":
                return bankkonten.get(0).getBankName();

                // ACHTUNG: Später verschiedene Adresstypen berücksichtigen!
            case "strasseTextfield":
                return benutzer.getKontaktAdresse().getStrasse();
            case "hausnummerTextfield":
                return benutzer.getKontaktAdresse().getHausnummer();
            case "stadtTextfield":
                return benutzer.getKontaktAdresse().getStadt();
            case "plzTextfield":
                return benutzer.getKontaktAdresse().getPlz();
            case "landTextfield":
                return benutzer.getKontaktAdresse().getLand();
            default:
                return "ÜBERTRAGUNGSFEHLER";
        }
    }

    public boolean setAttribute(String attributeData, String fxid) {
        switch (fxid) {
            case "vornameTextfield":
                benutzer.setVorname(attributeData);
                return true;
            case "userTextfield":
                setName(attributeData);
                return true;
            case "emailTextfield":
                benutzer.setEmailAdresse(attributeData);
                return true;
            case "telTextfield":
                benutzer.setTelefonnummer(attributeData);
                return true;
            case "nachnameTextfield":
                benutzer.setNachname(attributeData);
                return true;
            case "firmaTextfield":
                benutzer.setFirma(attributeData);
                return true;
            case "ibanTextfield": // Später berücksichtigen, dass mehrere Bankkonten angelegt werden können!
                bankkonten.get(0).setIban(attributeData);
                return true;
            case "bicTextfield":
                bankkonten.get(0).setBic(attributeData);
                return true;
            case "banknameTextfield":
                bankkonten.get(0).setBankName(attributeData);
                return true;

            // ACHTUNG: Später verschiedene Adresstypen berücksichtigen!
            case "strasseTextfield":
                benutzer.getKontaktAdresse().setStrasse(attributeData);
                return true;
            case "hausnummerTextfield":
                benutzer.getKontaktAdresse().setHausnummer(attributeData);
                return true;
            case "stadtTextfield":
                benutzer.getKontaktAdresse().setStadt(attributeData);
                return true;
            case "plzTextfield":
                benutzer.getKontaktAdresse().setPlz(attributeData);
                return true;
            case "landTextfield":
                benutzer.getKontaktAdresse().setLand(attributeData);
                return true;
            default:
                return false;
        }
    }

    public static int getAnzahlAccounts() {
        return anzahlAccounts;
    }

    public static void setAnzahlAccounts(int anzahlAccounts) {
        AccountDTO.anzahlAccounts = anzahlAccounts;
    }

    public boolean isAdmin() {
        return this.benutzer instanceof Admin;
    }

    public Benutzer getBenutzer() {
        return benutzer;
    }

    public void setBenutzer(Benutzer benutzer) {
        this.benutzer = benutzer;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPasswort() {
        return this.passwort;
    }

    public void setPasswort(String passwort) {
        this.passwort = passwort;
    }

    public int getUserID() {
        return userID;
    }

    public void setUserID(int userID) {
        this.userID = userID;
    }

    public int getAnzahlRechnungen() {
        return anzahlRechnungen;
    }

    public void setAnzahlRechnungen(int anzahlRechnungen) {
        this.anzahlRechnungen = anzahlRechnungen;
    }

    public Postfach getPostfach() {
        return postfach;
    }

    public void setPostfach(Postfach postfach) {
        this.postfach = postfach;
    }

    public ArrayList<Bankkonto> getBankkonten() {
        return bankkonten;
    }

//    public void addBankkonto(Bankkonto bankkonto) {
//        this.bankkonten.add(bankkonto);
//    }

    public ArrayList<Bestellung> getBestellungen() {
        return bestellungen;
    }

    public void setBestellungen(ArrayList<Bestellung> bestellungen) {
        this.bestellungen = bestellungen;
    }

    public Einkaufswagen getEkw() {
        return ekw;
    }

    public void setEkw(Einkaufswagen ekw) {
        this.ekw = ekw;
    }

    public ArrayList<Produkt> getAngeboteneProdukte() {
        return angeboteneProdukte;
    }

    public void setAngeboteneProdukte(ArrayList<Produkt> angeboteneProdukte) {
        if(!(benutzer instanceof Verkaeufer)) { // später: Exception schmeissen
            return;
        }
        this.angeboteneProdukte = angeboteneProdukte;
    }
}
