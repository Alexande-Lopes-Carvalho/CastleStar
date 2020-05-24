package controle;

import java.util.Observable;
import java.util.Observer;

import abstraction.Equipment;
import abstraction.Warrior;
import presentation.PresEquipment;


public abstract class CtrlEquipment implements Observer {
	private CtrlWarrior ctrlWarrior;
	private Equipment equipment;
	private PresEquipment presHand;
	public CtrlEquipment(Equipment _equipment) {
		equipment = _equipment;
		equipment.addObserver(this);
	}
	
	public void use() {
		equipment.use(ctrlWarrior.getWarrior(), ctrlWarrior.getListOfEnemy());
	}
	public abstract void handleCtrlWarrior();
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
		System.out.println("handleCtrlWarrior");
	}
	
	public CtrlWarrior getCtrlWarrior() {
		return ctrlWarrior;
	}
	
	public void update(Observable o, Object arg) {
		if(arg.equals(Warrior.LOOKINGTO_UPDATE)) {
			presHand.setOrentation(ctrlWarrior.getWarrior().getFacingLeft(), ctrlWarrior.getWarrior().getLookingTo().getAngle().getZ());
		}
	}
}
