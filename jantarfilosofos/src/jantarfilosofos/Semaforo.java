package jantarfilosofos;


public class Semaforo {
    protected int contador;

    
    public Semaforo () {
        this.contador = 0;
    }

    
    public Semaforo (int valor) {
        this.contador = valor;
    }

    
    public synchronized void decrementar () {
        while (this.contador == 0) {
            try {
                wait();
            }
            catch (InterruptedException ex) {
            }
        }
        this.contador--;
    }

    
    public synchronized void incrementar () {
        this.contador++;
        notify();
    }

}