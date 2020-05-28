package controle;

import abstraction.Hand;
import presentation.PresEquipment;

public class CtrlHandBack extends CtrlEquipment {
	public CtrlHandBack() {
		super(new Hand());
		setPresEquipment(new PresEquipment());
	}

	@Override
	public void use() {
		// ne fait rien
	}

	@Override
	public void handleCtrlWarrior() {
		//System.out.println("pass");
		getPresEquipment().set(getCtrlWarrior().getPresWarrior().getHandBack());
	}

	@Override
	public void putOnWarrior() {
		getCtrlWarrior().putEquipmentBack(this);
	}
}
