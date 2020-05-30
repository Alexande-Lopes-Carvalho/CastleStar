package presentation;

import javafx.scene.input.MouseButton;
import shapeSceneFX.EventHandling.Event;
import shapeSceneFX.EventHandling.ScheduledEvent;
/**
 * Represente un arme qui n'envoie pas de projectile
 * @author Administrator
 *
 */
public class PresWeapon extends PresEquipment {
	/**
	 * temps en milliseconde après le lancement de l'action après lequel l'arme fera son degat
	 */
	private long actionTime;
	private boolean action;
	private ScheduledEvent actionEvent;
	public PresWeapon(long _actionTime) {
		actionTime = _actionTime;
		action = false;
	}

	public void mousePressed() {
		//System.out.println("mousePressedEquipment");
		if(mouseButton().equals(MouseButton.PRIMARY)) {
			//System.out.println("PRIMARY");
			launchAction();
			//getCtrlEquipment().use();
		}
	}
	
	public void reset() {
		removeEvent(actionEvent);
		action = false;
		getAnimatedOrientedImage().setIndex(0);
	}
	/**
	 * Programme un coup qui sera lancé après actionTime milliseconde
	 * @see PresWeapon#actionTime
	 */
	public void launchAction() {
		if(!action) {
			action = true;
			actionEvent = new ActionEvent().in(0);
			addEvent(actionEvent);
		}
	}
	/**
	 * Evenement qui anime le bras du personnage et qui lance le coup a la fin de l'animation
	 * @author Administrator
	 *
	 */
	public class ActionEvent implements Event{
		@Override
		public void handleEvent() {
			AnimatedOrientedImage a = getAnimatedOrientedImage();
			a.setIndex((a.getIndex()+1)%a.getLength());
			if(a.getIndex() == 0) {
				action = false;
				getCtrlEquipment().use();
				return;
			}
			actionEvent = new ActionEvent().in(actionTime/a.getLength());
			addEvent(actionEvent);
		}
	}
}
