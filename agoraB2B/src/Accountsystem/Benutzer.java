package Accountsystem;

import java.io.Serializable;

public abstract class Benutzer implements Serializable {

    private String vorname;
    private String nachname;
    private String firma;
    private String telefonnummer;
    private String emailAdresse;
    private AccountDTO accountDTO;

    private Adresse kontaktAdresse, lieferAdresse, rechnungsAdresse, absendeAdresse, ruecksendeAdresse;

    public Benutzer(String vorname, String nachname, String firma, String telefonnummer, String emailAdresse,
                    /*AccountDTO accountDTO,*/ Adresse kontaktAdresse) {
        this.vorname = vorname;
        this.nachname = nachname;
        this.firma = firma;
        this.telefonnummer = telefonnummer;
        this.emailAdresse = emailAdresse;
//        this.accountDTO = accountDTO; // circular dependency issue
        this.kontaktAdresse = kontaktAdresse; // erstmal Kontaktadresse für alles eintragen, später über setter anpassbar
        this.lieferAdresse = kontaktAdresse;
        lieferAdresse.setAdresstyp(AdressTyp.LieferAdresse);
        this.rechnungsAdresse = kontaktAdresse;
        rechnungsAdresse.setAdresstyp(AdressTyp.RechnungsAdresse);
        this.absendeAdresse = kontaktAdresse;
        absendeAdresse.setAdresstyp(AdressTyp.AbsendeAdresse);
        this.ruecksendeAdresse = kontaktAdresse;
        ruecksendeAdresse.setAdresstyp(AdressTyp.RuecksendeAdresse);
    }

    public void produkteSuchen(String suchwort) {
        // TODO
        // Accountsystem.Produkt-Liste im Accountsystem.Katalog von vorne bis hinten durchlaufen und alle treffer in eine neue ArrayList<Accountsystem.Produkt> packen, diese dann anzeigen
    }

    // Die Funktion "profilBearbeiten()" kann einfach über den Accountsystem.AccountDTO ausgeführt werden und wurde hier daher gelöscht.

    public void accountLoeschen() {
        // Entfernt alle DB-Einträge zu diesem Accountsystem.Benutzer. (Bei der Verkaufs-Historie von Verkäufern steht dann nur noch "gelöschter Accountsystem.Benutzer" oder so)
    }

    public String toString() {
        return vorname +" "+ nachname;
    }

    public String getVorname() {
        return vorname;
    }

    public void setVorname(String vorname) {
        this.vorname = vorname;
    }

    public String getNachname() {
        return nachname;
    }

    public void setNachname(String nachname) {
        this.nachname = nachname;
    }

    public String getFirma() {
        return firma;
    }

    public void setFirma(String firma) {
        this.firma = firma;
    }

    public String getTelefonnummer() {
        return telefonnummer;
    }

    public void setTelefonnummer(String telefonnummer) {
        this.telefonnummer = telefonnummer;
    }

    public String getEmailAdresse() {
        return emailAdresse;
    }

    public void setEmailAdresse(String emailAdresse) {
        this.emailAdresse = emailAdresse;
    }

    public AccountDTO getAccountDTO() {
        return accountDTO;
    }

    public void setAccountDTO(AccountDTO accountDTO) {
        this.accountDTO = accountDTO;
    }

    // setter für die Adressen müssen den AdressTyp der übergebenen Adresse beachten !!

    public Adresse getKontaktAdresse() {
        return kontaktAdresse;
    }

    public void setKontaktAdresse(Adresse kontaktAdresse) {
        this.kontaktAdresse = kontaktAdresse;
        this.kontaktAdresse.setAdresstyp(AdressTyp.KontaktAdresse);
    }

    public Adresse getLieferAdresse() {
        return lieferAdresse;
    }

    public void setLieferAdresse(Adresse lieferAdresse) {
        this.lieferAdresse = lieferAdresse;
        this.lieferAdresse.setAdresstyp(AdressTyp.LieferAdresse);
    }

    public Adresse getRechnungsAdresse() {
        return rechnungsAdresse;
    }

    public void setRechnungsAdresse(Adresse rechnungsAdresse) {
        this.rechnungsAdresse = rechnungsAdresse;
        this.rechnungsAdresse.setAdresstyp(AdressTyp.RechnungsAdresse);
    }

    public Adresse getAbsendeAdresse() {
        return absendeAdresse;
    }

    public void setAbsendeAdresse(Adresse absendeAdresse) {
        this.absendeAdresse = absendeAdresse;
        this.absendeAdresse.setAdresstyp(AdressTyp.AbsendeAdresse);
    }

    public Adresse getRuecksendeAdresse() {
        return ruecksendeAdresse;
    }

    public void setRuecksendeAdresse(Adresse ruecksendeAdresse) {
        this.ruecksendeAdresse = ruecksendeAdresse;
        this.ruecksendeAdresse.setAdresstyp(AdressTyp.RuecksendeAdresse);
    }
}
