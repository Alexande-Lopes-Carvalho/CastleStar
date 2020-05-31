package abstraction;

import controle.CtrlBow;
import shapeSceneFX.Point;
/**
 * item qui donnera un arc au personnage qui le ramassera
 * @author Administrator
 *
 */
public class BowItem extends InventoryItem {
	public BowItem(CtrlBow _ctrlBow, Point coord) {
		super(_ctrlBow, coord, new Ellipse(new Point(0, 0), 14.5));
	}
}
