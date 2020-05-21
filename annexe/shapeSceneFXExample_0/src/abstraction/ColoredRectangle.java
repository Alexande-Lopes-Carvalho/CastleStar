package abstraction;

import java.util.Observable;

import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import shapeSceneFX.Point;

public class ColoredRectangle extends Observable {
	private Point coord, dim;
	private Paint p;
	public int value;
	public ColoredRectangle(Point _coord, Point _dim) {
		coord = _coord;
		dim = _dim;
		value = 0;
		p = Color.hsb(value/100., 0.8, 1);
	}
	
	public void newPaint() {
		value += 25;
		value = value%361;
		setPaint(Color.hsb(value, 0.8, 1));
		setChanged();
		notifyObservers(0);
	}
	
	private void setPaint(Paint _p) {
		p = _p;
	}
	
	public Paint getPaint() {
		return p;
	}
	
	public Point getCoord() {
		return coord;
	}
	
	public Point getDimension() {
		return dim;
	}
}
