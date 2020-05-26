package presentation;

import javafx.scene.image.Image;
import shapeSceneFX.Point;

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
	
	public Point getProjectileCoord() {
		return projectileCoord;
	}
}
