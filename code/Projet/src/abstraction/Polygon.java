package abstraction;

import shapeSceneFX.Point;

/**
 * Represente un polygon
 * 
 * @author Administrator
 *
 */
public abstract class Polygon {
	/**
	 * Coordonné du polygon
	 */
	private Point coord;
	public Polygon(Point _coord) {
		coord = _coord;
	}
	public Polygon() {
		coord = new Point(0, 0);
	}
	/**
	 * Verifie si le point est a l'interieur du polygon
	 * @param point
	 * @return
	 */
	abstract public boolean pointInside(Point point);
	/*
	 * utilisé pour calculé automatiquement le RenderPriorityAdd d'un ElementCollidable
	 * 
	 */
	/**
	 * retourne le point ayant la plus grande valeur en Y (utilisé pour calculé automatiquement le RenderPriorityAdd d'un ElementCollidable)
	 * @return
	 */
	abstract public double getHighestY();
	/*
	 * utilisé pour les operation "coup d'épée" ect ...
	 */
	/**
	 * Retourne le centre du polygon
	 * @return
	 */
	abstract public Point getCenter();
	/**
	 * retourne tous les point decrivant le polygon
	 * @return
	 */
	abstract public Point[] getPoints();
	
	public void setCoord(Point _coord) {
		coord.set(_coord);
	}
	
	public Point getCoord() {
		return coord;
	}
}
