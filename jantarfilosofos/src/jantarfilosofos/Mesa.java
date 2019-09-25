package jantarfilosofos;


public class Mesa {
    public static Semaforo mutex = new Semaforo(1);
    public static Semaforo semaforos[] = new Semaforo[5];
    public static int estado[] = new int[5];
    static Filosofo filosofo[] = new Filosofo[5];
    
    public void init () {
        for (int i = 0; i < estado.length; i++) {
            estado[i] = 0;
        }
        
        for(int i = 0; i < 5; i++) {
        	filosofo[i] = new Filosofo(i);
        	semaforos[i] = new Semaforo(i);
        	filosofo[i].start();
        	
        }
    }

}