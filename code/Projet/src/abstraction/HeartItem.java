package abstraction;

import controle.CtrlPlayer;
import shapeSceneFX.Point;

public class HeartItem extends Item{
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
