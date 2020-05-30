package presentation;

import controle.CtrlShield;
import javafx.scene.input.MouseButton;
import shapeSceneFX.EventHandling.Event;

public class PresShield extends PresEquipment {
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
	
	public void requestUse() {
		use = true;
		if(canUse) {
			getCtrlEquipment().use();
		}
	}
	
	public void resquestDontUse() {
		use = false;
		if(canUse) {
			ctrlShield.stopUse();
		}
	}
	
	public void mousePressed() {
		if(mouseButton().equals(MouseButton.SECONDARY)) {
			requestUse();
		}
	}
	
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
