package controle;

import abstraction.Hand;
import abstraction.Weapon;
import presentation.PresInventory;
import presentation.PresWeapon;

public class CtrlLance extends CtrlInventoryEquipment {
	private PresWeapon presWeapon;
	private Weapon weapon;
	private CtrlLanceBack ctrlLanceBack;
	public CtrlLance() {
		this(new Weapon(2, 39, 500));
	}
	
	public CtrlLance(Weapon _weapon) {
		super(_weapon, PresInventory.lance);
		weapon = _weapon;
		presWeapon = new PresWeapon(weapon.getActionTime());
		setPresEquipment(presWeapon);
		ctrlLanceBack = new CtrlLanceBack();
	}
	
	public void setCtrlWarrior(CtrlWarrior _ctrlWarrior) {
		super.setCtrlWarrior(_ctrlWarrior);
		ctrlLanceBack.setCtrlWarrior(_ctrlWarrior);
	}
	
	
	@Override
	public void handleCtrlWarrior() {
		getPresEquipment().set(getCtrlWarrior().getPresWarrior().getLanceFront());
	}

	@Override
	public void putOnWarrior() {
		getCtrlWarrior().putEquipment(this, ctrlLanceBack);
	}
	
	public class CtrlLanceBack extends CtrlEquipment {
		public CtrlLanceBack() {
			super(new Hand());
			setPresEquipment(new PresWeapon(weapon.getActionTime()));
		}

		@Override
		public void handleCtrlWarrior() {
			getPresEquipment().set(getCtrlWarrior().getPresWarrior().getLanceBack());
		}

		@Override
		public void putOnWarrior() {
			getCtrlWarrior().putEquipmentBack(this);
		}
	}
}
