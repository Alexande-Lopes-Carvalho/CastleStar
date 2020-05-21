package abstraction;

import shapeSceneFX.Point;

public abstract class Polygon {
	private Point coord;
	public Polygon(Point _coord) {
		coord = _coord;
	}
	public Polygon() {
		coord = new Point(0, 0);
	}
	abstract boolean pointInside(Point point);
	/*
	 * utilis� pour calcul� automatiquement le RenderPriorityAdd d'un ElementCollidable
	 * 
	 */
	abstract double getHighestY();
	/*
	 * utilis� pour les operation "coup d'�p�e" ect ...
	 */
	abstract Point getCenter();
	public void setCoord(Point _coord) {
		coord.set(_coord);
	}
	
	public Point getCoord() {
		return coord;
	}
}
