package banconaosincronizado;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 *
 * @author Rafael D'Addio (adaptado de Cay Horstmann - Core Java Vol 1., 9a edição)
 */
public class BancoNaoSincronizado {

    //constantes
    public static final int NCONTAS = 100;
    public static final double BALANCO_INICIAL = 1000;
    
    public static void main(String[] args) {
        
        Banco b = new Banco(NCONTAS, BALANCO_INICIAL);
        ExecutorService es = Executors.newFixedThreadPool(10);
        
        //loop eterno
        
        while(true){
            //gera aleatoriamente uma transferência de certa quantia entre uma conta e outra
            int de = (int) (b.tamanho() * Math.random());
            int para = (int) (b.tamanho() * Math.random());
            while(de == para){
               para = (int) (b.tamanho() * Math.random()); 
            }
            //.execute realiza a mesma operação que .submit, porém não possui retorno
            es.execute(new TransfereRunnable(b, de, para, BALANCO_INICIAL * Math.random()));
        }
    }
    
}
