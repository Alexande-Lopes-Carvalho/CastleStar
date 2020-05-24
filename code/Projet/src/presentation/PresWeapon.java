package presentation;

import javafx.scene.input.MouseButton;
import shapeSceneFX.EventHandling.Event;

public class PresWeapon extends PresEquipment {
	private long actionTime;
	private boolean action;
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
	
	public void launchAction() {
		if(!action) {
			action = true;
			addEvent(new ActionEvent().in(0));
		}
	}
	
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
			addEvent(new ActionEvent().in(actionTime/a.getLength()));
		}
	}
}