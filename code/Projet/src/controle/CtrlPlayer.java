package controle;

import java.util.ArrayList;
import java.util.List;

import abstraction.Player;
import presentation.PresPlayer;
import shapeSceneFX.Point;

public class CtrlPlayer extends CtrlWarrior {
	private PresPlayer presPlayer;
	private Player player;
	public CtrlPlayer(Player _player, PresPlayer _presPlayer) {
		super(_player, _presPlayer);
		player = _player;
		presPlayer = _presPlayer;
		presPlayer.setCtrlPlayer(this);
	}

	@Override
	public List<CtrlEntity> getListOfEnemy() {
		return new ArrayList<CtrlEntity>(); // A FAIRE
	}
	
	public void move(Point deplacement) {
		super.move(deplacement);
		currentLevel.playerMoved(this);
	}
	
	public PresPlayer getPresPlayer() {
		return presPlayer;
	}
	
	public void kill() {
		currentLevel.remove(this);
	}
}
