package presentation;

import shapeSceneFX.Point;
import shapeSceneFX.EventHandling.EventHandler;
/**
 * Represente un element du niveau a afficher
 * @author Administrator
 *
 */
public abstract class PresElementScene extends EventHandler implements Comparable<PresElementScene> {
	/**
	 * coordonnée de l'element a afficher
	 */
	private Point coord;
	/**
	 * priorité d'affichge de l'evenement (algorithme du peintre)
	 */
	private double renderPriority;
	public PresElementScene() {
		coord = new Point(0, 0);
	}

	public Point getCoord() {
		return coord;
	}
	
	public void setCoord(Point _coord) {
		// pour eviter les image flou on arrondie les coordonné
		Point newCoord = _coord.copy().mult(MainEventHandler.pxSize);
		newCoord.set(Math.round(newCoord.getX()), Math.round(newCoord.getY()));
		coord.set(newCoord);
	}
	
	public void setRenderPriority(double _renderPriority) {
		renderPriority = _renderPriority;
	}
	/**
	 * méthode qui permet de savoir s'il l'element sera present sur le canavs si l'on appelle sa methode render
	 * @param camera
	 * @return
	 */
	public abstract boolean doRender(Point camera);

	@Override
	public int compareTo(PresElementScene arg0) {
		return ((renderPriority-arg0.renderPriority < 0)? -1 : (renderPriority-arg0.renderPriority > 0)? 1 : 0);
	}
	
	public double getRenderPrio() {
		return renderPriority;
	}
	
	public static boolean between(double value, double inf, double sup) {
		return value >= inf && value <= sup;
	}
}
