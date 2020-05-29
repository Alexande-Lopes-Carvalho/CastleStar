package abstraction;

import controle.CtrlEnemy;
import controle.CtrlPlayer;
import shapeSceneFX.Point;

public abstract class Enemy extends Warrior {
	public static final Object OBJECTIVE_UPDATE = 13;
	public static final Object FOCUS_PLAYER = 14;
	private CtrlPlayer playerFocused;
	private double range;
	private Point objective;
	public Enemy(double _range, int _nbArrow, Point _lookingTo, double _speed,int _maxLife, Point _coord, Rectangle _rectangle) {
		super(_nbArrow, _lookingTo, _speed, _maxLife, _coord, _rectangle);
		range = _range;
	}
	
	public void playerMoved(CtrlPlayer p) {
		if(playerFocused == null && p.getPlayer().getCenterHitbox().getDist(getCenterHitbox()) <= range) {
			focusPlayer(p);
		}
	}
	
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
	
	public abstract void refreshItinary(CtrlEnemy e);
}
