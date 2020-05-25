package controle;

import abstraction.Weapon;
import presentation.PresWeapon;

public class CtrlSword extends CtrlEquipment {
	private Weapon weapon;
	private PresWeapon presWeapon;
	public CtrlSword() {
		this(new Weapon(1, 28.3d, 270));
	}
	
	public CtrlSword(Weapon _weapon) {
		super(_weapon);
		weapon = (Weapon)getEquipment();
		presWeapon = new PresWeapon(weapon.getActionTime());
		setPresEquipment(presWeapon);
	}

	@Override
	public void handleCtrlWarrior() {
		getPresEquipment().set(getCtrlWarrior().getPresWarrior().getSword());
	}
	
	public void putOnWarrior() {
		getCtrlWarrior().putEquipmentFront(this);
	}
}
