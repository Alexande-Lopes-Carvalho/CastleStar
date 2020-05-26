package abstraction;




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
	}
	
	public Rectangle(Point _dimension) {
		dimension = _dimension;
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
