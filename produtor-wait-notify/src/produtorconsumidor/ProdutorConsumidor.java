package produtorconsumidor;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;

/**
 *
 * @author Rafael D'Addio
 */
public class ProdutorConsumidor {

    public static void main(String[] args) {
        //cria um buffer de tamanho 10
        BlockingQueue<String> bm = new ArrayBlockingQueue<String>(10);
        
        //produtor injeta mensagens no buffer
        Thread t1 = new Thread(new Produtor(bm));
        //consumidor retira mensagens no buffer
        Thread t2 = new Thread(new Consumidor(bm));
        
        t1.start();
        t2.start();
        
        

       
    }
}
