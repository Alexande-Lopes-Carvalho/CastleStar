package abstraction;

import controle.CtrlSword;
import shapeSceneFX.Point;

public class SwordItem extends InventoryItem{
	public SwordItem(CtrlSword _ctrlSword, Point coord){
		super(_ctrlSword, coord, new Ellipse(new Point(0, 0), 18));
	}
}
