package Accountsystem;

import Datenbank.DTO;
import java.io.Serializable;

public class AccountDTO extends DTO implements Serializable { // Als DTO Objekt benutzt

//    private static int anzahlAccounts = 0;
    private Benutzer benutzer;
    private String name;
    private String passwort;
    private int accountID;
//    private int anzahlRechnungen;
    private String bankname;
    private String iban;
    private String bic;

//    private int
//    private Postfach postfach;
//    private ArrayList<Bankkonto> bankkonten = new ArrayList<>(); // als Array-List, wenn die möglichkeit für mehrere da sein muss
//    private ArrayList<Bestellung> bestellungen;
//    private Einkaufswagen ekw;
//    private ArrayList<ProduktDTO> angeboteneProdukte;

    public AccountDTO(/*Benutzer nBenutzer,*/ String nName, String nPasswort, String nIban, String nBic, String nBankname){
//        anzahlAccounts++;
//        accountID = anzahlAccounts;
//        benutzer = nBenutzer; // Circular dependency Issue
        name = nName;
        passwort = nPasswort;
        bankname = nBankname;
        iban = nIban;
        bic = nBic;

//        bankkonten.add(bankkonto);
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
                return getIban(); // Später berücksichtigen, dass mehrere Bankkonten angelegt werden können!
            case "bicTextfield":
                return getBic();
            case "banknameTextfield":
                return getBankname();

                // ACHTUNG: Später verschiedene Adresstypen berücksichtigen!
            case "strasseTextfield":
                return benutzer.getStrasse();
            case "hausnummerTextfield":
                return benutzer.getHausnummer();
            case "stadtTextfield":
                return benutzer.getStadt();
            case "plzTextfield":
                return benutzer.getPlz();
            case "landTextfield":
                return benutzer.getLand();
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
                setIban(attributeData);
                return true;
            case "bicTextfield":
                setBic(attributeData);
                return true;
            case "banknameTextfield":
                setBankname(attributeData);
                return true;

            // ACHTUNG: Später verschiedene Adresstypen berücksichtigen!
            case "strasseTextfield":
                benutzer.setStrasse(attributeData);
                return true;
            case "hausnummerTextfield":
                benutzer.setHausnummer(attributeData);
                return true;
            case "stadtTextfield":
                benutzer.setStadt(attributeData);
                return true;
            case "plzTextfield":
                benutzer.setPlz(attributeData);
                return true;
            case "landTextfield":
                benutzer.setLand(attributeData);
                return true;
            default:
                return false;
        }
    }

//    public static int getAnzahlAccounts() {
//        return anzahlAccounts;
//    }
//
//    public static void setAnzahlAccounts(int anzahlAccounts) {
//        AccountDTO.anzahlAccounts = anzahlAccounts;
//    }

    public boolean isAdmin() {
        return this.benutzer instanceof AdminAdapter;
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

    public int getAccountID() {
        return accountID;
    }

    public void setAccountID(int accountID) {
        this.accountID = accountID;
    }
//
//    public int getAnzahlRechnungen() {
//        return anzahlRechnungen;
//    }
//
//    public void setAnzahlRechnungen(int anzahlRechnungen) {
//        this.anzahlRechnungen = anzahlRechnungen;
//    }

    public String getBankname() {
        return bankname;
    }

    public void setBankname(String bankname) {
        this.bankname = bankname;
    }

    public String getIban() {
        return iban;
    }

    public void setIban(String iban) {
        this.iban = iban;
    }

    public String getBic() {
        return bic;
    }

    public void setBic(String bic) {
        this.bic = bic;
    }

    //    public Postfach getPostfach() {
//        return postfach;
//    }
//
//    public void setPostfach(Postfach postfach) {
//        this.postfach = postfach;
//    }
//
//    public ArrayList<Bankkonto> getBankkonten() {
//        return bankkonten;
//    }

//    public void addBankkonto(Bankkonto bankkonto) {
//        this.bankkonten.add(bankkonto);
//    }

//    public ArrayList<Bestellung> getBestellungen() {
//        return bestellungen;
//    }
//
//    public void setBestellungen(ArrayList<Bestellung> bestellungen) {
//        this.bestellungen = bestellungen;
//    }
//
//    public Einkaufswagen getEkw() {
//        return ekw;
//    }
//
//    public void setEkw(Einkaufswagen ekw) {
//        this.ekw = ekw;
//    }
//
//    public ArrayList<ProduktDTO> getAngeboteneProdukte() {
//        return angeboteneProdukte;
//    }
//
//    public void setAngeboteneProdukte(ArrayList<ProduktDTO> angeboteneProdukte) {
//        if(!(benutzer instanceof Verkaeufer)) { // später: Exception schmeissen
//            return;
//        }
//        this.angeboteneProdukte = angeboteneProdukte;
//    }
}
