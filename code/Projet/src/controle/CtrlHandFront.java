package controle;

import abstraction.Hand;
import presentation.PresEquipment;
/**
 * Main devant le personnage
 * @author Administrator
 *
 */
public class CtrlHandFront extends CtrlEquipment {

	public CtrlHandFront() {
		super(new Hand());
		setPresEquipment(new PresEquipment());
	}

	@Override
	public void use() {
		// ne fait rien
	}

	@Override
	public void handleCtrlWarrior() {
		getPresEquipment().set(getCtrlWarrior().getPresWarrior().getHandFront());
	}
	
	public void putOnWarrior() {
		getCtrlWarrior().putEquipmentFront(this);
	}
}
