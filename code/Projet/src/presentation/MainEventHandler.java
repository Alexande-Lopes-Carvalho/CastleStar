package presentation;

import controle.CtrlLevel;
import controle.CtrlLevel_1;
import shapeSceneFX.EventHandling.EventHandler;
import shapeSceneFX.EventHandling.TransferableEvent;

public class MainEventHandler extends EventHandler {
	public static int pxSize = 3;
	private CtrlLevel ctrlLevel;
	private PresLevel presLevel;
	public void setup() {
		size(pxSize*402, pxSize*160);
		ctrlLevel = new CtrlLevel_1();
		presLevel = ctrlLevel.getPresLevel();
	}
	
	protected void calc(long timePassed) {
		presLevel.calcEvent(timePassed);
	}
	
	public void render() {
		presLevel.render();
	}
	
	public void transferEvent(TransferableEvent e) {
		presLevel.addEvent(e.in(0));
	}
}
