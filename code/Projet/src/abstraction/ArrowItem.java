package abstraction;

import controle.CtrlPlayer;
import shapeSceneFX.Point;

public class ArrowItem extends Item{
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
