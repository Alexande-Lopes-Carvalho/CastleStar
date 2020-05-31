package controle;

import java.util.ArrayList;
import java.util.List;

import abstraction.Inventory;
import abstraction.Player;
import presentation.PresPlayer;
import shapeSceneFX.Point;
/**
 * Joueur
 * @author Administrator
 * @see Player
 * @see PresPlayer
 */
public class CtrlPlayer extends CtrlWarrior {
	private PresPlayer presPlayer;
	private Player player;
	private CtrlInventory ctrlInventory;
	public CtrlPlayer(Player _player, PresPlayer _presPlayer) {
		super(_player, _presPlayer);
		player = _player;
		presPlayer = _presPlayer;
		presPlayer.setCtrlPlayer(this);
		ctrlInventory = new CtrlInventory(new Inventory(0, 4), this);
		player.addObserver(ctrlInventory);
		presPlayer.setPresInventory(ctrlInventory.getPresInventory());
	}

	@Override
	public List<CtrlEntity> getListOfEnemy() {
		List<CtrlEntity> l = new ArrayList<CtrlEntity>(currentLevel.getCtrlEntityList()); // POUR TEST, A FAIRE CORRECTEMENT
		l.remove(this);
		return l;
	}
	
	public void move(Point deplacement) {
		super.move(deplacement);
		currentLevel.playerMoved(this);
	}
	
	public PresPlayer getPresPlayer() {
		return presPlayer;
	}
	
	public Player getPlayer() {
		return player;
	}
	/**
	 * Reinitialise le joueur pour le lancement d'un prochain niveau
	 * @see PresPlayer#reset()
	 */
	public void reset() {
		getPlayer().setLookingTo(new Point(50, 0));
		getPlayer().heal(getPlayer().getMaxLife());
		presPlayer.reset();
	}
	
	public void kill() {
		currentLevel.remove(this);
	}
	
	public CtrlInventory getCtrlInventory() {
		return ctrlInventory;
	}
}
