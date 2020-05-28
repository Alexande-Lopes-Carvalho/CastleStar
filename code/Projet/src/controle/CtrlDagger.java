package controle;

import abstraction.Weapon;
import presentation.PresInventory;
import presentation.PresWeapon;

public class CtrlDagger extends CtrlInventoryEquipment {
	private Weapon weapon;
	private PresWeapon presWeapon;
	public CtrlDagger() {
		this(new Weapon(1, 19, 135));
	}
	
	public CtrlDagger(Weapon _weapon) {
		super(_weapon, PresInventory.dagger);
		weapon = _weapon;
		presWeapon = new PresWeapon(weapon.getActionTime());
		setPresEquipment(presWeapon);
	}
	
	@Override
	public void handleCtrlWarrior() {
		getPresEquipment().set(getCtrlWarrior().getPresWarrior().getDagger());
	}

	@Override
	public void putOnWarrior() {
		getCtrlWarrior().putEquipmentFront(this);
	}

}
