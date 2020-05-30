package presentation;

import controle.CtrlEnemy;
import controle.CtrlPlayer;
import shapeSceneFX.Point;
import shapeSceneFX.EventHandling.Event;
/**
 * Represente un enemi
 * @author Administrator
 *
 */
public class PresEnemy extends PresWarrior {
	/**
	 * coordonnée vers laquelle l'enemy doit se rendre
	 */
	private Point objective;
	private CtrlEnemy ctrlEnemy;
	private CtrlPlayer ctrlPlayer;
	/**
	 * temps en ms entre les appel de la methode action
	 * @see PresEnemy#action()
	 */
	private int actionRate;
	public PresEnemy(OrientedImage _body, OrientedImage _shoulder, OrientedImage _leg, OrientedImage[] _walkingLeg, int _actionRate) {
		super(_body, _shoulder, _leg, _walkingLeg);
		actionRate = _actionRate;
	}
	
	protected void calc(long timePassed) {
		super.calc(timePassed);
		calcMove(timePassed);
	}
	
	public void render() {
		if(getCtrlPlayer() != null) {
			getCtrlWarrior().lookingTo(getCtrlPlayer().getPresPlayer().getCoord().copy().add(PresPlayer.frontEquipment).sub(getCoord()));
			//getCtrlWarrior().lookingTo(new Point(mouseX(), mouseY()).add(CtrlPlayer.currentLevel.getPresLevel().getCamera()).sub(getCoord()));
		}
		super.render();
	}
	/**
	 * méthode de calcul du deplacement de l'enemi pour atteindre l'objectif
	 * @param timePassed
	 */
	public void calcMove(long timePassed) {
		if(objective != null && timePassed > 0) {
			/*Point vec = getCoord().getVector(objective);
			double ang = vec.getAngle().getZ();
			Point o = new Point(Math.cos(ang)*getSpeedCoef().getX(), Math.sin(ang)*getSpeedCoef().getY());
			o.div(o.getDist()).mult(timePassed*getSpeed());
			double coef = o.getDist()/vec.getDist();*/
			Point vec = getCtrlEnemy().getEnemy().getCoord().getVector(getCtrlEnemy().getEnemy().getObjective());
			Point mov = vec.copy().div(vec.getDist()).mult(timePassed*getSpeed()*getSpeedCoef().getX());
			double coef = mov.getDist()/vec.getDist();
			//System.out.println((mov.getDist() >= vec.getDist()) + " " + getCoord() + " " + mov + " " +  mov.getDist() + " " + vec + " " + vec.getDist());
			if(mov.getDist() >= vec.getDist()) {
				mov.mult(1/coef);
				getCtrlEnemy().move(mov.div(MainEventHandler.pxSize));
				getCtrlEnemy().endObjective();
				calcMove((long)(timePassed*(1-1/coef)));
			} else {
				getCtrlEnemy().move(mov.div(MainEventHandler.pxSize));
			}
		}
	}
	
	public void setObjective(Point _objective) {
		if(_objective != null && objective != null && objective.equals(_objective)) {
			getCtrlEnemy().endObjective();
			return;
		}
		objective = _objective;
		if(getWalk() && (objective == null || getCoord().equals(objective))) {
			setWalk(false);
		}
		if(objective != null && !getWalk()) {
			setWalk(true);
		}
	}
	
	public CtrlEnemy getCtrlEnemy() {
		return ctrlEnemy;
	}
	
	public void setCtrlEnemy(CtrlEnemy _ctrlEnemy) {
		ctrlEnemy = _ctrlEnemy;
	}
	
	public void setCtrlPlayer(CtrlPlayer _ctrlPlayer) {
		ctrlPlayer = _ctrlPlayer;
		addEvent(new ActionEvent().in(0));
	}
	
	public CtrlPlayer getCtrlPlayer() {
		return ctrlPlayer;
	}
	/**
	 * méthode appeler à intervalle regulié (actionRate), pour gerer les attaque de l'enemy 
	 * @see PresEnemy#actionRate
	 */
	public void action() {
	}
	/**
	 * Evenement qui lorsqu'il est lancé appel la methode action et programme un evenement pour lancé action plus tard
	 * @author Administrator
	 *
	 */
	public class ActionEvent implements Event {
		@Override
		public void handleEvent() {
			action();
			addEvent(new ActionEvent().in(actionRate));
		}
	}
}
