public class Produkt {

    public enum ProduktKategorie{
        BUEROMATERIAL, ELEKTRONIK, RHB, SOFTWARE
    }
    public int produktID;
    public String bezeichnung;
    public String beschreibung;
    public double preis;
    public ProduktKategorie produktKategorie;
    public Einkaufswagen einkaufswagen;
    public Katalog katalog;
    public Account anbietenderAccount;
}
