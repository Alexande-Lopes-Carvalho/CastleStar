package abstraction;

import shapeSceneFX.Point;

public class Lootable extends Entity {
	private double distSpawn;
	public Lootable(double _distSpawn, int _maxLife, Point _coord, Rectangle _rectangle) {
		super(0, _maxLife, _coord, _rectangle);
		distSpawn = _distSpawn;
	}
	
	public double getDistSpawn() {
		return distSpawn;
	}
}
