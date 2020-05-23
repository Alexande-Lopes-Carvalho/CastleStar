package abstraction;

import java.util.List;

import controle.CtrlWarrior;

public class Weapon extends Equipment {
	private int damage;
	//private Ellipse range;
	public Weapon(int _damage, double _range) {
		damage = _damage;
		// create Ellipse with range
	}
	
	@Override
	void use(Warrior warrior, List<CtrlWarrior> enemy) {
		//range.setCoord(warrior.getCenterHitbox())
		for(CtrlWarrior k : enemy) {
			//if(range.pointInside(k.getCenterHitbox()) && ... (lookingTo)) {
				k.damage(damage);
			//}
		}
	}
}
