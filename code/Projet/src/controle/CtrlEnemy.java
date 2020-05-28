package controle;


import java.util.ArrayList;
import java.util.List;

import abstraction.Enemy;
import presentation.PresEnemy;


public class CtrlEnemy extends CtrlWarrior{
	
	public CtrlEnemy(Enemy enemy, PresEnemy presEnemy) {
		super(enemy, presEnemy);
		// TODO Auto-generated constructor stub
	}

	@Override
	public List<? extends CtrlEntity> getListOfEnemy() {
		// TODO Auto-generated method stub
		
		ArrayList<CtrlPlayer> a = (ArrayList<CtrlPlayer>) currentLevel.getCtrlPlayerList();
		return  a;
		
	}

	

}
