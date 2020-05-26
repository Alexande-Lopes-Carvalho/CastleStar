package abstraction;

import controle.CtrlPlayer;
import controle.CtrlSword;
import shapeSceneFX.Point;

public class SwordItem extends Item{
	private CtrlSword ctrlSword;
	public SwordItem(CtrlSword _ctrlSword, Point coord){
		super(coord, new Ellipse(new Point(0, 0), 18));
		ctrlSword = _ctrlSword;
	}

	@Override
	public boolean pickedUpBy(CtrlPlayer player) {
		//CtrlInventory ctrlInventory = ctrlplayer.getCtrlInventory();//a ajouter dans Ctrl Inventory	 A FAIRE 
		player.equip(ctrlSword);
		return true;
	}
}
