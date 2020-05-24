package abstraction;

import java.util.List;

import controle.CtrlElementCollidable;
import shapeSceneFX.Point;

public class Entity extends ElementCollidable {
	public static final Object DAMAGE = 2;
	public static final Object HEAL = 3;
	public static final Object MOVE = 4;
	private int life, maxLife;
	private double speed;
	public Entity(double _speed, int _maxLife, Point _coord, Polygon _polygon) {
		super(_coord, _polygon);
		speed = _speed;
		maxLife = _maxLife;
		life = maxLife;
		if(maxLife <= 0) {
			throw new IllegalArgumentException("Entity can't have a maxLife <= 0");
		}
	}
	
	public void damage(int damage) {
		life = Math.max(life-damage, 0);
		setChanged();
		notifyObservers(DAMAGE);
	}
	
	public void heal(int heal) {
		life = Math.min(life+heal, maxLife);
		setChanged();
		notifyObservers(HEAL);
	}
	
	public boolean isDead() {
		return life == 0;
	}
	
	public int getLife() {
		return life;
	}
	
	public double getSpeed() {
		return speed;
	}
	
	public void move(Point deplacement, List<CtrlElementCollidable> list) {
		// TO DO ...
		// ...
		
		// ...
		//setCoord(/* ... */);
	}
}
