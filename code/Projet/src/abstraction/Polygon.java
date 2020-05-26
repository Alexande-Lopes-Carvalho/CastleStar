package abstraction;

public abstract class Polygon {
	private Point coord;
	public Polygon(Point coord2) {
		coord = coord2;
	}
	public Polygon() {
		coord = new Point(0, 0);
	}
	abstract public boolean pointInside(Point point);
	/*
	 * utilis� pour calcul� automatiquement le RenderPriorityAdd d'un ElementCollidable
	 * 
	 */
	abstract public double getHighestY();
	/*
	 * utilis� pour les operation "coup d'�p�e" ect ...
	 */
	abstract public Point getCenter();
	public void setCoord(Point _coord) {
		coord.set(_coord);
	}
	
	public Point getCoord() {
		return coord;
	}
}
