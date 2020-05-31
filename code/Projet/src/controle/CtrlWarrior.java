package controle;

import java.util.List;
import java.util.Observable;

import abstraction.Warrior;
import presentation.MainEventHandler;
import presentation.PresWarrior;
import shapeSceneFX.Point;
/**
 * Combattant
 * @author Administrator
 * @see Warrior
 * @see PresWarrior
 */
public abstract class CtrlWarrior extends CtrlEntity {
	private PresWarrior presWarrior;
	private Warrior warrior;
	/**
	 * bouclier (s'il en possede un)
	 */
	private CtrlShield ctrlShield;
	/**
	 * main derriere, equipement par defaut
	 */
	private CtrlHandBack ctrlHandBack;
	/**
	 * main devant equipmenent par defaut
	 */
	private CtrlHandFront ctrlHandFront;
	/**
	 * equipment actuel
	 */
	private CtrlEquipment front, back;
	public CtrlWarrior(Warrior _warrior, PresWarrior _presWarrior) {
		super(_warrior, _presWarrior);
		warrior = _warrior;
		presWarrior = _presWarrior;
		presWarrior.setSpeed(warrior.getSpeed());
		presWarrior.setLookingTo(warrior.getLookingTo().copy().mult(MainEventHandler.pxSize));
		presWarrior.setCtrlWarrior(this);
		ctrlHandBack = new CtrlHandBack();
		ctrlHandBack.setCtrlWarrior(this);
		ctrlHandFront = new CtrlHandFront();
		equip(ctrlHandFront);
	}
	/**
	 * Retourne les enemi qui recevront potentiellement les degat des arme du combattant
	 * @return
	 */
	public abstract List<? extends CtrlEntity> getListOfEnemy();
	
	@Override
	public void update(Observable o, Object arg) {
		super.update(o, arg);
		if(arg.equals(Warrior.LOOKINGTO_UPDATE)) {
			presWarrior.setLookingTo(warrior.getLookingTo());
		} else if(arg.equals(Warrior.WALKING_UPDATE)) {
			presWarrior.setWalk(warrior.getWalking());
		}
	}
	
	public Warrior getWarrior() {
		return warrior;
	}
	
	public PresWarrior getPresWarrior() {
		return presWarrior;
	}
	
	public void lookingTo(Point point) {
		warrior.setLookingTo(point.copy().div(MainEventHandler.pxSize));
	}
	
	public void setWalking(boolean value) {
		warrior.setWalking(value);
	}
	/**
	 * relaie l'information du degat a warrior
	 * @see Warrior#damage(int)
	 * @see CtrlShield#damage(int)
	 */
	public void damage(int damage) {
		if(ctrlShield == back) {
			damage = ctrlShield.damage(damage);
		}
		if(damage != 0) {
			warrior.damage(damage);
		}
		//System.out.println(warrior.getLife());
	}
	
	public void kill() {
		currentLevel.remove(this);
	}
	
	public CtrlEquipment getHandFront() {
		return ctrlHandFront;
	}
	/**
	 * Equipe l'equipement
	 * @param e
	 */
	public void equip(CtrlEquipment e) {
		//System.out.println(e + " " + e.getPresEquipment());
		e.setCtrlWarrior(this);
		e.putOnWarrior();
	}
	/**
	 * Equipe le bouclier
	 * @param e
	 */
	public void equip(CtrlShield e) {
		ctrlShield = e;
		e.setCtrlWarrior(this);
		equip(front); // on réequipe le front pour savoir si on peut mettre le shield en main secondaire
	}
	/**
	 * Place l'equipment coté presentation
	 * @param _front
	 * @param _back
	 */
	public void putEquipment(CtrlEquipment _front, CtrlEquipment _back) {
		putEquipmentFront(_front);
		putEquipmentBack(_back);
	}
	/**
	 * Place l'equipment coté presentation
	 * @param _front
	 */
	public void putEquipmentFront(CtrlEquipment _front) {
		if(front != null) {
			getWarrior().deleteObserver(front);
			front.getPresEquipment().reset();
		}
		front = _front;
		getWarrior().addObserver(front);
		getPresWarrior().setPresEquipmentFront(front.getPresEquipment());
		front.setLooking();
		putEquipmentBack((ctrlShield == null)? ctrlHandBack : ctrlShield);
	}
	/**
	 * Place l'equipment coté presentation
	 * @param _back
	 */
	public void putEquipmentBack(CtrlEquipment _back) {
		if(back != null) {
			getWarrior().deleteObserver(_back);
			back.getPresEquipment().reset();
		}
		back = _back;
		getWarrior().addObserver(back);
		getPresWarrior().setPresEquipmentBack(back.getPresEquipment());
		back.setLooking();
	}
}
