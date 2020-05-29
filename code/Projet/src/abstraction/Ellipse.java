package abstraction;

import shapeSceneFX.Point;

/**
 * La coordonnée represente le centre de l'ellipse
 * 
 * @author Administrator
 *
 */

public class Ellipse extends Polygon {
	private static final Point xToYRatio = new Point(3, 2); // pronfondeur
	private Point dimension;
	public Ellipse(Point _coord, Point _dimension) {
		super(_coord);
		dimension = _dimension;
	}
	
	public Ellipse(double x, double y, double w, double h) {
		super(new Point(x, y));
		dimension = new Point(w, h);
	}
	
	/**
	 * Crée une ellipse selon la notion de profondeur dans notre jeu
	 * 
	 * @param _coord
	 * @param range porté en X
	 */
	public Ellipse(Point _coord, double range) {
		super(_coord);
		dimension = new Point(range*2, 2*range*(xToYRatio.getY()/xToYRatio.getX()));
	}
	
	@Override
	public boolean pointInside(Point point) {
		Point k = getCoord().copy().getVector(point);
		k.set(k.getX(), k.getY()*(dimension.getX()/dimension.getY()));
		return k.getDist() < dimension.getX()/2.;
	}

	@Override
	public double getHighestY() {
		return getCoord().getY()+dimension.getY()/2.;
	}

	@Override
	public Point getCenter() {
		return getCoord();
	}
	
	public Point getDimension() {
		return dimension;
	}
	
	public String toString() {
		return "Ellipse : Center at " + getCoord() + " dimension : " + dimension;
	}

	@Override
	public Point[] getPoints() {
		// TODO Auto-generated method stub
		return null;
	}
}

