package bounce;

import java.awt.EventQueue;
import javax.swing.JFrame;

/**
 * Exibe uma animação de bolas quicando
 * 
 * @author Rafael D'Addio (adaptado de Cay Horstmann - Core Java Vol 1., 9a edição)
 */

public class Bounce {

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() { //coloca a criação e atualização da janela na thread do Swing
			public void run() {
				JFrame frame = new BounceFrame();
				frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				frame.setVisible(true);
			}
		});
	}

}

