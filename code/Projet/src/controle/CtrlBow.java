package controle;

import java.util.Observable;

import abstraction.Bow;
import abstraction.Warrior;
import presentation.PresBow;

public class CtrlBow extends CtrlEquipment {
	private Bow bow;
	private PresBow presBow;
	private CtrlBowBack ctrlBowBack;
	public CtrlBow() {
		super(new Bow(200));
		bow = (Bow)getEquipment();
		presBow = new PresBow(bow.getCooldownTime(), this);
		setPresEquipment(presBow);
		ctrlBowBack = new CtrlBowBack();
	}
	
	public void setCtrlWarrior(CtrlWarrior _ctrlWarrior) {
		super.setCtrlWarrior(_ctrlWarrior);
		ctrlBowBack.setCtrlWarrior(_ctrlWarrior);
		//System.out.println(getCtrlWarrior().getWarrior().getNbArrow());
		bow.setCanShoot(getCtrlWarrior().getWarrior().getNbArrow() > 0);
	}
	
	public void update(Observable o, Object arg) {
		super.update(o, arg);
		if(arg.equals(Warrior.NBARROW_UPDATE)) {
			//System.out.println(getCtrlWarrior().getWarrior().getNbArrow());
			bow.setCanShoot(getCtrlWarrior().getWarrior().getNbArrow() > 0);
		} else if(arg.equals(Bow.UPDATE_CAN_SHOOT)) {
			presBow.setCanShoot(bow.getCanShoot());
		}
	}
	
	@Override
	public void handleCtrlWarrior() {
		getPresEquipment().set(getCtrlWarrior().getPresWarrior().getBowFront());
	}

	@Override
	public void putOnWarrior() {
		getCtrlWarrior().putEquipment(this, ctrlBowBack);
	}
	
	public class CtrlBowBack extends CtrlHandBack{
		public void handleCtrlWarrior() {
			getPresEquipment().set(getCtrlWarrior().getPresWarrior().getBowBack());
		}
	}
}
