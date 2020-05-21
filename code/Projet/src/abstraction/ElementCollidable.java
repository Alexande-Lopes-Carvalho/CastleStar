package abstraction;

import shapeSceneFX.Point;

public class ElementCollidable extends ElementScene {
	private Polygon polygon; 
	public ElementCollidable(Point _coord, Polygon _polygon) {
		super(_coord, _polygon.getHighestY());
		polygon = _polygon;
	}
	
	public Polygon getPolygon() {
		return polygon;
	}
}
