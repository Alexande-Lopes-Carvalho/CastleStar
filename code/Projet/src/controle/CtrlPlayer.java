package controle;

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
		return null;
	}
	
	public void move(Point deplacement) {
		super.move(deplacement);
		currentLevel.playerMoved(this);
	}
	
	public PresPlayer getPresPlayer() {
		return presPlayer;
	}
}
