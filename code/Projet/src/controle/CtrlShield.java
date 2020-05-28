package controle;

import java.util.Observable;

import abstraction.Shield;
import presentation.PresShield;

public class CtrlShield extends CtrlEquipment {
	private Shield shield;
	private PresShield presShield;
	public CtrlShield() {
		super(new Shield(800));
		shield = (Shield)getEquipment();
		presShield = new PresShield(shield.getCooldownTime(), this);
		setPresEquipment(presShield);
	}

	@Override
	public void handleCtrlWarrior() {
		getPresEquipment().set(getCtrlWarrior().getPresWarrior().getShield());
	}
	
	public void stopUse() {
		shield.setProtect(false);
	}

	@Override
	public void putOnWarrior() {
		getCtrlWarrior().putEquipmentBack(this);
	}
	
	public int damage(int damage) {
		return shield.damage(damage);
	}
	
	public void update(Observable o, Object arg) {
		super.update(o, arg);
		if(arg.equals(Shield.SHIELD_PROTECT_UPDATE)) {
			presShield.setProtect(shield.getProtect());
		} else if(arg.equals(Shield.SHIELD_BLOCK_ATTACK)) {
			presShield.cantUse();
		}
	}
}
