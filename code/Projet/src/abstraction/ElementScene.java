package abstraction;

import java.util.Observable;

import shapeSceneFX.Point;

public class ElementScene extends Observable {
	public static final Object RENDER_PRIORITY_UPDATE = 0;
	public static final Object COORD_UPDATE = 1;
	private Point coord;
	private double renderPriority, renderPriorityAdd;
	public ElementScene(Point _coord, double _renderPriorityAdd) {
		this(_coord);
		//System.out.println(_renderPriorityAdd);
		renderPriorityAdd = _renderPriorityAdd;
	}
	
	public ElementScene(Point _coord) {
		coord = _coord;
		setRenderPriority(calcRenderPriority());
	}
	
	public Point getCoord() {
		return coord;
	}
	
	public double getRenderPriority() {
		return renderPriority;
	}
	
	public void setRenderPriority(double value) {
		renderPriority = value;
		setChanged();
		notifyObservers(RENDER_PRIORITY_UPDATE);
	}
	
	public double calcRenderPriority() {
		return coord.getY()+renderPriorityAdd;
	}
	
	public void setCoord(Point a) {
		coord.set(a);
		setRenderPriority(calcRenderPriority());
		setChanged();
		notifyObservers(COORD_UPDATE);
	}
}
