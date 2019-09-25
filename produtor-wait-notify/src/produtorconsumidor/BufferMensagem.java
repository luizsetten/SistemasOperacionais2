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
     * @param tamanho o tamanho m�ximo do buffer
     */
    public BufferMensagem(int tamanho) {
        mensagens = new ArrayList<>();
        this.tamanho = tamanho;
    }
    
    /**
     * Checa se est� cheio.
     * 
     * @return true = cheio; false = caso contr�rio
     */
    public boolean isFull(){
        return (mensagens.size() == tamanho);
    }
    
    /**
     * Checa se est� vazio.
     * 
     * @return true = vazio; false = caso contr�rio
     */
    public boolean isEmpty(){
        return mensagens.isEmpty(); //m�todo isEmpty() do ArrayList
    }
    
    /**
     * Adiciona uma mensagem ao buffer. 
     * M�todo sincronizado. As threads poder�o acessar este m�todo ou o de remo��o, um por vez.
     * 
     * @param msg a mensagem a ser adicionada.
     */
    public synchronized void adicionaMensagem(String msg){
        //espera enquanto est� cheio
        while(isFull()){
            try {
                this.wait();
            } catch (InterruptedException ex) {
               ex.printStackTrace();
            }
        }
        
        mensagens.add(msg);
        //notifica ap�s adicionar mensagem. Se o buffer estava vazio, a thread de consumidor ser� notificada.
        this.notify();
    }
    
    /**
     * Remove e retorna uma mensagem do buffer.
     * M�todo sincronizado. As threads poder�o acessar este m�todo ou o de adi��o, um por vez.
     * 
     * @return a mensagem removida
     */
    public synchronized String removeMensagem(){
        //espera enquanto est� vazio
        while(isEmpty()){
            try {
                this.wait();
            } catch (InterruptedException ex) {
                ex.printStackTrace();
            }
        }        
        String msg = mensagens.remove(0);
        //notifica ap�s remover uma mensagem. Se o buffer estava cheio, a thread de consumidor ser� notificada.
        this.notify();
        return msg;
    }
    
}
