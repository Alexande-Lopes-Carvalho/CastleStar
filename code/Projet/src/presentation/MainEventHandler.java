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
		Items.initImage();
		Lootables.initImage();
		PresWarrior.initImage();
		PresPlayer.initImage();
		PresOrc.initImage();
		PresArrow.initImage();
		PresInventory.initImage();
		ctrlLevel = new CtrlLevel_1();
		presLevel = ctrlLevel.getPresLevel();
	}
	
	protected void calc(long timePassed) {
		presLevel.calcEvent(timePassed);
	}
	
	public void render() {
		presLevel.render();
		stroke(255);
		text(Math.round(frameRate())+"", 15, 170);
		stroke(0);
	}
	
	public void transferEvent(TransferableEvent e) {
		//System.out.println("TransferEvent From MainEventHandler");
		presLevel.addEvent(e.transfer().in(0));
	}
}
