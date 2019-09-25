package produtorconsumidor;

import java.util.concurrent.BlockingQueue;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * O produtor. Implementa runnable.
 *
 * @author Rafael D'Addio
 */
public class Produtor implements Runnable {

    BlockingQueue<String> bm; //buffer onde o produtor irá adicionar mensagens

    public Produtor(BlockingQueue<String> bm) {
        this.bm = bm;
    }

    @Override
    public void run() {
        for (int i = 1; i <= 10; i++) {
            //produz mensagens e as adiciona ao buffer.

            String msg = "Olá, mensagem numero " + i;
            try {
                bm.put(msg);
                System.out.println("Produziu mensagem " + msg);
            } catch (InterruptedException ex) {
                ex.printStackTrace();
            }
            
        }
    }

}
