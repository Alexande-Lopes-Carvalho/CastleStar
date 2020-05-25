package abstraction;


import java.util.List;

import controle.CtrlEntity;
import shapeSceneFX.Point;

public class Weapon extends Equipment {
	private int damage;
	private int actionTime;
	private Ellipse range;
	public Weapon(int _damage, double _range, int _actionTime) {
		damage = _damage;
		actionTime = _actionTime;
		range = new Ellipse(new Point(0, 0), _range);
	}
	
	public int getActionTime() {
		return actionTime;
	}
	
	@Override
	public void use(Warrior warrior, List<? extends CtrlEntity> enemy) {
		range.setCoord(warrior.getCenterHitbox());
		//System.out.println(warrior.getCoord() + " " + warrior.getRectangle());
		//System.out.println(range);
		Rectangle r = warrior.getRectangle();
		Point c;
		if(warrior.getFacingLeft()) {
			c = warrior.getCoord().copy().add(r.getCoord().getX()+r.getDimension().getX(),0);
			for(CtrlEntity k : enemy) {
				Point a = k.getEntity().getCenterHitbox();
				if(c.getX() >= a.getX() && range.pointInside(a)) {
					k.damage(damage);
					//System.out.println("DAMAGE");
				}
			}
		} else {
			c = warrior.getCoord().copy().add(r.getCoord().getX(), 0);
			for(CtrlEntity k : enemy) {
				Point a = k.getEntity().getCenterHitbox();
				if(c.getX() <= a.getX() && range.pointInside(a)) {
					k.damage(damage);
					//System.out.println("DAMAGE");
				}
			}
		}
		//System.out.println();
	}
}
