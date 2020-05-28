package abstraction;

import controle.CtrlInventoryEquipment;
import controle.CtrlPlayer;
import shapeSceneFX.Point;

public class InventoryItem extends Item {
	private CtrlInventoryEquipment ctrlInventoryEquipment;
	public InventoryItem(CtrlInventoryEquipment _ctrlInventoryEquipment, Point coord, Ellipse ellipse) {
		super(coord, ellipse);
		ctrlInventoryEquipment = _ctrlInventoryEquipment;
	}
	@Override
	public boolean pickedUpBy(CtrlPlayer player) {
		player.getCtrlInventory().add(ctrlInventoryEquipment);
		return true;
	}
}
