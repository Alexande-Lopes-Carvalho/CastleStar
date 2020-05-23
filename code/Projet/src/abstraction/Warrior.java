package abstraction;

import shapeSceneFX.Point;

public class Warrior extends Entity {
	public static final Object SHIELD_PROTECT_UPDATE = 5;
	public static final Object LOOKINGTO_UPDATE = 6;
	public static final Object NBARROW_UPDATE = 7;
	public static final Object WALKING_UPDATE = 8;
	private boolean facingLeft;
	private boolean shieldProtect;
	private Point lookingTo;
	private int nbArrow;
	private boolean walking;
	public Warrior(int _nbArrow, Point _lookingTo,int _maxLife, Point _coord, Polygon _polygon) {
		super(_maxLife, _coord, _polygon);
		lookingTo = _lookingTo;
		setNbArrow(_nbArrow);
	}
	
	public void setShieldProtect(boolean value) {
		shieldProtect = value;
		setChanged();
		notifyObservers(SHIELD_PROTECT_UPDATE);
	}
	
	public boolean getShieldProtect() {
		return shieldProtect;
	}
	
	public void setLookingTo(Point a) {
		if(a.getDist() >= 5) {
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
