package Accountsystem;

public class Verkaeufer extends Kaeufer {

    private int anzahlVerkaeufe = 0;

    public Verkaeufer(String vorname, String nachname, String firma, String telefonnummer, String emailAdresse, /*AccountDTO account,*/
                      Adresse kontaktAdresse) {
        super(vorname, nachname, firma, telefonnummer, emailAdresse,/* account,*/ kontaktAdresse);
    }

    protected void produktAnbieten() {

    }

    protected void angebotLoeschen() {

    }

    protected void angebotBearbeiten() {

    }

    public int getAnzahlVerkaeufe() {
        return anzahlVerkaeufe;
    }

//    public void setAnzahlVerkaeufe(int anzahlVerkaeufe) {
//        this.anzahlVerkaeufe = anzahlVerkaeufe;
//    }
}


