package abstraction;

import shapeSceneFX.Point;


/**
 * 
 * coord -----------------------
 *   |                         |   / \
 *   |                         |    |  dimension.getY()
 *   |                         |   \ /
 *   ---------------------------
 *       
 *   <------------------------->
 *         dimension.getX()
 *   
 * @author Administrator
 *
 */
public class Rectangle extends Polygon {
	private Point dimension;
	public Rectangle(Point _coord, Point _dimension){
		super(_coord);
		dimension = _dimension;
		if(dimension.getX() < 0) {
			getCoord().add(dimension.getX(), 0);
			dimension.add(-2*dimension.getX(), 0);
		}
		if(dimension.getY() < 0) {
			getCoord().add(0, dimension.getY());
			dimension.add(0, -2*dimension.getY());
		}
	}
	
	public Rectangle(Point _dimension) {
		dimension = _dimension;
		if(dimension.getX() < 0) {
			getCoord().add(dimension.getX(), 0);
			dimension.add(-2*dimension.getX(), 0);
		}
		if(dimension.getY() < 0) {
			getCoord().add(0, dimension.getY());
			dimension.add(0, -2*dimension.getY());
		}
	}
	
	@Override
	public boolean pointInside(Point point) {
		return point.getX() >= getCoord().getX() && point.getX() <= getCoord().getX()+dimension.getX() && point.getY() >= getCoord().getY() && point.getY() <= getCoord().getY()+dimension.getY();
	}
	
	public double getHighestY() {
		return getCoord().getY()+dimension.getY();
	}
	
	public Point getCenter() {
		return getCoord().copy().add(dimension.copy().mult(0.5));
	}
	
	public Point getDimension() {
		return dimension;
	}
	
	public void setDimension(Point _dimension) {
		dimension.set(_dimension);
	}
	
	public String toString() {
		return "Rectangle coord left top " + getCoord() + " dimension : " + dimension;
	}
}
