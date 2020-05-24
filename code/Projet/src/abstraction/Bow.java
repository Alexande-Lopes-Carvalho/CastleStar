package abstraction;

import java.util.List;

import controle.CtrlEntity;

public class Bow extends Equipment {
	public static final Object UPDATE_CAN_SHOOT = 10;
	private int cooldownTime;
	private boolean canShoot;

	public Bow(int _cooldownTime) {
		cooldownTime = _cooldownTime;
		canShoot = false;
	}

	public boolean getCanShoot() {
		return canShoot;
	}

	public void setCanShoot(boolean value) {
		canShoot = value;
		setChanged();
		notifyObservers(UPDATE_CAN_SHOOT);
	}

	public int getCooldownTime() {
		return cooldownTime;
	}

	@Override
	public void use(Warrior warrior, List<? extends CtrlEntity> enemy) {
		warrior.setNbArrow(warrior.getNbArrow() - 1);
		// ... spawn fleche
	}
}
