package presentation;

import controle.CtrlShield;
import javafx.scene.input.MouseButton;
import shapeSceneFX.EventHandling.Event;
/**
 * represente un bras tenant un bouclier
 * @author Administrator
 *
 */
public class PresShield extends PresEquipment {
	/**
	 * durée en ms avant de pouvoir réactiver le bouclier après avoir parer un coup
	 */
	private int cooldownTime;
	private boolean canUse, use;
	private boolean protect;
	private CtrlShield ctrlShield;
	public PresShield(int _cooldownTime, CtrlShield _ctrlShield) {
		cooldownTime = _cooldownTime;
		canUse = true;
		ctrlShield = _ctrlShield;
		use = false;
	}
	/**
	 * active le bouclier
	 */
	public void requestUse() {
		use = true;
		if(canUse) {
			getCtrlEquipment().use();
		}
	}
	/**
	 * desactive le bouclier
	 */
	public void resquestDontUse() {
		use = false;
		if(canUse) {
			ctrlShield.stopUse();
		}
	}
	/**
	 * si l'instance reçois un evenement bouton droit de la souris enfoncé (méthode lancé s'il s'agit du bras d'un joueur) active le bouclier
	 */
	public void mousePressed() {
		if(mouseButton().equals(MouseButton.SECONDARY)) {
			requestUse();
		}
	}
	/**
	 * si l'instance reçois un evenement bouton droit de la souris relaché (méthode lancé s'il s'agit du bras d'un joueur) desactive le bouclier
	 */
	public void mouseReleased() {
		if(mouseButton().equals(MouseButton.SECONDARY)) {
			resquestDontUse();
		}
	}
	
	public void setProtect(boolean _protect) {
		protect = _protect;
		if(protect) {
			getAnimatedOrientedImage().setIndex(1);
		} else {
			getAnimatedOrientedImage().setIndex(0);
		}
	}
	
	public void cantUse() {
		canUse = false;
		addEvent(new CanUseEvent().in(cooldownTime));
	}
	
	public class CanUseEvent implements Event{
		@Override
		public void handleEvent() {
			canUse = true;
			if(use) {
				getCtrlEquipment().use();
			}
		}
	}
	
	public void reset() {
		resquestDontUse();
	}
}
