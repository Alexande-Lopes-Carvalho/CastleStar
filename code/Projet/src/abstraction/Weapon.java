package abstraction;

import java.util.List;

import controle.CtrlEntity;

public class Weapon extends Equipment {
	private int damage;
	private int actionTime;
	//private Ellipse range;
	public Weapon(int _damage, double _range, int _actionTime) {
		damage = _damage;
		actionTime = _actionTime;
		// create Ellipse with range
	}
	
	public int getActionTime() {
		return actionTime;
	}
	
	@Override
	public void use(Warrior warrior, List<? extends CtrlEntity> enemy) {
		//range.setCoord(warrior.getCenterHitbox())
		//for(CtrlEntity k : enemy) {
			//if(range.pointInside(k.getCenterHitbox()) && ... (lookingTo)) {
				//k.damage(damage);
			//}
		//}
	}
}
