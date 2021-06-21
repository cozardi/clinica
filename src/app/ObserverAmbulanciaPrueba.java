package app;

import modelo.ambulancia.Ambulancia;

import java.util.Observable;
import java.util.Observer;

public class ObserverAmbulanciaPrueba implements Observer {
    private Ambulancia ambulancia;

    public ObserverAmbulanciaPrueba(Ambulancia ambulancia) {
        this.ambulancia = ambulancia;
        this.ambulancia.addObserver(this);
    }

    @Override
    public void update(Observable o, Object arg) {
        Ambulancia a = (Ambulancia) o;
        if (a == this.ambulancia)
            System.out.println("La Ambulancia esta "+ arg);
        else
            throw new IllegalArgumentException();

    }
}
