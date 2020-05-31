package abstraction;

import shapeSceneFX.Point;
/**
 * Un combattant
 * @author Administrator
 *
 */
public class Warrior extends Entity {
	public static final Object LOOKINGTO_UPDATE = 5;
	public static final Object NBARROW_UPDATE = 6;
	public static final Object WALKING_UPDATE = 7;
	/**
	 * indique dans quelle sens le personnage est orienté (gauche droite)
	 */
	private boolean facingLeft;
	/**
	 * indique ou le combattant regarde
	 */
	private Point lookingTo;
	/**
	 * nombre de fleche dont le combattant dispose
	 */
	private int nbArrow;
	/**
	 * indique si le combattant est en train de marcher
	 */
	private boolean walking;
	public Warrior(int _nbArrow, Point _lookingTo, double _speed,int _maxLife, Point _coord, Rectangle _rectangle) {
		super(_speed,_maxLife, _coord, _rectangle);
		lookingTo = new Point(0, 0);
		setLookingTo(_lookingTo);
		setNbArrow(_nbArrow);
	}
	
	public void setLookingTo(Point a) {
		if(Math.abs(a.getX()) >= 5) {
			lookingTo.set(a);
			facingLeft = lookingTo.getX() < 0;
			setChanged();
			notifyObservers(LOOKINGTO_UPDATE);
		}
	}
	
	public Point getLookingTo() {
		return lookingTo;
	}
	
	public boolean getFacingLeft() {
		return facingLeft;
	}
	
	public void setNbArrow(int _nbArrow) {
		nbArrow = Math.max(_nbArrow, 0);
		setChanged();
		notifyObservers(NBARROW_UPDATE);
	}
	
	public int getNbArrow() {
		return nbArrow;
	}
	
	public void setWalking(boolean value) {
		if(value != walking) {
			walking = value;
			setChanged();
			notifyObservers(WALKING_UPDATE);
		}
	}
	
	public boolean getWalking() {
		return walking;
	}
}
