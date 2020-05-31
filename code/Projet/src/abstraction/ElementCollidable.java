package abstraction;

import shapeSceneFX.Point;
/**
 * Un element qui possede une hitbox, et donc qui n'est pas franchissable par les entité
 * @author Administrator
 *
 */
public class ElementCollidable extends ElementScene {
	/**
	 * hitbox
	 */
	private Polygon polygon; 
	public ElementCollidable(Point _coord, Polygon _polygon) {
		super(_coord, _polygon.getHighestY());
		polygon = _polygon;
	}
	
	public Polygon getPolygon() {
		return polygon;
	}
	/**
	 * retourne la coordonné dans le niveau du centre de l'hitbox
	 * @return
	 */
	public Point getCenterHitbox() {
		return getCoord().copy().add(polygon.getCenter());
	}
}
