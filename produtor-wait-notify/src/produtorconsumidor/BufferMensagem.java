package produtorconsumidor;

import java.util.ArrayList;

/**
 * Um buffer de mensagens.
 * 
 * @author Rafael D'Addio
 */
public class BufferMensagem {
    
    private ArrayList<String> mensagens;
    private final int tamanho;

    /**
     * Construtor.
     * 
     * @param tamanho o tamanho máximo do buffer
     */
    public BufferMensagem(int tamanho) {
        mensagens = new ArrayList<>();
        this.tamanho = tamanho;
    }
    
    /**
     * Checa se está cheio.
     * 
     * @return true = cheio; false = caso contrário
     */
    public boolean isFull(){
        return (mensagens.size() == tamanho);
    }
    
    /**
     * Checa se está vazio.
     * 
     * @return true = vazio; false = caso contrário
     */
    public boolean isEmpty(){
        return mensagens.isEmpty(); //método isEmpty() do ArrayList
    }
    
    /**
     * Adiciona uma mensagem ao buffer. 
     * Método sincronizado. As threads poderão acessar este método ou o de remoção, um por vez.
     * 
     * @param msg a mensagem a ser adicionada.
     */
    public synchronized void adicionaMensagem(String msg){
        //espera enquanto está cheio
        while(isFull()){
            try {
                this.wait();
            } catch (InterruptedException ex) {
               ex.printStackTrace();
            }
        }
        
        mensagens.add(msg);
        //notifica após adicionar mensagem. Se o buffer estava vazio, a thread de consumidor será notificada.
        this.notify();
    }
    
    /**
     * Remove e retorna uma mensagem do buffer.
     * Método sincronizado. As threads poderão acessar este método ou o de adição, um por vez.
     * 
     * @return a mensagem removida
     */
    public synchronized String removeMensagem(){
        //espera enquanto está vazio
        while(isEmpty()){
            try {
                this.wait();
            } catch (InterruptedException ex) {
                ex.printStackTrace();
            }
        }        
        String msg = mensagens.remove(0);
        //notifica após remover uma mensagem. Se o buffer estava cheio, a thread de consumidor será notificada.
        this.notify();
        return msg;
    }
    
}
