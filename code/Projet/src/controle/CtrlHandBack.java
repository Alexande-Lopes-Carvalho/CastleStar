package controle;

import abstraction.Equipment;
import abstraction.Hand;
import presentation.PresEquipment;

public class CtrlHandBack extends CtrlEquipment {
	public CtrlHandBack() {
		super(new Hand());
		setPresEquipment(new PresEquipment());
	}

	@Override
	void use() {
		// ne fait rien
	}

	@Override
	void handleCtrlWarrior() {
		System.out.println("pass");
		getPresEquipment().set(getCtrlWarrior().getPresWarrior().getHandBack());
	}

	@Override
	void putOnWarrior() {
		getCtrlWarrior().putEquipmentBack(this);
	}
}
