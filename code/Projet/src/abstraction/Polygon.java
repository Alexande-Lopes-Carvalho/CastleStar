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
	abstract public boolean pointInside(Point point);
	/*
	 * utilisé pour calculé automatiquement le RenderPriorityAdd d'un ElementCollidable
	 * 
	 */
	abstract public double getHighestY();
	/*
	 * utilisé pour les operation "coup d'épée" ect ...
	 */
	abstract public Point getCenter();
	public void setCoord(Point _coord) {
		coord.set(_coord);
	}
	
	public Point getCoord() {
		return coord;
	}
}
