package controle;

import java.util.Observable;

import abstraction.Entity;
import presentation.PresElementScene;
import shapeSceneFX.Point;
/**
 * Entité
 * @author Administrator
 * @see Entity
 * @see PresElementScene
 */
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
	/**
	 * supprime l'entité du niveau
	 */
	public void kill() {
		currentLevel.remove(this);
	}
	/**
	 * relaie l'information des degat a entity
	 * @param damage
	 * @see Entity#damage(int)
	 */
	public void damage(int damage) {
		entity.damage(damage);
	}
	/**
	 * relaie l'information du soin a entity
	 * @param heal
	 * @see Entity#heal(int)
	 */
	public void heal(int heal) {
		entity.heal(heal);
	}
	/**
	 * relaie l'information du deplacement a entity
	 * @param deplacement
	 * @see Entity#move(Point, java.util.List)
	 */
	public void move(Point deplacement) {
		entity.move(deplacement, currentLevel.getCtrlElementCollidableList());
	}
}
