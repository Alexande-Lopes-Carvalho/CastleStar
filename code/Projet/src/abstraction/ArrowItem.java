package abstraction;

import controle.CtrlPlayer;
import shapeSceneFX.Point;
/**
 * Represente un item qui donnera des fleche lorqu'il est ramasser
 * @author Administrator
 *
 */
public class ArrowItem extends Item{
	/**
	 * nombre de fleche a donner lorsque l'item est ramassé
	 */
	private int nbArrow;
	public ArrowItem(int nbArrow, Point coord) {
		super(coord, new Ellipse(new Point(0, 0), 13.5));
		this.nbArrow = nbArrow;
		
	}

	@Override
	public boolean pickedUpBy(CtrlPlayer player) {
		player.getPlayer().setNbArrow(player.getPlayer().getNbArrow()+nbArrow);
		return true;
	}
}
