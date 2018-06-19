package Accountsystem;

public class Kaeufer extends Benutzer {

    private int anzahlKaeufe = 0;

    public Kaeufer(String vorname, String nachname, String firma, String telefonnummer, String emailAdresse,/* AccountDTO account,*/
                   Adresse kontaktAdresse) {
        super(vorname, nachname, firma, telefonnummer, emailAdresse, /*account,*/ kontaktAdresse);
    }

    protected void bestellungenAnzeigen() {
        // greift auf die ArrayList<Accountsystem.Bestellung> "account.bestellungen" zu und listet sie auf dem Bildschirm auf
    }

    public int getAnzahlKaeufe() {
        return anzahlKaeufe;
    }

//    public void setAnzahlKaeufe(int anzahlKaeufe) {
//        this.anzahlKaeufe = anzahlKaeufe;
//    }
}
