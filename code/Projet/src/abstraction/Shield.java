package abstraction;

import java.util.List;

import controle.CtrlEntity;
/**
 * Un bouclier
 * @author Administrator
 *
 */
public class Shield extends Equipment {
	public static final Object SHIELD_PROTECT_UPDATE = 8;
	public static final Object SHIELD_BLOCK_ATTACK = 9;
	/**
	 * indique si le bouclier protege le personnage
	 */
	private boolean protect;
	/**
	 * temps en milliseconde avant de pouvoir se protege après avoir paré un coup
	 */
	private int cooldownTime;
	public Shield(int _cooldownTime) {
		cooldownTime = _cooldownTime;
	}
	
	public int getCooldownTime() {
		return cooldownTime;
	}

	@Override
	public void use(Warrior warrior, List<? extends CtrlEntity> enemy) {
		setProtect(true);
	}
	
	public void setProtect(boolean _protect) {
		protect = _protect;
		setChanged();
		notifyObservers(SHIELD_PROTECT_UPDATE);
	}
	
	public boolean getProtect() {
		return protect;
	}
	/**
	 * Retourne les degat reçu par le personnage en fonction de la position du bouclier
	 * @param damage
	 * @return
	 */
	public int damage(int damage) {
		if(protect) {
			setProtect(false);
			setChanged();
			notifyObservers(SHIELD_BLOCK_ATTACK);
			return 0;
		}
		return damage;
	}

}
