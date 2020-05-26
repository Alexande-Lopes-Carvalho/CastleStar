package abstraction;

import java.util.List;

import controle.CtrlArrow;
import controle.CtrlElementScene;
import controle.CtrlEntity;
import controle.CtrlLevel;
import shapeSceneFX.Point;

public class Bow extends Equipment {
	public static final Object UPDATE_CAN_SHOOT = 10;
	private int cooldownTime;
	private boolean canShoot;
	private Point projectileCoord;
	public Bow(int _cooldownTime) {
		cooldownTime = _cooldownTime;
		canShoot = false;
		projectileCoord = new Point(0, 0);
	}

	public boolean getCanShoot() {
		return canShoot;
	}
	
	public void setProjectileCoord(Point _p) {
		System.out.println(_p);
		projectileCoord.set(_p);
	}

	public void setCanShoot(boolean value) {
		canShoot = value;
		setChanged();
		notifyObservers(UPDATE_CAN_SHOOT);
	}

	public int getCooldownTime() {
		return cooldownTime;
	}

	@Override
	public void use(Warrior warrior, List<? extends CtrlEntity> enemy) {
		System.out.println(projectileCoord.copy().add(warrior.getCoord()) + " " + warrior.getCoord());
		warrior.setNbArrow(warrior.getNbArrow() - 1);
		CtrlElementScene.currentLevel.add(new CtrlArrow(new Arrow(warrior.getFacingLeft(), 2, enemy, projectileCoord.copy().add(warrior.getCoord()), new Rectangle(new Point(0, -projectileCoord.getY()-7), new Point(((warrior.getFacingLeft())? -15 :15) , 14)))));
	}
}
