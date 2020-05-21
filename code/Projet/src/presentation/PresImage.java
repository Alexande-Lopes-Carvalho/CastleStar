package presentation;

import javafx.scene.image.Image;
import shapeSceneFX.Point;

public class PresImage extends PresElementScene {
	private Image img;
	public PresImage(String _img) {
		img = loadPixelatedImage(_img, MainEventHandler.pxSize);
	}
	
	public void render() {
		image(img, getCoord());
	}
	
	public void setCoord(Point _coord) {
		super.setCoord(_coord);
		setRenderPriority(getCoord().getY()+getHeight()/MainEventHandler.pxSize);
	}

	@Override
	public double getWidth() {
		return img.getWidth();
	}

	@Override
	public double getHeight() {
		return img.getHeight();
	}
}
