package banconaosincronizado;

import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * A tarefa de transferência. 
 * 
 * Implementa Runnable.
 * 
 * @author rmart
 */
public class TransfereRunnable implements Runnable {

    private Banco banco;
    private int deConta, paraConta;
    private double quantia;
    private final int ATRASO = 10;

    /**
     * O Construtor.
     * 
     * @param banco banco onde será feita a transferência
     * @param deConta conta de saída
     * @param paraConta conta de entrada
     * @param quantia quantidade a ser transferida
     */
    public TransfereRunnable(Banco banco, int deConta, int paraConta, double quantia) {
        this.banco = banco;
        this.deConta = deConta;
        this.paraConta = paraConta;
        this.quantia = quantia;

    }

    @Override
    public void run() {
        try {
            //realiza a transferência
            banco.transfere(deConta, paraConta, quantia);
            //dorme por um tempo aleatório
            Thread.sleep((int) (ATRASO * Math.random()));
        } catch (InterruptedException ex) {
        }
    }

}
