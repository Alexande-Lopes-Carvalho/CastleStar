package presentation;

import controle.CtrlColoredRectangle;
import javafx.scene.paint.Paint;
import shapeSceneFX.Point;
import shapeSceneFX.EventHandling.Event;
import shapeSceneFX.EventHandling.EventHandler;

public class PresColoredRectangle extends EventHandler {
	private CtrlColoredRectangle c;
	private Paint p;
	private Point coord, dim;
	public PresColoredRectangle(CtrlColoredRectangle _c) {
		c = _c;
		addEvent(new NewColorEvent().in(NewColorEvent.PERIOD));
	}
	
	public void setPaint(Paint _p) {
		p = _p;
	}
	
	public void calc(long timePassed) {
	}
	
	public void render() {
		fill(p);
		rect(coord, dim);
	}
	
	public Point getCoord() {
		return coord;
	}

	public void setCoord(Point coord) {
		this.coord = coord;
	}

	public Point getDimension() {
		return dim;
	}

	public void setDimension(Point dim) {
		this.dim = dim;
	}

	public void mousePressed() {
		c.newColor();
	}
	
	public class NewColorEvent implements Event {
		public static final int PERIOD = 1000;
		@Override
		public void handleEvent() {
			c.newColor();
			addEvent(new NewColorEvent().in(PERIOD));
		}
	}
}
