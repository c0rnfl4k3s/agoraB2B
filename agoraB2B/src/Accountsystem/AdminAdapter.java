package Accountsystem;

public class AdminAdapter extends Verkaeufer implements AdminAdapterInterface {

    public AdminAdapter(String vorname, String nachname, String firma, String telefonnummer, String emailAdresse, String strasse, String hausnummer, String plz, String stadt, String land) {
        super(vorname, nachname, firma, telefonnummer, emailAdresse, strasse, hausnummer, plz, stadt, land);
    }

    @Override
    public void angebotEntfernen() { // TODO: über ProduktDAO 'produktLoeschen()' aufrufen

    }

//    protected void accountSperren(AccountDTO acc) {
//
//    }
}
