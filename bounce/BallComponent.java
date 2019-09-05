package bounce;

import java.awt.*;
import java.util.*;
import javax.swing.*;

/**
 * O componente que desenha as bolas
 *
 * @author Rafael D'Addio (adaptado de Cay Horstmann - Core Java Vol 1., 9a edição)
 */
public class BallComponent extends JPanel {
    
        /* Dimensões do painel */
	private static final int DEFAULT_WIDTH = 450;
	private static final int DEFAULT_HEIGHT = 350;

        /*lista contendo as bolas */
	private ArrayList<Ball> balls = new ArrayList<>();

	/**
	 * Adiciona uma bola ao componente
	 * 
	 * @param b a bola a ser adicionada
	 */
	public void add(Ball b) {
		balls.add(b);
	}

        /**
         * Pinta o componente na tela
         * 
         * @param g 
         */
        @Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g); // apaga o fundo
		Graphics2D g2 = (Graphics2D) g;
		for (Ball b : balls) { //para cada bola, recupera sua forma e posição e as desenha
			g2.fill(b.getShape());
		}
	}

        /**
         * Recupera o tamanho de preferência, definido por DEFAULT_WIDTH e DEFAULT_HEIGHT
         * 
         * @return um objeto Dimension com o tamanho preferido
         */
        @Override
	public Dimension getPreferredSize() {
		return new Dimension(DEFAULT_WIDTH, DEFAULT_HEIGHT);
	}
}