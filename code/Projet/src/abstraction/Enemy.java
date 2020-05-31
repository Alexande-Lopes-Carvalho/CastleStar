package abstraction;

import controle.CtrlEnemy;
import controle.CtrlPlayer;
import shapeSceneFX.Point;
/**
 * Enemi
 * @author Administrator
 *
 */
public abstract class Enemy extends Warrior {
	public static final Object OBJECTIVE_UPDATE = 13;
	public static final Object FOCUS_PLAYER = 14;
	/**
	 * Joueur que l'enemi voudra attaqué
	 */
	private CtrlPlayer playerFocused;
	/**
	 * dès qu'un joueur sera a une distance inferieur a range de l'ennemi, alors le joueur deviendra visible pour l'ennemi et il commencera a l'attaqué
	 */
	private double range;
	/**
	 * Coordonné que l'enemi doit atteindre, (egale a null si doit rester immobile)
	 */
	private Point objective;
	public Enemy(double _range, int _nbArrow, Point _lookingTo, double _speed,int _maxLife, Point _coord, Rectangle _rectangle) {
		super(_nbArrow, _lookingTo, _speed, _maxLife, _coord, _rectangle);
		range = _range;
	}
	/**
	 * si le joueur c'est deplacé on regarde s'il est visible pour l'enemi et on agit en conséquence
	 * @param p
	 */
	public void playerMoved(CtrlPlayer p) {
		if(playerFocused == null && p.getPlayer().getCenterHitbox().getDist(getCenterHitbox()) <= range) {
			focusPlayer(p);
		}
	}
	/**
	 * un joueur est visible pour l'enemi, il va alors le "focus"
	 * @param p
	 */
	public void focusPlayer(CtrlPlayer p) {
		playerFocused = p;
		setChanged();
		notifyObservers(FOCUS_PLAYER);
	}
	
	public void endObjective() {
		setObjective(null);
	}
	
	public void setObjective(Point coord) {
		objective = coord;
		setChanged();
		notifyObservers(OBJECTIVE_UPDATE);
	}
	
	public Point getObjective() {
		return objective;
	}
	
	public CtrlPlayer getPlayerFocused() {
		return playerFocused;
	}
	/**
	 * méthode qui calcule ou l'ennemi doit se rendre
	 * @param e
	 */
	public abstract void refreshItinary(CtrlEnemy e);
}
