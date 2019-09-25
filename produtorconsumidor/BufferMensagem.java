package produtorconsumidor;

import java.util.ArrayList;

/**
 *
 * @author Rafael D'Addio
 */
public class BufferMensagem {
    
    private ArrayList<String> mensagens;
    private final int tamanho;

    public BufferMensagem(int tamanho) {
        mensagens = new ArrayList<>();
        this.tamanho = tamanho;
    }
    
    public boolean isFull(){
        return (mensagens.size() == tamanho);
    }
    
    public boolean isEmpty(){
        return mensagens.isEmpty();
    }
    
    public void adicionaMensagem(String msg){
        if(isFull()){
            return;
        }       
        mensagens.add(msg);   
    }
    
    public String removeMensagem(){
        if(isEmpty()){
            return "";
        }        
        String msg = mensagens.remove(0);
        return msg;
    }
    
}
