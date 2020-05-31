package controle;

import abstraction.Equipment;
import javafx.scene.image.Image;
/**
 * Equipment pouvant placé dans l'inventaire
 * @author Administrator
 *
 */
public abstract class CtrlInventoryEquipment extends CtrlEquipment {
	/**
	 * representation graphique de l'equipment dans l'inventaire
	 */
	private Image inventoryImage;
	public CtrlInventoryEquipment(Equipment e, Image _inventoryImage) {
		super(e);
		inventoryImage = _inventoryImage;
	}
	
	public Image getInventoryImage() {
		return inventoryImage;
	}
}
