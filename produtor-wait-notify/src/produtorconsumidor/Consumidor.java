package produtorconsumidor;

import java.util.concurrent.BlockingQueue;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * O consumidor. Implementa Runnable.
 * 
 * @author Rafael D'Addio
 */
public class Consumidor implements Runnable {

    BlockingQueue<String> bm; //buffer onde o consumidor irá remover mensagens

    public Consumidor(BlockingQueue<String> bm) {
        this.bm = bm;
    }

    @Override
    public void run() {
        for (int i = 1; i <= 10; i++) {

            try {
                System.out.println("Consumiu mensagem " + bm.take());
            } catch (InterruptedException ex) {
                ex.printStackTrace();
            }

        }
    }

}
