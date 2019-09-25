package jantarfilosofos;


public class Filosofo extends Thread{

    // PENSANDO = 0
    // FAMINTO  = 1
    // COMENDO  = 2
    private int fil;

    public Filosofo (int filosofo){
    	this.fil = filosofo;
    }

   
    public void ComFome(){
    	Mesa.estado[this.fil] = 1;
    	System.out.println("O Filósofo " + this.fil + " está com fome!");
    }

    public void Come(){
        Mesa.estado[this.fil] = 2;
        System.out.println("O Filósofo " + this.fil + " está comendo!");
        
        try{
            Thread.sleep(1000);//EXIGE O TRY-CATCH
        }
        catch (InterruptedException ex){
        }
    }

    public void Pensa(){
        Mesa.estado[this.fil] = 0;
        System.out.println("O Filósofo " + this.fil + " está pensando!");
        
        try {
            Thread.sleep(1000);
        }
        catch (InterruptedException ex) {
        }
    }

    public void LargarGarfo () {
        Mesa.mutex.decrementar();
        
        Pensa();
        Mesa.filosofo[VizinhoEsquerda()].TentarObterGarfos();
        Mesa.filosofo[VizinhoDireita()].TentarObterGarfos();

        Mesa.mutex.incrementar();
    }


    public void PegarGarfo () {
        Mesa.mutex.decrementar();

        ComFome();
        TentarObterGarfos();

        Mesa.mutex.incrementar();
        Mesa.semaforos[this.fil].decrementar();
    }

    
    public void TentarObterGarfos() {
        if (Mesa.estado[this.fil] == 1 && Mesa.estado[VizinhoEsquerda()] != 2 && Mesa.estado[VizinhoDireita()] != 2) {
        	Come();
            Mesa.semaforos[this.fil].incrementar();
        }
    }

    
    @Override
    public void run () {
        try {
            Pensa();
            while (true) {
                PegarGarfo();
                Thread.sleep(1000);
                LargarGarfo();
            }
        }
        catch (InterruptedException ex) {
            return;
        }
    }

    
    public int VizinhoDireita () {
        return (this.fil + 1) % 5;
    }

    
    public int VizinhoEsquerda () {
        if (this.fil == 0) {
            return 4;
        }
        else {
            return (this.fil - 1) % 5;
        }
    }

}