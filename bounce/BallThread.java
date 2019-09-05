package bounce;

/**
 *
 * @author Rafael D'Addio
 */
public class BallThread extends Thread {

    private Ball b;
    private BallComponent comp;
    public static final int STEPS = 1000;
    public static final int DELAY = 3;

    public BallThread(Ball b, BallComponent comp) {
        super();
        this.b = b;
        this.comp = comp;
    }

    @Override
    public void run() {
        //FAÇA A BOLA SE MOVER
        try {
            for (int i = 1; i <= STEPS; i++) {
                b.move(comp.getBounds());
                comp.paint(comp.getGraphics());
                Thread.sleep(DELAY);
            }
        } catch (InterruptedException e) {
            System.out.println("Problemas ao fazer a thread dormir.");
        }
    }

}
