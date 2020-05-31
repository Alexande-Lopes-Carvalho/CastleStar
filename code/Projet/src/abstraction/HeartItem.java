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
	 * nombre d'unité de vie (demi coeur) a donnée au personnage qui le recupère
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
