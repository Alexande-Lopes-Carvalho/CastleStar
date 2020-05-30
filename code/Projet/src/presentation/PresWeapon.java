package presentation;

import javafx.scene.input.MouseButton;
import shapeSceneFX.EventHandling.Event;
import shapeSceneFX.EventHandling.ScheduledEvent;

public class PresWeapon extends PresEquipment {
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
	
	public void launchAction() {
		if(!action) {
			action = true;
			actionEvent = new ActionEvent().in(0);
			addEvent(actionEvent);
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
			actionEvent = new ActionEvent().in(actionTime/a.getLength());
			addEvent(actionEvent);
		}
	}
}
