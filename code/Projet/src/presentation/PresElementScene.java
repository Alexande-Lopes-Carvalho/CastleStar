package presentation;

import shapeSceneFX.Point;
import shapeSceneFX.EventHandling.EventHandler;

public abstract class PresElementScene extends EventHandler implements Comparable<PresElementScene> {
	private Point coord;
	private double renderPriority;
	public PresElementScene() {
		coord = new Point(0, 0);
	}

	public Point getCoord() {
		return coord;
	}
	
	public void setCoord(Point _coord) {
		coord.set(_coord.copy().mult(MainEventHandler.pxSize));
	}
	
	public void setRenderPriority(double _renderPriority) {
		renderPriority = _renderPriority;
	}
	
	public abstract double getWidth();
	public abstract double getHeight();

	@Override
	public int compareTo(PresElementScene arg0) {
		return (int)(renderPriority-arg0.renderPriority);
	}
	
	public double getRenderPrio() {
		return renderPriority;
	}
}
