package controle;

import java.util.Observable;
import java.util.Observer;

import abstraction.Equipment;
import abstraction.Warrior;
import presentation.PresEquipment;

/**
 * Equipement
 * @author Administrator
 * @see Equipment
 * @see PresEquipment
 */
public abstract class CtrlEquipment implements Observer {
	/**
	 * Combattant qui possede l'equipement
	 */
	private CtrlWarrior ctrlWarrior;
	private Equipment equipment;
	private PresEquipment presHand;
	public CtrlEquipment(Equipment _equipment) {
		equipment = _equipment;
		equipment.addObserver(this);
	}
	/**
	 * relaie l'information de l'utilisation de l'equipment a equipment
	 * @see Equipment#use(Warrior, java.util.List)
	 */
	public void use() {
		equipment.use(ctrlWarrior.getWarrior(), ctrlWarrior.getListOfEnemy());
	}
	/**
	 * procedure appeler lorsque l'equipement possede un CtrlWarrior
	 */
	public abstract void handleCtrlWarrior();
	/**
	 * procedure pour equipé (visuallement) notre combattant avec l'equipment
	 */
	public abstract void putOnWarrior();
	
	public Equipment getEquipment() {
		return equipment;
	}
	
	public void setPresEquipment(PresEquipment _presHand) {
		presHand = _presHand;
		presHand.setCtrlEquipment(this);
	}
	
	public PresEquipment getPresEquipment() {
		return presHand;
	}
	
	public void setCtrlWarrior(CtrlWarrior _ctrlWarrior) {
		ctrlWarrior = _ctrlWarrior;
		handleCtrlWarrior();
		//System.out.println("handleCtrlWarrior");
	}
	
	public CtrlWarrior getCtrlWarrior() {
		return ctrlWarrior;
	}
	
	public void setLooking() {
		presHand.setOrentation(ctrlWarrior.getWarrior().getFacingLeft(), ctrlWarrior.getWarrior().getLookingTo());
	}
	
	public void update(Observable o, Object arg) {
		if(arg.equals(Warrior.LOOKINGTO_UPDATE)) {
			setLooking();
		}
	}
}
