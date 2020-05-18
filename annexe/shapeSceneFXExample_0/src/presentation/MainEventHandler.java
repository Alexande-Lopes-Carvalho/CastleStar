package presentation;

import abstraction.ColoredRectangle;
import controle.CtrlColoredRectangle;
import shapeSceneFX.Point;
import shapeSceneFX.EventHandling.EventHandler;
import shapeSceneFX.EventHandling.TransferableEvent;

public class MainEventHandler extends EventHandler {
	/**
	 * setup est ici pour definir des information propre au lancement du canvas
	 * comme par exemple sa taille (avec size())
	 * @see shapeSceneFX.ProcessingMethod#size(int, int)
	 */
	PresColoredRectangle r;
	public void setup() {
		// ici on definit une fenetre de 250 pixel par 250
		size(250, 250);
		CtrlColoredRectangle c = new CtrlColoredRectangle(new ColoredRectangle(new Point(50, 50), new Point(60, 60)));
		r = c.getP();
	}
	
	protected void calc(long timePassed) {
		r.calcEvent(timePassed);
	}
	
	public void render() {
		background(0);
		r.render();
		stroke(255);
		text(""+frameCount(), 20, 20);
		stroke(0);
	}
	
	public void mousePressed() {
	}
	
	public void transferEvent(TransferableEvent e) {
		addEvent(e.in(0));
		r.addEvent(e.in(0));
	}
}
