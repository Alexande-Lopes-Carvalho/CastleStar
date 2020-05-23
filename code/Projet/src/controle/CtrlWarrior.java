package controle;

import java.util.List;
import java.util.Observable;

import abstraction.Warrior;
import presentation.MainEventHandler;
import presentation.PresWarrior;
import shapeSceneFX.Point;

public abstract class CtrlWarrior extends CtrlEntity {
	private PresWarrior presWarrior;
	private Warrior warrior;
	//private CtrlShield ctrlShield;
	private CtrlHandBack ctrlHandBack;
	private CtrlHandFront ctrlHandFront;
	//private CtrlShield ;
	private CtrlEquipment front, back;
	public CtrlWarrior(Warrior _warrior, PresWarrior _presWarrior) {
		super(_warrior, _presWarrior);
		warrior = _warrior;
		presWarrior = _presWarrior;
		presWarrior.setLookingTo(warrior.getLookingTo().copy().mult(MainEventHandler.pxSize));
		presWarrior.setCtrlWarrior(this);
		ctrlHandBack = new CtrlHandBack();
		ctrlHandBack.setCtrlWarrior(this);
		ctrlHandFront = new CtrlHandFront();
		ctrlHandFront.setCtrlWarrior(this);
		ctrlHandFront.putOnWarrior();
	}
	
	public abstract List<CtrlEntity> getListOfEnemy();
	
	@Override
	public void update(Observable o, Object arg) {
		super.update(o, arg);
		if(arg.equals(Warrior.LOOKINGTO_UPDATE)) {
			presWarrior.setLookingTo(warrior.getLookingTo());
		} else if(arg.equals(Warrior.SHIELD_PROTECT_UPDATE)) {
			// ctrlShield ...
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
	
	/*public void protect(){
	 
	 }*/
	
	/*public void attack() {
		
	}*/
	
	public void lookingTo(Point point) {
		warrior.setLookingTo(point.copy().div(MainEventHandler.pxSize));
	}
	
	public void setWalking(boolean value) {
		warrior.setWalking(value);
	}
	
	public void putEquipment(CtrlEquipment _front, CtrlEquipment _back) {
		putEquipmentFront(_front);
		putEquipmentBack(_back);
	}
	
	public void putEquipmentFront(CtrlEquipment _front) {
		//System.out.println("putEquipmentFront");
		if(front != null) {
			getWarrior().deleteObserver(front);
		}
		front = _front;
		getWarrior().addObserver(front);
		getPresWarrior().setPresEquipmentFront(front.getPresEquipment());
		putEquipmentBack(ctrlHandBack);
	}
	
	public void putEquipmentBack(CtrlEquipment _back) {
		//System.out.println("putEquipmentBack");
		if(back != null) {
			getWarrior().deleteObserver(_back);
		}
		back = _back;
		getWarrior().addObserver(back);
		getPresWarrior().setPresEquipmentBack(back.getPresEquipment());
	}
}
