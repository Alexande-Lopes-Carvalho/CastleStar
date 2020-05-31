package abstraction;

import controle.CtrlLance;
import shapeSceneFX.Point;

/**
 * Item qui donne une lance a celui qui le recupère
 * @author Administrator
 *
 */
public class LanceItem extends InventoryItem {
	public LanceItem(CtrlLance _ctrlLance, Point coord) {
		super(_ctrlLance, coord, new Ellipse(new Point(0, 0), new Point(42, 8)));
	}
}
