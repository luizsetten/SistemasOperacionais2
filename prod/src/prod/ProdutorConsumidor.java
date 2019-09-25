package prod;

/**
 *
 * @author Rafael D'Addio
 */
public class ProdutorConsumidor {

    public static void main(String[] args) {
        BufferMensagem bm = new BufferMensagem(10);

        for (int i = 0; i < 10; i++) {
            String msg = "Olá, mensagem numero " + i;
            System.out.println("Produziu mensagem " + msg);
            bm.adicionaMensagem(msg);
        }

        for (int i = 0; i < 10; i++) {
            String msg = bm.removeMensagem();
            System.out.println("Consumiu mensagem " + msg);
        }
    }
}
