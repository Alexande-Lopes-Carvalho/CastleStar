package abstraction;

import controle.CtrlPlayer;
import controle.CtrlShield;
import shapeSceneFX.Point;
/**
 * Item qui donne un bouclier a celui qui le ramasse
 * @author Administrator
 *
 */
public class ShieldItem extends Item{
	private CtrlShield ctrlShield;
	public ShieldItem(CtrlShield _ctrlShield, Point coord) {
		super(coord, new Ellipse(new Point(0, 0), 13.5));
		ctrlShield = _ctrlShield;
	}
	
	public boolean pickedUpBy(CtrlPlayer ctrlPlayer) {
		ctrlPlayer.equip(ctrlShield);
		return true;
	}
}
