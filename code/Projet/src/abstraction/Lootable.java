package abstraction;

import shapeSceneFX.Point;
/**
 * Conteneur d'item
 * @author Administrator
 *
 */
public class Lootable extends Entity {
	/**
	 * Rayon dans lequel les item apparaitrons après la destruction du conteneur
	 */
	private double distSpawn;
	public Lootable(double _distSpawn, int _maxLife, Point _coord, Rectangle _rectangle) {
		super(0, _maxLife, _coord, _rectangle);
		distSpawn = _distSpawn;
	}
	
	public double getDistSpawn() {
		return distSpawn;
	}
}
