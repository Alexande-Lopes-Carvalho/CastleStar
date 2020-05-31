package controle;

import java.util.Observable;

import abstraction.Shield;
import presentation.PresShield;
/**
 * Bouclier
 * @author Administrator
 * @see Shield
 * @see PresShield
 */
public class CtrlShield extends CtrlEquipment {
	private Shield shield;
	private PresShield presShield;
	public CtrlShield() {
		super(new Shield(800));
		shield = (Shield)getEquipment();
		presShield = new PresShield(shield.getCooldownTime(), this);
		setPresEquipment(presShield);
	}
	
	public CtrlShield(Shield e) {
		super(e);
		shield = (Shield)getEquipment();
		presShield = new PresShield(shield.getCooldownTime(), this);
		setPresEquipment(presShield);
	}

	@Override
	public void handleCtrlWarrior() {
		getPresEquipment().set(getCtrlWarrior().getPresWarrior().getShield());
	}
	/**
	 * Descative le bouclier
	 */
	public void stopUse() {
		shield.setProtect(false);
	}

	@Override
	public void putOnWarrior() {
		getCtrlWarrior().putEquipmentBack(this);
	}
	/**
	 * Retourne les degat apres absrobtion (ou non) du bouclier
	 * @param damage
	 * @return
	 * @see Shield#damage(int)
	 */
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
