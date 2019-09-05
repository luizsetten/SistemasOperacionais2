package banconaosincronizado;

/**
 * Um banco cujas contas estarão realizando transferências.
 *
 * @author Rafael D'Addio (adaptado de Cay Horstmann - Core Java Vol 1., 9a
 * edição)
 */
public class Banco {

    private double[] contas;

    /**
     * Construtor.
     *
     * @param n número de contas
     * @param balanco saldo inicial das contas
     */
    public Banco(int n, double balanco) {
        contas = new double[n];
        for (int i = 0; i < contas.length; i++) {
            contas[i] = balanco;
        }
    }

    /**
     * Realiza uma transferência entre contas.
     *
     * Método sincronizado! a palavra-chave synchronized garante uma TRAVA
     * (lock) no objeto que chama essa função para a Thread que o evocou.
     * Nenhuma outra Thread poderá realizar operações enquanto o objeto estiver
     * travado (locked). As demais Threads que evocarem esse método através do
     * MESMO objeto de Banco irão ser enfileiradas enquanto aguardam a trava.
     *
     * @param de a conta de saída
     * @param para a conta de entrada
     * @param quantia a quantia transferida
     * @throws InterruptedException
     */
    public synchronized void transfere(int de, int para, double quantia) throws InterruptedException {

        //enquanto a conta de saída não tiver dinheiro suficiente para transferir,
        //irá esperar e ceder CONDICIONALMENTE a trava para outra Thread realizar
        //a operação
        while (contas[de] < quantia) {
            System.out.println("Conta " + de + " com saldo insuficiente. Esperando...");
            wait();

        }

        //realiza a transferência
        System.out.print(Thread.currentThread()); //exibe a Thread corrente que está realizando a transferência
        contas[de] -= quantia;
        System.out.printf(" %10.2f de %d para %d ", quantia, de, para);
        contas[para] += quantia;
        System.out.printf("Balanço total: %10.2f\n", getBalancoTotal());

        //após transferir, a Thread notifica TODAS as Threads que estão atualmente
        //esperando (e cedendo condicionalmente a trava)
        notifyAll();

    }

    /**
     * Calcula o balanço total
     * @return soma de todas as contas
     */
    public double getBalancoTotal() {
        double total = 0;
        for (int i = 0; i < contas.length; i++) {
            total += contas[i];
        }
        return total;
    }

    /**
     * Retorna o numero de contas
     * @return numero de contas
     */
    public int tamanho() {
        return contas.length;
    }

}
