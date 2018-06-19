package Accountsystem;

import java.io.Serializable;

public class Chat implements Serializable {

    private Postfach p1, p2;
    private int chatID;
    private int anzahlNachrichten;

    public Chat(Postfach p1, Postfach p2, int chatID, int anzahlNachrichten) {
        this.p1 = p1;
        this.p2 = p2;
        this.chatID = chatID;
        this.anzahlNachrichten = anzahlNachrichten;
    }

    public void nachrichtSenden() {

    }

    public Postfach getP1() {
        return p1;
    }

    public void setP1(Postfach p1) {
        this.p1 = p1;
    }

    public Postfach getP2() {
        return p2;
    }

    public void setP2(Postfach p2) {
        this.p2 = p2;
    }

    public int getChatID() {
        return chatID;
    }

    public void setChatID(int chatID) {
        this.chatID = chatID;
    }

    public int getAnzahlNachrichten() {
        return anzahlNachrichten;
    }

    public void setAnzahlNachrichten(int anzahlNachrichten) {
        this.anzahlNachrichten = anzahlNachrichten;
    }
}
