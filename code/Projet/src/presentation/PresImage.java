package presentation;

import javafx.scene.image.Image;
import shapeSceneFX.Point;

public class PresImage extends PresElementScene {
	private Image img;
	public PresImage(String _img) {
		img = loadPixelatedImage(_img, MainEventHandler.pxSize);
	}
	
	public PresImage(Image _img) {
		img = _img;
	}
	
	public void render() {
		image(img, getCoord());
	}

	public double getWidth() {
		return img.getWidth();
	}

	public double getHeight() {
		return img.getHeight();
	}
	
	public boolean doRender(Point camera) {
		return (getCoord().getX() <= camera.getX() && getCoord().getX()+getWidth() >= camera.getX()+width()) || between(getCoord().getX(), camera.getX(), camera.getX()+width()) || between(getCoord().getX()+getWidth(), camera.getX(), camera.getX()+width());
	}
}
