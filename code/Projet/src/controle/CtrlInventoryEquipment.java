package controle;

import abstraction.Equipment;
import javafx.scene.image.Image;

public abstract class CtrlInventoryEquipment extends CtrlEquipment {
	private Image inventoryImage;
	public CtrlInventoryEquipment(Equipment e, Image _inventoryImage) {
		super(e);
		inventoryImage = _inventoryImage;
	}
	
	public Image getInventoryImage() {
		return inventoryImage;
	}
}
