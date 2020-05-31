package controle;

import java.util.ArrayList;
import java.util.List;
import java.util.Observable;

import abstraction.Enemy;
import presentation.MainEventHandler;
import presentation.PresEnemy;
/**
 * Enemy
 * @author Administrator
 * @see Enemy
 * @see PresEnemy
 */
public class CtrlEnemy extends CtrlWarrior {
	private Enemy enemy;
	private PresEnemy presEnemy;
	public CtrlEnemy(Enemy _enemy, PresEnemy _presEnemy) {
		super(_enemy, _presEnemy);
		enemy = _enemy;
		presEnemy = _presEnemy;
		presEnemy.setCtrlEnemy(this);
		//System.out.println("a");
		presEnemy.setObjective((enemy.getObjective() == null)? null : enemy.getObjective().copy().mult(MainEventHandler.pxSize));
		//System.out.println("b");
	}
	/**
	 * Si un joueur c'est deplacé on relaie l'information a enemy
	 * @param p
	 * @see Enemy#playerMoved(CtrlPlayer)
	 */
	public void playerMoved(CtrlPlayer p) {
		enemy.playerMoved(p);
	}
	
	public Enemy getEnemy() {
		return enemy;
	}
	
	public void refreshItinary() {
		enemy.refreshItinary(this);
	}
	
	public void kill() {
		currentLevel.remove(this);
	}
	
	@Override
	public List<? extends CtrlEntity> getListOfEnemy() {
		return new ArrayList<>(currentLevel.getCtrlPlayerList());
	}
	/**
	 * Si l'enemi a atteint son objectif on relaie l'information a enemy
	 * @see Enemy#endObjective()
	 */
	public void endObjective() {
		enemy.endObjective();
	}
	
	public void update(Observable o, Object arg) {
		super.update(o, arg);
		if(arg.equals(Enemy.FOCUS_PLAYER)) {
			presEnemy.setCtrlPlayer(enemy.getPlayerFocused());
		} else if(arg.equals(Enemy.OBJECTIVE_UPDATE)) {
			presEnemy.setObjective((enemy.getObjective() == null)? null : enemy.getObjective().copy().mult(MainEventHandler.pxSize));
		}
	}
}
