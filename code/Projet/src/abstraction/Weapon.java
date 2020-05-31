package abstraction;


import java.util.List;

import controle.CtrlEntity;
import shapeSceneFX.Point;
/**
 * Une arme qui n'envoie pas de projectile 
 * @author Administrator
 *
 */
public class Weapon extends Equipment {
	/**
	 * degat que l'arme produit
	 */
	private int damage;
	/**
	 * temps en milliseconde pour attaqué avec l'arme
	 */
	private int actionTime;
	/**
	 * hitbox de l'arme
	 */
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
				if(c.getX() >= a.getX() && range.pointInside(a.copy().add(k.getEntity().getRectangle().getDimension().getX()/2., 0))) {
					k.damage(damage);
					//System.out.println("DAMAGE");
				}
			}
		} else {
			c = warrior.getCoord().copy().add(r.getCoord().getX(), 0);
			for(CtrlEntity k : enemy) {
				Point a = k.getEntity().getCenterHitbox();
				if(c.getX() <= a.getX() && range.pointInside(a.copy().sub(k.getEntity().getRectangle().getDimension().getX()/2., 0))) {
					k.damage(damage);
					//System.out.println("DAMAGE");
				}
			}
		}
		//System.out.println();
	}
}
