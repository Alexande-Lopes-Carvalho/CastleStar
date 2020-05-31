package abstraction;

import controle.CtrlEnemy;
import controle.CtrlPlayer;
import shapeSceneFX.Point;
/**
 * Un squelette possede un arc et a une strategie de deplacement different de l'orc (alignement sur l'axe Y pour pouvoir tiré)
 * @author Administrator
 *
 */
public class Skeleton extends Enemy {
	/**
	 * Distance a partir de laquelle les joueur devienne visible pour le squelette
	 */
	public static final double range = 230;
	public Skeleton(Point _coord) {
		super(range, 1, new Point(-50, 0), 0.05, 6, _coord, new Rectangle(new Point(-5, -3), new Point(10, 3)));
	}
	
	public void setNbArrow(int _nbArrow) {
		super.setNbArrow(1);   
	}
	
	public void focusPlayer(CtrlPlayer e) {
		super.focusPlayer(e);
		setObjective(new Point(getCoord().getX(),getPlayerFocused().getPlayer().getCoord().getY()));
	}

	public void endObjective() {
		if(getPlayerFocused() != null && getObjective().getY() != getPlayerFocused().getPlayer().getCoord().getY()) {
			setObjective(new Point(getCoord().getX(),getPlayerFocused().getPlayer().getCoord().getY()));
		} else {
			setObjective(null);
		}
	}
	/**
	 * Alignement sur l'axe Y du joueur "focus"
	 */
	@Override
	public void refreshItinary(CtrlEnemy e) {
		if(getPlayerFocused() != null && (getObjective() == null || getObjective().getY() != getPlayerFocused().getPlayer().getCoord().getY())) {
			setObjective(new Point(getCoord().getX(),getPlayerFocused().getPlayer().getCoord().getY()));
		}
	}
}