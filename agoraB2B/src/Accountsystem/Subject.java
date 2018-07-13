package Accountsystem;

import GUI.Observer;

import java.util.ArrayList;

public abstract class Subject {

    public ArrayList<Observer> observers = new ArrayList<>();

    public void attach(Observer o) {
        observers.add(o);
    }
    public void detach(Observer o) {
        observers.remove(o);
    }
    public void notifyObservers() {
        for(Observer o : observers) {
            o.update();
        }
    }
}
