import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

class TarefaImpressao implements Runnable{
    char c;

    public TarefaImpressao(char c){
        this.c = c;
    }
    private void imprime() {
        for(int i=0; i<100; i++){
            System.out.print(c);
        }
    }

    @Override
    public void run(){
        imprime();
    }
}

public class Teste {

    public static void main(String[] args) {

        ExecutorService e = Executors.newFixedThreadPool(10);
    
        char c ='a';
        e.submit(new  TarefaImpressao(c));
        for(int i = 0; i<25; i++){
            c++;
            e.submit(new TarefaImpressao(c));
        }
        e.shutdown();
        
        try{
        e.awaitTermination(1, TimeUnit.MINUTES);
        } catch (InterruptedException ex){
            System.out.println("Erro para fechar thread spool");
        }
    }

}
