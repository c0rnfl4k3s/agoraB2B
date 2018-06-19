package Accountsystem;

public class Admin extends Verkaeufer {

    public Admin(String vorname, String nachname, String firma, String telefonnummer, String emailAdresse, /*AccountDTO account,*/ Adresse kontaktAdresse) {
        super(vorname, nachname, firma, telefonnummer, emailAdresse,/* account,*/ kontaktAdresse);
    }

    protected void angeboteEntfernen() {

    }

    protected void accountSperren(AccountDTO acc) {

    }
}
