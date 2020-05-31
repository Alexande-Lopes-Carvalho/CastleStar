package abstraction;

import controle.CtrlSword;
import shapeSceneFX.Point;
/**
 * Item qui donne une épée a celui qui le ramasse
 * @author Administrator
 *
 */
public class SwordItem extends InventoryItem{
	public SwordItem(CtrlSword _ctrlSword, Point coord){
		super(_ctrlSword, coord, new Ellipse(new Point(0, 0), 18));
	}
}
