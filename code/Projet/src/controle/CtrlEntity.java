package controle;

import java.util.Observable;

import abstraction.Entity;
import presentation.PresElementScene;
import shapeSceneFX.Point;

public class CtrlEntity extends CtrlElementCollidable {
	private Entity entity;
	public CtrlEntity(Entity _entity, PresElementScene _elementScene) {
		super(_entity, _elementScene);
		entity = _entity;
	}
	
	public void update(Observable o, Object arg) {
		super.update(o, arg);
		if(arg.equals(Entity.MOVE)) {
			getPresElementScene().setCoord(entity.getCoord());
		} else if(arg.equals(Entity.DAMAGE)) {
			if(entity.isDead()) {
				kill();
			}
		} else if(arg.equals(Entity.HEAL)) {
			
		}
	}
	
	public Entity getEntity() {
		return entity;
	}
	
	public void kill() {
		currentLevel.remove(this);
	}
	
	public void damage(int damage) {
		entity.damage(damage);
	}
	
	public void heal(int heal) {
		entity.heal(heal);
	}
	
	public void move(Point deplacement) {
		entity.move(deplacement, currentLevel.getCtrlElementCollidableList());
	}
}
