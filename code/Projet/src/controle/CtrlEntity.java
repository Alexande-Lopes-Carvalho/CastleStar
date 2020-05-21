package controle;

import abstraction.Entity;
import presentation.PresElementScene;
import shapeSceneFX.Point;

public class CtrlEntity extends CtrlElementCollidable {
	private Entity entity;
	public CtrlEntity(Entity _entity, PresElementScene _elementScene) {
		super(_entity, _elementScene);
		entity = _entity;
	}
	
	public void kill() {
		currentLevel.remove(this);
	}
	
	public void damage(int damage) {
		entity.damage(damage);
		if(entity.isDead()) {
			kill();
		}
	}
	
	public void heal(int heal) {
		entity.heal(heal);
	}
	
	public void move(Point deplacement) {
		entity.move(deplacement, currentLevel.getCtrlElementCollidableList());
	}
}
