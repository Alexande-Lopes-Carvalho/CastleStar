package presentation;

import javafx.scene.image.Image;
import shapeSceneFX.Point;
/**
 * Plusieur image ayant un sens, une coordonnée partagé lié a l'affichage et une autre coordonné
 * (ici utilisé pour les bras de personnage devant lancé un projectile, comme l'arc par exemple)
 * 
 * @author Administrator
 * @see AnimatedOrientedImage
 */
public class AnimatedProjectileLauncher extends AnimatedOrientedImage {
	private Point projectileCoord;
	public AnimatedProjectileLauncher(Point _projectileCoord, Point _coord, String ... path) {
		super(_coord, path);
		projectileCoord = _projectileCoord;
	}
	
	public AnimatedProjectileLauncher(Point _projectileCoord, Point _coord, Image ... images) {
		super(_coord, images);
		projectileCoord = _projectileCoord;
	}
	/**
	 * copie superficielle
	 * 
	 * @param a
	 * @see AnimatedOrientedImage#AnimatedOrientedImage(AnimatedOrientedImage)
	 */
	public AnimatedProjectileLauncher(AnimatedProjectileLauncher a) {
		super(a);
		projectileCoord = a.getProjectileCoord().copy();
	}
	
	public Point getProjectileCoord() {
		return projectileCoord;
	}
}
