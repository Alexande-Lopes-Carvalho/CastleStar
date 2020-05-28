package abstraction;

import controle.CtrlDagger;
import shapeSceneFX.Point;

public class DaggerItem extends InventoryItem {
	public DaggerItem(CtrlDagger _ctrlDagger, Point coord){
		super(_ctrlDagger, coord, new Ellipse(new Point(0, 0), 7.5));
	}
}
