package controle;

import abstraction.Equipment;
import abstraction.Hand;
import presentation.PresEquipment;

public class CtrlHandFront extends CtrlEquipment {

	public CtrlHandFront() {
		super(new Hand());
		setPresEquipment(new PresEquipment());
	}

	@Override
	void use() {
		// ne fait rien
	}

	@Override
	void handleCtrlWarrior() {
		getPresEquipment().set(getCtrlWarrior().getPresWarrior().getHandFront());
	}
	
	public void putOnWarrior() {
		getCtrlWarrior().putEquipmentFront(this);
	}
}
