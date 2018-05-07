public class Adresse {

    public enum AdressTyp{
        LieferAdresse, RechnungsAdresse, KontaktAdresse, AbsendeAdresse, RuecksendeAdresse
    }
    public String strasse;
    public String hausnummer;
    public String plz;
    public String stadt;
    public String land;
    public AdressTyp adresstyp;
}
