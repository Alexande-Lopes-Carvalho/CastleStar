package abstraction;

import java.util.List;

import controle.CtrlElementCollidable;
import shapeSceneFX.Point;

public class Entity extends ElementCollidable {
	public static final Object DAMAGE = 2;
	public static final Object HEAL = 3;
	public static final Object MOVE = 4;
	public static final Object COLLIDE = -2;
	private int life, maxLife;
	private double speed;
	private Rectangle rectangle;

	public Entity(double _speed, int _maxLife, Point _coord, Rectangle _rectangle) {
		super(_coord, _rectangle);
		rectangle = _rectangle;
		speed = _speed;
		maxLife = _maxLife;
		life = maxLife;
		if (maxLife <= 0) {
			throw new IllegalArgumentException("Entity can't have a maxLife <= 0");
		}
	}

	public void damage(int damage) {
		life = Math.max(life - damage, 0);
		setChanged();
		notifyObservers(DAMAGE);
	}

	public void heal(int heal) {
		life = Math.min(life + heal, maxLife);
		setChanged();
		notifyObservers(HEAL);
	}

	public boolean isDead() {
		return life == 0;
	}

	public int getLife() {
		return life;
	}
	
	public int getMaxLife() {
		return maxLife;
	}

	public double getSpeed() {
		return speed;
	}

	public Rectangle getRectangle() {
		return rectangle;
	}

	private boolean canMove(Point deplacement, List<CtrlElementCollidable> list) {
		Point[] c = new Point[4];
		c[0] = new Point(getCoord().copy().add(deplacement).add(getRectangle().getCoord()));
		c[1] = c[0].copy().add(getRectangle().getDimension().getX(), 0);
		c[2] = c[0].copy().add(getRectangle().getDimension().getX(), getRectangle().getDimension().getY());
		c[3] = c[0].copy().add(0, getRectangle().getDimension().getY());
		for (CtrlElementCollidable k : list) {
			if (k.getElementCollidable() != this) {
				for (Point p : c) {
					if (k.getElementCollidable().getPolygon()
							.pointInside(k.getElementCollidable().getCoord().getVector(p))) {
						return false;
					}
				}
			}
		}
		return true;
	}

	public void move(Point deplacement, List<CtrlElementCollidable> list) {
		if(canMove(deplacement, list)) {
			setCoord(getCoord().copy().add(deplacement));
			return;
		} else if(deplacement.getX() != 0 && deplacement.getY() != 0) { 
			if(canMove(deplacement.copy().set(deplacement.getX(), 0), list)) {
				setCoord(getCoord().copy().add(deplacement.copy().set(deplacement.getX(), 0)));
			} else if(canMove(deplacement.copy().set(0, deplacement.getY()), list)) {
				setCoord(getCoord().copy().add(deplacement.copy().set(0, deplacement.getY())));
			}
		}
		
		setChanged();
		notifyObservers(COLLIDE);
	}
}
