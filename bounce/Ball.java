package bounce;

import java.awt.geom.*;

/**
 * Uma bola que se move e quica quando atinge as bordas do retângulo
 * 
 * @author Rafael D'Addio (adaptado de Cay Horstmann - Core Java Vol 1., 9a edição)
 */
public class Ball {
        /* tamanho da bola nas coordenadas x e y*/
	private static final int XSIZE = 15; 
	private static final int YSIZE = 15; 
        
        /* posição da bola nas coordenadas x e y*/
	private double x = 0; 
	private double y = 0;
        
        /* direção do movimento nas coordenadas x e y*/
	private double dx = 1;
	private double dy = 1;

	/**
	 * Move a bola pra próxima posição, mudando sua direção se ela 
	 * bate em uma das bordas
         * @param bounds os limites do retângulo
	 */
	public void move(Rectangle2D bounds) {
		x += dx;
		y += dy;
		if (x < bounds.getMinX()) {
			x = bounds.getMinX();
			dx = -dx;
		}
		if (x + XSIZE >= bounds.getMaxX()) {
			x = bounds.getMaxX() - XSIZE;
			dx = -dx;
		}
		if (y < bounds.getMinY()) {
			y = bounds.getMinY();
			dy = -dy;
		}
		if (y + YSIZE >= bounds.getMaxY()) {
			y = bounds.getMaxY() - YSIZE;
			dy = -dy;
		}
	}

	/**
	 * Obtém a forma da bola na sua posição atual
         * @return o formato da bola
	 */
	public Ellipse2D getShape() {
		return new Ellipse2D.Double(x, y, XSIZE, YSIZE);
	}
}