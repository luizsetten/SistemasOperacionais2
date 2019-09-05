package testecallable;

import java.util.ArrayList;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;

/**
 *
 * @author Rafael D'Addio
 */


/**
 * Classe que implementa a soma de um vetor de maneira assíncrona.
 * Implementa uma interface Callable que deverá retornar um Integer ao fim da
 *      execução de seu método call()
 * 
 */
class SomaVetorTarefa implements Callable<Integer>{

    int[] v;
    
    /**
     * Construtor
     * 
     * @param v o vetor que irá realizar a soma
     */
    public SomaVetorTarefa(int[] v){
        this.v = v;
    }   
    
    /**
     * Método que irá realizar uma tarefa de forma assíncrona.
     * 
     * @return a soma dos vetores
     * @throws Exception qualquer exceção lançada pelo método call() será recuperada pelo objeto Future a qual a tarefa está designada
     *          - Quando Future<>.get() é invocado, ele lança ExecutionException, a qual vem diretamente deste método  
     */
    @Override
    public Integer call() throws Exception {
        return soma();
    }
    
    /**
     * Soma os elementos de um vetor.
     * 
     * @return a soma do vetor
     */
    private Integer soma(){
        int soma = 0;
        
        for(int i = 0; i < v.length; i++){
            soma += v[i];
        }
        
        return soma;
    }
    
}

public class TesteCallable {

    public static void main(String[] args) throws InterruptedException, ExecutionException {
        
        int[] v = {1,2,3,4,5,6,7,8};
        
        /*Executors.newCachedThreadPool() produz uma Thread Pool que cresce 
        *   a medida que todas suas threads já estão ocupadas.
        *       - Se há thread disponível, aloca tarefa a ela
        *       - Senão, cria uma nova thread e aloca a tarefa
        */
        ExecutorService e = Executors.newCachedThreadPool();
        
        
        //criando uma tarefa assíncrona que soma os elementos do vetor v
        SomaVetorTarefa s = new SomaVetorTarefa(v);
        /* Criando um objeto Future que irá guardar o resultado futuro da thread
             - Através do objeto Future podemos realizar algumas manipulações na sua thread
             - Por exemplo, f.cancel();
             - A tarefa é submetida a um ThreadPool através do método submit, o qual retorna um objeto Future
             - O Objeto Future deve ser do mesmo tipo de retorno da tarefa (nesse caso, Integer)
        */
        Future<Integer> f = e.submit(s);
        
        // Checamos se a tarefa já finalizou com o método .isDone()
        // O loop aqui garante que a CPU não fique ocupada enquanto aguarda o fim da execução da tarefa
        // Enquanto a tarefa não está pronta, libere a CPU para outra Thread através do método Thread.yield();
        while(!f.isDone()){
            System.out.println("Executando...");
            Thread.yield();
        }
        
        /* 
            - O método .get() devolve o valor retornado pela tarefa. 
            - Caso a tarefa não tenha terminado, a thread que chamou .get() fica aguardando 
                (e consequentemente deixando a CPU ociosa em todo seu tempo de execução) 
                até a tarefa terminar e o resultado ficar pronto. 
        */
        int resultado = f.get();
        
        System.out.println("Resultado eh: " + resultado);
        

        //Um método seguro para se executar múltiplas tarefas Callable é manter uma lista de Futures<>
        ArrayList<Future<Integer>> futures = new ArrayList<>();
        
        int[] v2 = {1,2,4,6,1,4,7,2};
        int[] v3 = {2,3,5,7,8,94,2,5};
        
        //Aqui submetemos tarefas novas anônimas para o ThreadPool e já guardamos objetos Future<> anônimos na lista
        futures.add(e.submit(new SomaVetorTarefa(v2)));
        futures.add(e.submit(new SomaVetorTarefa(v3)));
        
        int res = 0;
        
        // Para recuperar os resultados das tarefas, iteramos na lista
        for(Future<Integer> fl : futures){
            //Para cada future, enquanto a tarefa não está pronta, 
            //   liberamos o controle da CPU para outras threads
            while(!fl.isDone()){
                Thread.yield();
            }
            
            //recuperamos o valor de cada tarefa
            int soma = fl.get();
            System.out.println("A soma do vetor eh"+ soma);
            //e somamos no resultado final
            res +=soma;
        }
        System.out.println("O resultado final eh:"+ res);
            
        //fechamento do Thread Pool
        e.shutdown();
        e.awaitTermination(10, TimeUnit.SECONDS);
        
    }
    
}
