package abstraction;

import controle.CtrlPlayer;
import shapeSceneFX.Point;
/**
 * Item qui donne de la vie au personnage qui le recupere
 * @author Administrator
 *
 */
public class HeartItem extends Item{
	/**
	 * nombre d'unit� de vie (demi coeur) a donn�e au personnage qui le recup�re
	 */
	private int sustain;
	public HeartItem(int _sustain, Point coord) {
		super(coord, new Ellipse(new Point(0, 0), 9));
		sustain = _sustain;
	}
	
	@Override
	public boolean pickedUpBy(CtrlPlayer player) {
		player.getPlayer().heal(sustain);
		return true; 
	}
}
